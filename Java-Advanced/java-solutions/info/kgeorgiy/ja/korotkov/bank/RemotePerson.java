package info.kgeorgiy.ja.korotkov.bank;

import java.rmi.RemoteException;

public class RemotePerson extends AbstractPerson {
    public RemotePerson(final String name, final String surname, final String passport) {
        super(name, surname, passport);
    }

    public RemotePerson(final String name, final String surname, final String passport, final Bank bank) {
        super(name, surname, passport);
        this.bank = bank;
    }

    @Override
    public synchronized Account createAccount(final String id) throws RemoteException {
        System.out.println("Creating account " + id);

        final RemoteAccount account = new RemoteAccount(id);
        if (accounts.putIfAbsent(id, account) == null) {
            if (bank != null) {
                bank.createAccount(account, this);
            }
            return account;
        } else {
            return getAccount(id);
        }
    }

    @Override
    public void createAccount(final Account account) throws RemoteException {
        accounts.put(account.getId(), account);
    }
}
