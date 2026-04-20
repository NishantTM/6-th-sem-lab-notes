// Create a RMI application such that a client sends an integer number to the server and the server
// return the factorial value of that integer. Give a clear specification for every step.
package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FactorialServer {
    public static void main(String[] args) {
        try {
            //create a regostry on port 9999
            Registry reg = LocateRegistry.createRegistry(9999);

            //create remote object
            FactorialImpl factObj = new FactorialImpl();

            reg.rebind("factorialService", factObj);
            System.out.println("Factorial Server is ready");
        } catch (RemoteException e) {
            System.out.println("Server exception :" + e);
        }
        
    }
    
}
