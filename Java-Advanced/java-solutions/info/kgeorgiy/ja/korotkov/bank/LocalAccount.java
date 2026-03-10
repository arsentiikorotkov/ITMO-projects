package info.kgeorgiy.ja.korotkov.bank;

import java.rmi.RemoteException;

public class LocalAccount extends AbstractAccount {
    public LocalAccount(final String id) {
        super(id);
    }

    public LocalAccount(final Account account) throws RemoteException {
        super(account.getId());
        amount = account.getAmount();
    }
}
