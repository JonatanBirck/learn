package controllers;

import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
            System.out.println( "[ERROR] Problems when creating Robot: " + e.getMessage() );
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

}
