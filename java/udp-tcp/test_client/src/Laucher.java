
import controller.RobotController;
import java.util.List;
import model.Client;
import model.DataPackage;
import util.Log;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonat
 */
public class Laucher 
{
    public static void main(String[] args)
    {
        Client client = new Client();
        
        while( true )
        {
            List<DataPackage> dataPackages = RobotController.getInstance().createScreenCaptureUDP( 1920,1050 );
        
            for( DataPackage dataPackage : dataPackages )
            {
                byte[] bytes = dataPackage.toBytes();
                Log.info( bytes.length + "-->" );
                client.send( bytes );
            }  
        }
        

    }
}
