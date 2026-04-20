package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RMIClient {
    public static void main(String[] args) {
        RMIClient rc = new RMIClient();
        rc.connectRemote();
    }

    private void connectRemote() {
        try {
            Scanner sc = new Scanner(System.in);
            Registry reg = LocateRegistry.getRegistry("localhost", 9999);
            Adder ad = (Adder) reg.lookup("server_key");
            System.out.print("Enter two numbers: \n>> ");
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(String.format("The sum of %d and %d is: %d", a, b, ad.add(a, b)));
        } catch (NotBoundException | RemoteException e) {
            System.err.println("Exception :" + e);
        }
    }
}
