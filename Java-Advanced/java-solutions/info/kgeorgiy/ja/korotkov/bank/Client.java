package info.kgeorgiy.ja.korotkov.bank;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Arrays;
import java.util.Objects;

public final class Client {
    private final static int DEFAULT_PORT = 8888;

    /**
     * Utility class.
     */
    private Client() {
    }

    public static void main(final String... args) throws RemoteException {
        if (args == null || args.length != 5 || Arrays.stream(args).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Usage: java Client <name> <surname> <passport> <id> <amount>");
        }

        final String name = args[0];
        final String surname = args[1];
        final String passport = args[2];
        final String id = args[3];
        final int amount;
        try {
            amount = Integer.parseInt(args[4]);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Amount must be integer. Message: " + e.getMessage());
        }

        final Bank bank;
        try {
            bank = (Bank) LocateRegistry.getRegistry(DEFAULT_PORT).lookup("//localhost/bank");
        } catch (final NotBoundException e) {
            System.out.println("Bank is not bound");
            return;
        }

        Person person = bank.createPerson(name, surname, passport);
        if (!(person.getName().equals(name)) || !(person.getSurname().equals(surname)) ||
                !(person.getPassport().equals(passport))) {
            throw new IllegalArgumentException("Person is present, but the data is incorrect.");
        }

        Account account = bank.createAccount(id, person);

        account.addDelta(amount);
        System.out.println(person.getAccount(id).getAmount());
    }
}
