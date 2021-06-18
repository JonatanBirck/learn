import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculator extends Remote
{
    int add( int arg0, int arg1 ) throws RemoteException;
    int subtract( int arg0, int arg1 ) throws RemoteException;
    int multiply( int arg0, int arg1 ) throws RemoteException;
    double divide( int arg0, int arg1 ) throws RemoteException;
}
