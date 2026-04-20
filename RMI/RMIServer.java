package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements Adder {
    public RMIServer() throws Exception {
        super();
    }

    @Override
    public int add(int n1, int n2) throws RemoteException {
        return n1 + n2;
    }

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(9999);
            reg.rebind("server_key", new RMIServer());
            System.out.println("Sever is ready");
        } catch (Exception e) {
            System.err.println("Exception:" + e);
        }
    }
}
