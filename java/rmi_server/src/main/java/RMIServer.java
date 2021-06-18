import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) {
        /**
         * RMI Server
         * RMI Methods Name Server
         * RMI Client
         *
         * how to put this RMIServer on?
         * 1º compiling project //javac
         * 2º exec on terminal "rmic -keep Calculator"  // keep is optional to generated StubClass.java || Calculator_Stub stay on client and server
         * 3º exec on terminal "java -Djava.security.policy=server.policy RMIServer";
         */
        try {
            String server = "localhost";
            int port = 1099; // default

            ICalculator calculator = new Calculator();
            String calculatorServiceName = "rmi://" + server + "/calculator";

            System.out.println("info: registering calculator service on RMIRegistry...");

            LocateRegistry.createRegistry( port );
            Naming.rebind( calculatorServiceName, calculator );

            System.out.println("info: waiting for clients...");

        } catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
