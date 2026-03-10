package info.kgeorgiy.ja.korotkov.bank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RemoteBank implements Bank {
    private final int port;
    private final ConcurrentMap<String, RemoteAccount> accounts = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, RemotePerson> persons = new ConcurrentHashMap<>();

    public RemoteBank(final int port) {
        this.port = port;
    }

    @Override
    public Account createAccount(final String id, final Person person) throws RemoteException {
        final String subId = createAccountId(person.getPassport(), id);
        System.out.println("Creating account " + subId);
        final RemoteAccount account = new RemoteAccount(id);
        if (accounts.putIfAbsent(subId, account) == null) {
            UnicastRemoteObject.exportObject(account, port);
            createPerson(person.getName(), person.getSurname(), person.getPassport());
            person.createAccount(account);
            return account;
        } else {
            return getAccount(id, person);
        }
    }

    @Override
    public void createAccount(final RemoteAccount account, final Person person) throws RemoteException {
        accounts.put(createAccountId(person.getPassport(), account.getId()), account);
    }

    @Override
    public Account getAccount(final String id, final Person person) throws RemoteException {
        final String subId = createAccountId(person.getPassport(), id);
        System.out.println("Retrieving account " + subId);
        return accounts.get(subId);
    }

    @Override
    public Person createPerson(final String name, final String surname, final String passport) throws RemoteException {
        System.out.println("Creating person " + passport);
        final RemotePerson person = new RemotePerson(name, surname, passport, this);
        if (persons.putIfAbsent(passport, person) == null) {
            UnicastRemoteObject.exportObject(person, port);
            return person;
        } else {
            return getPerson(passport, true);
        }
    }

    @Override
    public Person getPerson(final String passport, final Boolean isRemote) throws RemoteException {
        System.out.println("Retrieving person " + passport);
        final RemotePerson remotePerson = persons.get(passport);
        return isRemote ? remotePerson : new LocalPerson(remotePerson);
    }

    private String createAccountId(final String passport, final String id) {
        return String.format("%s:%s", passport, id);
    }
}
