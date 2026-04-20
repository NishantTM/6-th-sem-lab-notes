// Create a RMI application such that a client sends an integer number to the server and the server
// return the factorial value of that integer. Give a clear specification for every step.
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Factorial extends Remote {
    int computeFactorial(int n) throws RemoteException;
    
}
