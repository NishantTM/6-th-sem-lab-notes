// Develop RMI application where client input two numbers if both number are even then result
// (𝑎 + 𝑏)2 otherwise (𝑎 + 𝑏)3 .
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Adder1 extends Remote {
        int add(int n1, int n2) throws RemoteException;
}
