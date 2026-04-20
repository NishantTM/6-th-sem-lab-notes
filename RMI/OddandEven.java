package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface OddandEven extends Remote {
    String checkNumber(int n) throws RemoteException;
    
}
