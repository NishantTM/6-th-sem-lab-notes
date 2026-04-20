// 3. Write a java programs using RMI to find product of two numbers
package RMI;

import java.rmi.*;
// import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ProductClient {
    public static void main(String[] args) {
        ProductClient pc = new ProductClient();
        pc.connectRemote();
    }

    private void connectRemote() {
        Scanner sc = new Scanner(System.in);
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 9999);
            Product pd = (Product) reg.lookup("ProductService");
            System.out.println("Enter first numbers :");
            int a = sc.nextInt();

            System.out.println("Enter Second numbers :");
            int b = sc.nextInt();

            int result = pd.multiply(a, b);
            System.out.println("Product of " + a + " and " + b + " is: " + result);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Client Exceptin :" + e);
        }
    }
}
