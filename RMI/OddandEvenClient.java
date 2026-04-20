package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class OddandEvenClient {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 9999);

            OddandEven od = (OddandEven) reg.lookup("OddEvenService");

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a number :");
            int n = sc.nextInt();

            String resultcheck = od.checkNumber(n);
            System.out.println(resultcheck);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Client Exception :" + e);
        }
    }
    
}
