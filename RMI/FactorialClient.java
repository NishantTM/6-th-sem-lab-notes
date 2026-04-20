// Create a RMI application such that a client sends an integer number to the server and the server
// return the factorial value of that integer. Give a clear specification for every step.
package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class FactorialClient {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 9999);

            // Lookup remote object
            Factorial fact = (Factorial) reg.lookup("factorialService");

            // input number
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter an Integer: ");
            int n = sc.nextInt();

            // call remote method
            int result = fact.computeFactorial(n);
            System.out.println("Factorial of " + n + " is:" + result);

        } catch (RemoteException | NotBoundException e) {
            System.err.println("Client Exception: " + e);
        }
    }

}
