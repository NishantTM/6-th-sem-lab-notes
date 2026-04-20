package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class SPPriceClient {
    public static void main(String[] args) {
        try {

            Scanner sc = new Scanner(System.in);
            Registry registry = LocateRegistry.getRegistry("localhost", 9999);

            SellingPrice sp = (SellingPrice) registry.lookup("Sellingprice");
            int costPrice = 5000;
            int discount = 500;

            int SellingPrice = sp.calculateSellingPrice(costPrice, discount);

            System.out.println("Cost Price:" + costPrice);
            System.out.println("Discount:" + discount);
            System.out.println("Selling price :" + SellingPrice);
            
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

}
