package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public  interface Adder extends Remote {
    int add(int n1, int n2) throws RemoteException;
}
