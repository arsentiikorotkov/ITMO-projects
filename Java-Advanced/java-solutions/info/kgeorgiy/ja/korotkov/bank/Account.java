package info.kgeorgiy.ja.korotkov.bank;

import java.io.Serializable;
import java.rmi.*;

public interface Account extends Remote, Serializable {
    /** Returns account identifier. */
    String getId() throws RemoteException;

    /** Returns amount of money in the account. */
    int getAmount() throws RemoteException;

    /** Sets amount of money in the account. */
    void setAmount(final int amount) throws RemoteException;

    /** Add delta to amount of money in the account. */
    void addDelta(final int delta) throws RemoteException;
}
