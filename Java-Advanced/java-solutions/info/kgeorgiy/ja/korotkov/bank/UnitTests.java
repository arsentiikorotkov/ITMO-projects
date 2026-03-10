package info.kgeorgiy.ja.korotkov.bank;


import org.junit.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

//пофиксить названия тестов
//написать многопоточные тесты
//убрать ссылку на банк, фриковые методы и сделать все со списком на персонов

public class UnitTests {
    private final static int DEFAULT_PORT = 8888;
    @Test
    public void firstTest() throws RemoteException {
        System.out.println("Simple actions with account test");
        System.out.println();

        RemoteAccount remoteAcc = new RemoteAccount("11");
        remoteAcc.addDelta(37);
        remoteAcc.addDelta(-9);
        assertEquals(remoteAcc.getAmount(), 28);

        LocalAccount localAcc1 = new LocalAccount("245");
        localAcc1.setAmount(103);
        assertEquals(localAcc1.getAmount(), 103);

        LocalAccount localAcc2 = new LocalAccount(remoteAcc);
        assertEquals(localAcc2.getId(), "11");

        System.out.println();
    }

    @Test
    public void secondTest() throws RemoteException {
        System.out.println("Simple actions with person test");
        System.out.println();

        RemotePerson remotePerson1 = new RemotePerson("Arsentiy", "Korotkov", "1234");
        assertEquals(remotePerson1.getName(), "Arsentiy");
        assertEquals(remotePerson1.getPassport(), "1234");

        RemotePerson remotePerson2 = new RemotePerson("Anna", "Kurbatova", "4321");
        final Map<String, Account> accs = new HashMap<>();
        accs.put("1", remotePerson2.createAccount("1"));
        assertEquals(remotePerson2.createAccount("2"), remotePerson2.getAccount("2"));
        accs.put("2", remotePerson2.getAccount("2"));
        accs.put("3", remotePerson2.createAccount("3"));
        assertEquals(remotePerson2.getAccounts(), Map.copyOf(accs));

        LocalPerson localPerson = new LocalPerson(remotePerson2);
        assertEquals(localPerson.getSurname(), "Kurbatova");
        assertEquals(localPerson.getPassport(), "4321");
        assertNotEquals(localPerson.getAccount("2"), remotePerson2.getAccount("2"));

        System.out.println();
    }

    @Test
    public void thirdTest() throws RemoteException {
        System.out.println("Simple actions with bank test");
        System.out.println();

        RemoteBank bank = new RemoteBank(DEFAULT_PORT);

        final Person person = bank.createPerson("Anna", "Kurbatova", "4321");
        assertEquals(bank.createAccount("1", person), bank.getAccount("1", person));
        assertEquals(bank.createPerson("Arsentiy", "Korotkov", "1234"), bank.getPerson("1234", true));

        System.out.println();
    }

    @Test
    public void forthTest() throws RemoteException {
        System.out.println("Simple actions with client validate test");
        System.out.println();

        assertThrows(IllegalArgumentException.class, () -> Client.main(null));
        assertThrows(IllegalArgumentException.class, () -> Client.main("a", "b", "c"));
        assertThrows(IllegalArgumentException.class, () -> Client.main("a", "b", "c", "d", "e", "f"));
        assertThrows(IllegalArgumentException.class, () -> Client.main("a", "b", "c", "d", "e"));
        assertThrows(IllegalArgumentException.class, () -> Client.main("a", "b", "c", null, "6"));

        System.out.println();
    }

    @Test
    public void fifthTest() throws RemoteException, NotBoundException {
        System.out.println("Simple actions with client server test");
        System.out.println();

        final int port = 1234;

        Bank bankServer = new RemoteBank(port);
        RemotePerson person = (RemotePerson) bankServer.createPerson("Anna", "Kurbatova", "1234");
        Account account = bankServer.createAccount("1", person);
        LocalPerson localPerson = (LocalPerson) bankServer.getPerson("1234", false);

        final Registry registry = LocateRegistry.createRegistry(DEFAULT_PORT);
        registry.rebind("//localhost/bank", bankServer);
        Bank bankClient = (Bank) registry.lookup("//localhost/bank");
        UnicastRemoteObject.exportObject(bankClient, DEFAULT_PORT);

        assertEquals(person, bankClient.getPerson("1234", true));
        assertEquals(account, bankClient.getAccount("1", person));
        assertEquals(localPerson.getPassport(), bankClient.getPerson("1234", false).getPassport());

        System.out.println();
    }
}
