/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author jonat
 */
public class ImageLoader 
{
    public static BufferedImage loadImage( String path )
    {
        try 
        {
            return ImageIO.read( ImageLoader.class.getResource( path ) );
        } 
        catch (IOException ex) 
        {
            System.out.println("[ERRO] " + ex.getMessage() );
            System.exit(1);
        }
        
        return null;
    }
}
