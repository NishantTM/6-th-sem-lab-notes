package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FactorialImpl extends UnicastRemoteObject implements Factorial {

    protected FactorialImpl() throws RemoteException {
        super();
    }

    @Override
    public int computeFactorial(int n) throws RemoteException {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;

    }

}
