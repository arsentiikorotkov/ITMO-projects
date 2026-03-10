package info.kgeorgiy.ja.korotkov.bank;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class AbstractPerson implements Person {
    protected final String name;
    protected final String surname;
    protected final String passport;
    protected Bank bank = null;
    protected final ConcurrentMap<String, Account> accounts = new ConcurrentHashMap<>();

    protected AbstractPerson(final String name, final String surname, final String passport) {
        this.name = name;
        this.surname = surname;
        this.passport = passport;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getPassport() {
        return passport;
    }

    @Override
    public synchronized Map<String, Account> getAccounts() {
        return Map.copyOf(accounts);
    }

    @Override
    public synchronized Account getAccount(final String id) {
        return accounts.get(id);
    }
}
