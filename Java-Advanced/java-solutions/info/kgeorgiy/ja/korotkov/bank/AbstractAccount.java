package info.kgeorgiy.ja.korotkov.bank;

public abstract class AbstractAccount implements Account {
    protected final String id;
    protected int amount;

    protected AbstractAccount(final String id) {
        this.id = id;
        amount = 0;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public synchronized int getAmount() {
        System.out.println("Getting amount of money for account " + id);
        return amount;
    }

    @Override
    public synchronized void setAmount(final int amount) {
        System.out.println("Setting amount of money for account " + id);
        this.amount = amount;
    }

    @Override
    public synchronized void addDelta(final int delta) {
        System.out.println("Add delta of money for account " + id);
        this.amount += delta;
    }
}
