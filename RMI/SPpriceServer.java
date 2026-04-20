package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SPpriceServer extends UnicastRemoteObject implements SellingPrice{

    protected SPpriceServer() throws RemoteException {
        super();
    }

    @Override
    public int calculateSellingPrice(int costPrice, int discount) throws RemoteException {
        return costPrice - discount;
    }
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(9999);

            registry.rebind("Sellingprice", new SPpriceServer());
            System.out.println("Selling price server are ready");
        } catch (RemoteException e) {
            System.out.println("Server exception :" + e);
        }
    }
    
}
