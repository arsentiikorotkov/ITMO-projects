package info.kgeorgiy.ja.korotkov.bank;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bank extends Remote {
    /**
     * Creates a new account with specified identifier if it does not already exist.
     *
     * @param id account id
     * @return created or existing account.
     */
    Account createAccount(final String id, final Person person) throws RemoteException;

    /**
     * Add this account to person.
     *
     * @param account account
     * @param person person
     */
    void createAccount(final RemoteAccount account, final Person person) throws RemoteException;

    /**
     * Returns account by identifier.
     *
     * @param id account id
     * @return account with specified identifier or {@code null} if such account does not exist.
     */
    Account getAccount(final String id, final Person person) throws RemoteException;

    /**
     * Creates a new remotePerson by data.
     *
     * @param name person name
     * @param surname person surname
     * @param passport person passport
     * @return remotePerson by data
     */
    Person createPerson(final String name, final String surname, final String passport) throws RemoteException;

    /**
     * Returns person by data.
     *
     * @param passport person passport
     * @param isRemote equals true if person is remote else false if person is local
     * @return person
     */
    Person getPerson(final String passport, final Boolean isRemote) throws RemoteException;
}
