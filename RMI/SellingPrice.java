package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SellingPrice extends Remote {
        int calculateSellingPrice(int costPrice, int discount) throws RemoteException;
}
