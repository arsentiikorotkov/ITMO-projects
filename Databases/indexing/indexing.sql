-- (1.G)

-- Индекс для первичного ключа таблицы Groups, упорядоченность не важна.
create unique index GroupsPk on Groups using hash (GroupId);

-- Индекс для быстрого поиска по GroupName, покрывающий индекс для избежания лишнего обращения к таблице, unique исходя из фз GroupName → GroupId, упорядоченность для работы с префиксом индекса.
create unique index GroupsNameId on Groups using btree (GroupName, GroupId);

-- (1.S)

-- Индекс для первичного ключа таблицы Students, упорядоченность не важна.
create unique index StudentsPk on Students using hash (StudentId);

-- Индекс для быстрого поиска по StudentName, покрывающий индекс для избежания лишнего обращения к таблице, unique исходя из наличия StudentId, упорядоченность для работы с префиксом индекса.
create unique index StudentsNameId on Students using btree (StudentName, StudentId);

-- Внешний ключ, упорядоченность не важна.
create index StudentsGId on Students using hash (GroupId);

-- (1.C)

-- Индекс для первичного ключа таблицы Courses, упорядоченность не важна.
create unique index CoursesPk on Courses using hash (CourseId);

-- Индекс для быстрого поиска по CourseName, покрывающий индекс для избежания лишних обращений к таблице, unique так как содержит ключ, упорядоченность важна для работы с префиксом индекса.
create unique index CoursesNameId on Courses using btree (CourseName, CourseId);

-- (1.L)

-- Индекс для первичного ключа таблицы Lecturers, упорядоченность не важна.
create unique index LecturersPk on Lecturers using hash (LecturerId);

-- Индекс для быстрого поиска по LecturerName, покрывающий индекс для избежания лишних обращений к таблице, unique так как содержит ключ, упорядоченность важна для работы с префиксом индекса.
create unique index LecturersNameId on Lecturers using btree (LecturerName, LecturerId);

-- (1.P)

-- Индекс для первичного ключа таблицы Plan, упорядоченность важна, так как хотим иногда работать с префиксом индекса.
create unique index PlanPk on Plan using btree (GroupId, CourseId);

-- Индекс для быстрого поиска по CourseId, упорядоченность важна, так как хотим иногда работать с префиксом индекса, unique, так как содержит ключ.
create unique index PlanCIdGId on Plan using btree (CourseId, GroupId);

-- Внешний ключ, упорядоченность не важна.
create index PlanLId on Plan using hash (LecturerId);

-- (1.M)

-- Индекс для первичного ключа таблицы Marks, упорядоченность важна, так как хотим иногда работать с префиксом индекса.
create unique index MarksPk on Marks using btree (StudentId, CourseId);

-- Индекс для быстрого поиска по CourseId, упорядоченность важна, так как хотим иногда работать с префиксом индекса.
create index MarksCIdMark on Marks using btree (CourseId, Mark);

-- Индекс для быстрого поиска оценок по StudentId, упорядоченность важна, так как хотим иногда работать с префиксом индекса.
create index MarksSIdMark on Marks using btree (StudentId, Mark);

-- (1.C)

-- Индекс для первичного ключа таблицы Clubs, упорядоченность не важна.
create unique index ClubsPk on Clubs using hash (ClubId);

-- Индекс для быстрого поиска по ClubName, unique исходя из фз ClubName → ClubId, ClubStudentHeadId, упорядоченность не важна.
create unique index ClubsName on Clubs using hash (ClubName);

-- Индекс для быстрого поиска по ClubStudentHeadId, упорядоченность не важна.
create index ClubsSHId on Clubs using hash (ClubStudentHeadId);

-- (1.CM)

-- Индекс для первичного ключа таблицы ClubMembers, упорядоченность важна, так как хотим иногда работать с префиксом индекса.
create unique index ClubMembersPk1 on ClubMembers using btree (ClubId, StudentId);

-- Хотим быстро искать ClubId по StudentId, упорядоченность важна, так как хотим иногда работать с префиксом индекса, unique так как содержит ключ.
create unique index ClubMembersPk2 on ClubMembers using btree (StudentId, ClubId);

-- (2)

select avg(cast(Mark as float)) as AvgMark
from Students
  natural join Marks
  natural join Courses
  natural join ClubMembers
  natural join Clubs
where CourseName = :CourseName
  and ClubName = :ClubName;

-- Индекс для первичного ключа для Students, упорядоченность не важна.
create unique index StudentsPk on Students using hash (StudentId);

-- Индекс для внешнего ключа, упорядоченность не важна.
create index MarksSId on Marks using hash (StudentId);

-- Индекс для первичного ключа для Marks, CourseId - индекс для внешнего ключа, упорядоченность важна для работы с внешним ключом, покрывающий индекс для работы с агрегацией.
create unique index MarksPk on Marks using btree (CourseId, StudentId, Mark);

-- Индекс для первичного ключа для Courses, упорядоченность не важна.
create unique index CoursesPk on Courses using hash (CourseId);

-- Индекс для внешнего ключа, упорядоченность не важна.
create index ClubMembersSId on ClubMembers using hash (StudentId);

-- Индекс для первичного ключа для ClubMembers.
create unique index ClubMembersCId on ClubMembers using btree (ClubId, StudentId);

-- Индекс для первичного ключа для Clubs, упорядоченность не важна.
create unique index ClubsPk on Clubs using hash (ClubId);

-- Индекс для быстрого поиска по CourseName, упорядоченность не важна.
create index CoursesName on Courses using hash (CourseName);

-- Индекс для быстрого поиска по ClubsName, упорядоченность не важна.
create unique index ClubsName on Clubs using hash (ClubName);

-- (3.1)

-- Клубы, в которых глава имеет StudentId не меньше, чем 5.
select ClubName 
from Clubs 
where ClubStudentHeadId > 5;

-- Так как ClubsSHId это hash индекс, а нам теперь необходимо также сравнение, старый индекс стал бесполезен.
drop index ClubsSHId;

-- Быстрый поиск по ClubStudentHeadId, упорядочивание важно для сравнения, покрывающий индекс для избежания лишнего обращения к таблице.
create unique index ClubsSHIdName on Clubs using btree (ClubStudentHeadId, ClubName);

-- (3.2)

-- Студенты, являющиеся членами какого-то из клубов по фитнесу.
select distinct StudentName
from Students natural join ClubMembers natural join Clubs
where ClubName like 'Фитнес %';

-- Так как ClubsName это hash индекс, а нам теперь необходим поиск по префиксу.
drop index ClubsName;

-- Быстрый поиск по ClubsName, упорядочивание важно для сравнения, unique в связи с фз.
create unique index ClubsName on Clubs using btree (ClubName);

-- (3.3)

-- Группы, для которых существует лектор, который читает более одного курса для этой группы по плану.
select GroupName
from Plan natural join Groups
group by LecturerId, GroupId
having count(CourseId) > 1;

-- Так как PlanLId недостаточно эффективно работает для нашего запроса.
drop index PlanLId;

-- Быстрый поиск по LecturerId, также эффективно работает в этом запросе с агрегацией (для этого также упорядоченность), unique так как содержит ключ, упорядоченность для работы с префиксом индекса.
create unique index PlanLIdGIdCId on Plan using btree (LecturerId, GroupId, CourseId);