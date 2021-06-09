/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.RobotController;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.List;
import util.ImageUtil;
import util.Log;

/**
 *
 * @author jonat
 */
public class ImagePackageWorker extends Thread
{
    private Robot robot;
    private boolean running = false;
    private int width;
    private int height;        
    private int x;
    private int y;
    
    public ImagePackageWorker( int x, int y, int width, int height )
    {
        try
        {
            this.robot = new Robot();
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
        catch( AWTException e )
        {
            Log.erro(e);
        }
    }
    
    @Override
    public void run() 
    {
        
    }
    
    private BufferedImage createScreenCapture( int x, int y, int width, int height )
    {
        Rectangle rectangle = new Rectangle( x, y, width, height );

        return this.robot.createScreenCapture( rectangle );
    }
    
    public List<DataImagePackage> createScreenCaptureUDP( int x, int y, int width, int height )
    {
        BufferedImage image = createScreenCapture( x, y, width, height );
        
        return DataImagePackage.createPackages( ImageUtil.imageToByte(image) );
    }    
}
