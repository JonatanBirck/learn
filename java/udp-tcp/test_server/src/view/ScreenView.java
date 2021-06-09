package view;

import model.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import util.Log;

public class ScreenView implements Runnable
{
    private Display display;
    private boolean running = false;
    private String title;
    private int width;
    private int height;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private Thread thread;
    private int fps = 60;
    private byte[] tempBytes;

    public ScreenView( String title, int width, int height )
    {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    private void init()
    {
        display = new Display( title, width, height );
    }

    private void tick()
    {
    }
    
    public void setBytes( byte[] tempBytes )
    {
        this.tempBytes = tempBytes;
    }

    private void render()
    {
        bufferStrategy = display.getCanvas().getBufferStrategy();

        if( bufferStrategy == null )
        {
            display.getCanvas().createBufferStrategy( 5 );
            return;
        }

        graphics = bufferStrategy.getDrawGraphics();

        //clear
        graphics.clearRect(0, 0, width, height);
        
        // original
        if( tempBytes != null )
        {
            try
            {
                //ImageIcon image = new ImageIcon( tempBytes );
        
                //graphics.drawImage( image.getImage(), 0, 0, null );

                InputStream in = new ByteArrayInputStream( tempBytes, 0, tempBytes.length);

                BufferedImage image = ImageIO.read(in);

                graphics.drawImage( image, 0, 0, null ); 
            }
            catch( Exception e )
            {
                Log.erro(e);
            }

        }

        // fps
        graphics.setFont( new Font("Monospaced", Font.BOLD, 20 ) );
        graphics.setColor( Color.RED );
        graphics.drawString( fps + "", width-50, 40 );
        
        // bytes
        graphics.setFont( new Font("Monospaced", Font.BOLD, 20 ) );
        graphics.setColor( Color.RED );
        String bytesTransfer = ( (tempBytes == null) ? "0" : tempBytes.length + "" );
        int widthBytes = (width-26) - ( bytesTransfer.toString().toCharArray().length * 12 );
        graphics.drawString( bytesTransfer, widthBytes, 60 );        

        //show
        bufferStrategy.show();
        graphics.dispose();
    }

    @Override
    public void run()
    {
        init();

        double timePerTick = 1000000000 / 30;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        
        while( running )
        {
            now = System.nanoTime();
            delta += ( now - lastTime ) / timePerTick;
            timer += ( now - lastTime );
            lastTime = now;

            if( delta >= 1 )
            {
                tick();
                render();
                ticks++;
                delta--;
            }

            if( timer >= 1000000000 )
            {
                fps = ticks;
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public synchronized void start()
    {
        if( running )
            return;

        running = true;
        thread = new Thread( this );
        thread.start();
    }

    public synchronized void stop()
    {
        if( !running )
            return;

        running = false;

        try
        {
            thread.join();
        }
        catch (InterruptedException ex)
        {
            System.out.println("[ERRO]: " + ex.getMessage() );
        }
    }
}
