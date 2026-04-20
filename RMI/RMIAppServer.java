// Develop RMI application where client input two numbers if both number are even then result
// (𝑎 + 𝑏)2 otherwise (𝑎 + 𝑏)3 .
package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIAppServer extends UnicastRemoteObject implements Adder1 {
    public RMIAppServer() throws RemoteException   {
        super();
    }

    @Override
    public int add(int n1, int n2) {
        int sum = n1 + n2;
        if (n1 % 2 == 0 && n2 % 2 == 0) {
            return sum * sum;  //(a+b)^2
        }
        else{
            return sum * sum * sum; //(a+b)^3
        }  
    }
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("hi server",  new RMIAppServer());
            System.out.println("Server is ready!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
}
