package info.kgeorgiy.ja.korotkov.bank;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface Person extends Remote, Serializable {
    /** Returns person name. */
    String getName() throws RemoteException;

    /** Returns person surname. */
    String getSurname() throws RemoteException;

    /** Returns person passport. */
    String getPassport() throws RemoteException;

    /** Returns person accounts in a map with id keys. */
    Map<String, Account> getAccounts() throws RemoteException;

    /** Returns person account by id. */
    Account getAccount(final String id) throws RemoteException;

    /** Create to person an account by id. */
    Account createAccount(final String id) throws RemoteException;

    /** Create to person an account by account. */
    void createAccount(final Account account) throws RemoteException;
}
