package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageUtil
{
    public static BufferedImage resizeImage(BufferedImage image, int width, int height)
    {
        BufferedImage imgResized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        imgResized.getGraphics().drawImage(image, 0, 0, width, height, null);
        return imgResized;
    }

    public static byte[] resizeImage(byte[] imageByte, int width, int height)
    {
        try
        {
            InputStream inputImage = new ByteArrayInputStream(imageByte);
            BufferedImage image = ImageIO.read(inputImage);
            BufferedImage imgResized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imgResized.getGraphics().drawImage(image, 0, 0, width, height, null);
            return imageToByte(imgResized);
        }
        catch( IOException e )
        {
            System.out.println( "[ERROR] Problems in resizeImage method: " + e.getMessage() );
        }

        return null;
    }

    public static BufferedImage bytesToBufferedImage( byte[] bytes )
    {

        try
        {
            InputStream is = new ByteArrayInputStream(bytes);
            BufferedImage bi = ImageIO.read( is );
        }
        catch (IOException e)
        {
            System.out.println( "[ERROR] Problems in bytesToBufferedImage method: " + e.getMessage() );
        }

        return null;
    }

    public static byte[] imageToByte( BufferedImage img, String extension )
    {
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, extension, baos);
            baos.flush();
            return baos.toByteArray();
        }
        catch( IOException e )
        {
            System.out.println( "[ERROR] Problems in imageToByte method: " + e.getMessage() );
        }

        return null;
    }

    public static byte[] imageToByte( BufferedImage img )
    {
        return imageToByte(img, "jpg");
    }
}