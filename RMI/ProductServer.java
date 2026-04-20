// 3. Write a java programs using RMI to find product of two numbers
package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ProductServer extends UnicastRemoteObject implements Product {
    public ProductServer() throws RemoteException{
        super();
    }

    @Override
    public int multiply(int a, int b) throws RemoteException {
        return a * b;
    }
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(9999);
            reg.rebind("ProductService", new ProductServer());
            System.out.println("Product server is ready");
        } catch (RemoteException e) {
            System.out.println("Exception :" + e);
        }
    }
    
}
