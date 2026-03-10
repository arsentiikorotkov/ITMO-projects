package info.kgeorgiy.ja.korotkov.bank;

import jdk.jshell.spi.ExecutionControl;

import java.io.Serializable;
import java.rmi.RemoteException;

public class LocalPerson extends AbstractPerson implements Serializable {
    public LocalPerson(final RemotePerson remotePerson) {
        super(remotePerson.name, remotePerson.surname, remotePerson.passport);
        remotePerson.getAccounts().values().forEach(acc -> {
            try {
                accounts.put(acc.getId(), new LocalAccount(acc));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public synchronized Account createAccount(final String id) {
        System.out.println("Creating account " + id);

        final Account account = new LocalAccount(id);
        if (accounts.putIfAbsent(id, account) == null) {
            return account;
        } else {
            return getAccount(id);
        }
    }

    @Override
    public void createAccount(final Account account) throws RemoteException {}
}
