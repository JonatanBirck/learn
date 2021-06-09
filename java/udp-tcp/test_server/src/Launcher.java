
import model.Server;
import view.ScreenView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonat
 */
public class Launcher 
{
    public static void main(String[] args) 
    {        
        // SERVER
        
        ScreenView screenView = new ScreenView("Teste",1920,1050);
        screenView.start();
                
        Server server = new Server( screenView );
        
        
        /*
        //<-- TESTE LOCAL -->
        ArrayList<DataPackage> packages = new ArrayList<>();
        
        ScreenView screenView = new ScreenView("Teste",1024,820);
        screenView.start();
        
        while( true )
        {
            List<DataPackage> dataPackages = RobotController.getInstance().createScreenCaptureUDP( 1024,820 );
            
            for( DataPackage dataPackage : dataPackages )
            {
                packages.add(dataPackage);
            }
            
            byte[] bytes = Util.dataPackagesToImageByte( dataPackages );
            
            screenView.setBytes( bytes );
        }
        */
    }
}
