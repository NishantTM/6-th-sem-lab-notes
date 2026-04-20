// Develop RMI application where client input two numbers if both number are even then result
// (𝑎 + 𝑏)2 otherwise (𝑎 + 𝑏)3 .
package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
// import java.rmi.registry.Registry;
import java.util.Scanner;

public class RMIAppClient {
    public static void main(String[] args) {
        RMIAppClient rmc = new RMIAppClient();
        rmc.connectRemote();
    }

    private void connectRemote() {
        Scanner sc = new Scanner(System.in);
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            Adder1 ad = (Adder1) reg.lookup("hi server");
            System.out.println("Enter two numbers: ");
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println("Result is :" + ad.add(a, b));
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Exception: " + e);
        }
    }

    
}
