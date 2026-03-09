-- Таблица рейсов, FlightId - первичный ключ.
-- SellAllowed - параметр настройки рейса, показывает возможность покупки билетов.
-- ReservationAllowed - параметр настройки рейса, показывает возможность брони билетов.
create table Flights (
  FlightId int primary key,
  FlightTime timestamp not null,
  PlaneId int not null,
  SellAllowed boolean not null,
  ReservationAllowed boolean not null
);

-- Таблица мест в самолетах, (PlaneId, SeatNo) - первичный ключ.
-- Ограничение на SeatNo, исходя из условий.
create table Seats (
  PlaneId integer not null,
  SeatNo varchar(4) not null check (SeatNo ~ '^[1-9][0-9]{0,2}[A-Z]$'),
  primary key (PlaneId, SeatNo)
);

-- Таблица пользователей, UserId - первичный ключ.
-- IsAdmin - параметр, который показывает, есть ли у пользователя права админа.
create table Users (
  UserId int primary key,
  Pass text not null,
  IsAdmin boolean not null default false
);

-- Вспомогательный тип для индекации билета (куплен или забронирован).
create type ticket_type as enum ('reserved', 'selled');

-- Таблица билетов, (FlightId, SeatNo) - первичный ключ.
-- FlightId, UserId - внешние ключи.
-- Type - тип билета (куплен или забронирован).
-- ReservedUntil - параметр, который показывает время окончания брони.
create table Tickets (
  FlightId int not null, 
  SeatNo varchar(4) not null check (SeatNo ~ '^[1-9][0-9]{0,2}[A-Z]$'), 
  UserId int not null,
  Type ticket_type not null,
  ReservedUntil timestamp,
  primary key (FlightId, SeatNo),
  foreign key (FlightId) references Flights (FlightId),
  foreign key (UserId) references Users (UserId)
);



create or replace function RegisterUser(user_id int, pass text)
returns boolean as $$
begin
  insert into Users (UserId, Pass)
  values (user_id, pass);
  
  return true;

exception 
  when others then
    return false;
end;
$$ language plpgsql;


-- обновляет параметры в связи со скорым рейсом.
create or replace function RefreshParams(flight_id int)
returns void as $$
declare 
  flight_time timestamp;
begin
  flight_time := (select FlightTime from Flights where FlightId = flight_id);

  if flight_time - now() < interval '3 days' 
    and params.ReservationAllowed
  then 
    update Flights
    set 
      ReservationAllowed = false
    where FlightId = flight_id;
  end if;

  if flight_time - now() < interval '3 hours' 
    and params.SellAllowed
  then 
    update Flights
    set 
      SellAllowed = false
    where FlightId = flight_id;
  end if;
end;
$$ language plpgsql;

create or replace function ManageFlight(
  user_id int, pass text, flight_id int, 
  sell_allowed boolean, reservation_allowed boolean
)
returns void as $$
declare 
  flight_time timestamp;
  params record;
  new_sell_allowed boolean := sell_allowed;
  new_reservation_allowed boolean := reservation_allowed;
begin
  perform RefreshParams(flight_id);

  if not IsCorrectAdmin(user_id, pass)
  then return;
  end if;

  params := FlightParams(flight_id);
  flight_time := (select FlightTime from Flights where FlightId = flight_id);

  if flight_time - now() < interval '3 days' then 
  new_reservation_allowed := params.ReservationAllowed;
  end if;

  if flight_time - now() < interval '3 hours' then 
  new_sell_allowed := params.SellAllowed;
  end if;

  update Flights
  set 
    SellAllowed = new_sell_allowed,
    ReservationAllowed = new_reservation_allowed
  where FlightId = flight_id;
end;
$$ language plpgsql;



-- Функция по рейсу возвращает его параметры
create or replace function FlightParams(flight_id int)
returns record as $$
declare
  res record;
begin
  perform RefreshParams();

  select SellAllowed, ReservationAllowed
  into res
  from Flights
  where FlightId = flight_id;
    
  return res;
end;
$$ language plpgsql;

create or replace function FreeSeats(flight_id int)
returns Table(SeatNo varchar(4)) as $$
declare
  params record;
begin
  params := FlightParams(flight_id);

  if not params.SellAllowed or not params.ReservationAllowed
  then return;
  end if;

  return query
  select SeatNo
  from Seats
  where PlaneId in (select PlaneId from Flights where FlightId = flight_id)
    and SeatNo not in
      (select SeatNo
      from Tickets natural join Flights
      where FlightId = flight_id
        and (Type = 'selled' or ReservedUntil > now()));
end;
$$ language plpgsql;


-- Функция проверяет, существует ли пользователь с данным логином и паролем
create or replace function IsCorrectUser(user_id int, pass text)
returns boolean as $$
begin
  return exists
    (select UserId
    from Users
    where UserId = user_id
      and Pass = pass);
end;
$$ language plpgsql;

-- Функция проверяет, существует ли админ с данным логином и паролем
create or replace function IsCorrectAdmin(user_id int, pass text)
returns boolean as $$
begin
  return exists
    (select UserId
    from Users
    where UserId = user_id
      and Pass = pass
      and Admin);
end;
$$ language plpgsql;

-- Функция по первичному ключу Tickets возвращает остальные данные билета
create or replace function TicketInfo(
  flight_id int, 
  seat_no varchar(4)
) returns record as $$
declare
  res record;
begin
  select UserId, Type, ReservedUntil 
  into res
  from Tickets
  where FlightId = flight_id
    and SeatNo = seat_no;
    
  return res;
end;
$$ language plpgsql;

create or replace function Reserve(user_id int, pass text, flight_id int, seat_no varchar(4))
returns boolean as $$
declare 
  ticket_info record;
  params record;
begin
  params := FlightParams(flight_id);

  if not IsCorrectUser(user_id, pass)
    or not params.ReservationAllowed
  then return false;
  end if;

  ticket_info := TicketInfo(flight_id, seat_no);

  case 
    when ticket_info.Type is null then 
      insert into Tickets (FlightId, SeatNo, UserId, Type, ReservedUntil)
      values (flight_id, seat_no, user_id, 'reserved', now() + interval '1 day');

      return true;
    when ticket_info.Type = 'reserved' and ticket_info.ReservedUntil <= now() then
      update Tickets
      set 
        UserId = user_id,
        ReservedUntil = now() + interval '1 day'
      where FlightId = flight_id
        and SeatNo = seat_no;

      return true;
    else return false;
  end case;

exception 
  when others then
    return false;
end;
$$ language plpgsql;



create or replace function ExtendReservation(user_id int, pass text, flight_id int, seat_no varchar(4))
returns boolean as $$
declare 
  ticket_info record;
  params record;
begin
  params := FlightParams(flight_id);

  if not IsCorrectUser(user_id, pass)
    or not params.ReservationAllowed
  then return false;
  end if;

  ticket_info := TicketInfo(flight_id, seat_no);

  if ticket_type.UserId = user_id 
    and ticket_info.Type = 'reserved' 
    and ticket_info.ReservedUntil > now()
  then 
    update Tickets
    set ReservedUntil = now() + interval '1 day'
    where FlightId = flight_id
      and SeatNo = seat_no;

    return true;
  end if;

  return false;

exception 
  when others then
    return false;
end;
$$ language plpgsql;



create or replace function BuyFree(user_id int, pass text, flight_id int, seat_no varchar(4))
returns boolean as $$
declare 
  ticket_info record;
  params record;
begin
  params := FlightParams(flight_id);

  if not IsCorrectUser(user_id, pass)
    or not params.SellAllowed
  then return false;
  end if;

  ticket_info := TicketInfo(flight_id, seat_no);

  case 
    when ticket_info.Type is null then 
      insert into Tickets (FlightId, SeatNo, UserId, Type)
      values (flight_id, seat_no, user_id, 'selled');

      return true;
    when ticket_info.Type = 'reserved' and ticket_info.ReservedUntil <= now() then
      update Tickets
      set 
        UserId = user_id,
        Type = 'selled'
      where FlightId = flight_id
        and SeatNo = seat_no;

      return true;
    else return false;
  end case;

exception 
  when others then
    return false;
end;
$$ language plpgsql;



create or replace function BuyReserved(user_id int, pass text, flight_id int, seat_no varchar(4))
returns boolean as $$
declare 
  ticket_info record;
  params record;
begin
  params := FlightParams(flight_id);

  if not IsCorrectUser(user_id, pass)
    or not params.SellAllowed
  then return false;
  end if;

  ticket_info := TicketInfo(flight_id, seat_no);

  if ticket_info.UserId = user_id
    and ticket_info.Type = 'reserved' 
    and ticket_info.ReservedUntil > now()
  then 
    update Tickets
    set Type = 'selled'
    where FlightId = flight_id
      and SeatNo = seat_no;

    return true;
  end if;

  return false;

exception 
  when others then
    return false;
end;
$$ language plpgsql;



create or replace function FlightsStatistics(user_id int, pass text)
returns 
  Table(FlightId int,
        SellAllowed boolean, 
        ReservationAllowed boolean,
        FreeSeatsCount int,
        ReservedSeatsCount int,
        SelledSeatsCount int) as $$
begin
  if not IsCorrectAdmin(user_id, pass)
  then return;
  end if;

  return query
  select F.FlightId, F.SellAllowed, F.ReservationAllowed, 
    (select count(*) from Seats where PlaneId = F.PlaneId) 
      - count(case when T.Type = 'reserved' and T.ReservedUntil > now() then 1 end) 
      - count(case when T.Type = 'selled' then 1 end) as FreeSeatsCount, 
    count(case when T.Type = 'reserved' and T.ReservedUntil > now() then 1 end) as ReservedSeatsCount, 
    count(case when T.Type = 'selled' then 1 end) as SelledSeatsCount
  from Flights F
    left join Tickets T 
      on F.FlightId = T.FlightId
  group by F.FlightId, F.SellAllowed, F.ReservationAllowed, F.PlaneId;
end;
$$ language plpgsql;



create or replace function FlightStat(user_id int, pass text, flight_id int)
returns 
  Table(FlightId int,
        SellAllowed boolean, 
        ReservationAllowed boolean,
        FreeSeatsCount int,
        ReservedSeatsCount int,
        SelledSeatsCount int) as $$
begin
  if not IsCorrectAdmin(user_id, pass)
  then return;
  end if;

  return query
  select FlightId, SellAllowed, ReservationAllowed,
    FreeSeatsCount, ReservedSeatsCount, SelledSeatsCount
  from FlightsStatistics(user_id, pass)
  where FlightId = flight_id;
end;
$$ language plpgsql;



create or replace function CompressSeats(user_id int, pass text, flight_id int)
returns void as $$
declare
  cur cursor for
    select UserId, Type, ReservedUntil, SeatNo
    from Tickets
    where FlightId = flight_id
    order by Type desc;
  
  seat_no varchar(4);
  user_id int;
  cur_ticket_type ticket_type;
  reserved_until timestamp;
  i int := 1;
begin
  if not IsCorrectAdmin(user_id, pass)
  then return;
  end if;

  start transaction;
    open cur;

    delete from Tickets where FlightId = flight_id;

    loop
      fetch cur into user_id, cur_ticket_type, reserved_until;
      exit when not found;

      select SeatNo
      into seat_no
      from Seats
      where PlaneId = (select PlaneId from Flights where FlightId = flight_id)
      order by length(SeatNo) asc, SeatNo asc
      offset (i - 1) rows
      limit 1;

      insert into Tickets (FlightId, SeatNo, UserId, Type, ReservedUntil)
      values (flight_id, seat_no, user_id, cur_ticket_type, reserved_until);
      i := i + 1;
    end loop; 

    close cur;
  commit;
end;
$$ language plpgsql;