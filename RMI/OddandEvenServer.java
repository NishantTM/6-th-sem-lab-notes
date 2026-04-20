package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class OddandEvenServer extends UnicastRemoteObject implements OddandEven {

    protected OddandEvenServer() throws RemoteException {
        super();
    }

    @Override
    public String checkNumber(int n) throws RemoteException {
        if (n % 2 == 0) {
            return n + " is even";
        } else {
            return n + " is odd";
        }
    }

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(9999);

            reg.rebind("OddEvenService", new OddandEvenServer());

            System.out.println("Odd and even server are ready");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
