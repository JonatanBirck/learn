import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Calculator extends UnicastRemoteObject implements ICalculator {
    protected Calculator() throws RemoteException {
    }

    @Override
    public int add(int arg0, int arg1) throws RemoteException {
        return arg0 + arg1;
    }

    @Override
    public int subtract(int arg0, int arg1) throws RemoteException {
        return arg0 - arg1;
    }

    @Override
    public int multiply(int arg0, int arg1) throws RemoteException {
        return arg0 * arg1;
    }

    @Override
    public double divide(int arg0, int arg1) throws RemoteException {
        return arg0 / arg1;
    }
}
