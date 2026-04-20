// 3. Write a java programs using RMI to find product of two numbers
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Product extends Remote {
    int multiply(int a, int b) throws RemoteException;
    
}
