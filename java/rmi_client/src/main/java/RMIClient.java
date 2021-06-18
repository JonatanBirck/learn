import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {

        String server = "localhost";
        int port = 1099;

        String calculatorService = "rmi://" + server + ":" + port + "/Calculator";

        ICalculator calculator = (ICalculator) Naming.lookup( calculatorService );

        System.out.println( "Soma: 1 + 2 = " + calculator.add( 1, 2 ) );
    }
}
