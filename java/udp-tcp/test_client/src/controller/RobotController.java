package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import util.ImageUtil;
import util.Log;
import model.DataPackage;

public class RobotController
{
    private static RobotController robotController = null;
    private Robot robot;

    public RobotController()
    {
        try
        {
            this.robot = new Robot();
        }
        catch( AWTException e )
        {
            Log.erro(e);
        }
    }

    public static RobotController getInstance()
    {
        if( robotController == null )
            robotController = new RobotController();

        return robotController;
    }

    public BufferedImage createScreenCapture()
    {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) screen.getWidth();
        int height = (int) screen.getHeight();

        Rectangle rectangle = new Rectangle( width, height );

        return this.robot.createScreenCapture( rectangle );
    }
    
    public BufferedImage createScreenCapture( int width, int height )
    {
        Rectangle rectangle = new Rectangle( width, height );

        return this.robot.createScreenCapture( rectangle );
    }    
    
    public List<DataPackage> createScreenCaptureUDP()
    {
        BufferedImage image = createScreenCapture();
        
        return DataPackage.createPackages( ImageUtil.imageToByte(image) );
    }
    
    public List<DataPackage> createScreenCaptureUDP( int width, int height )
    {
        BufferedImage image = createScreenCapture( width, height);
        
        return DataPackage.createPackages( ImageUtil.imageToByte(image) );
    }    
}
