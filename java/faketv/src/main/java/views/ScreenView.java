package views;

import controllers.KeyController;
import controllers.RobotController;
import lombok.Getter;
import model.Display;
import model.User;
import utilities.ImageUtil;

import javax.net.ssl.KeyManager;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

@Getter
public class ScreenView implements Runnable
{
    private Display display;
    private KeyController KeyController;
    private boolean running = false;
    private User user;
    private String title;
    private int width;
    private int height;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private Thread thread;
    private int fps = 60;

    public ScreenView( User user, String title, int width, int height )
    {
        KeyController = new KeyController();

        this.user = user;
        this.title = title;
        this.width = width;
        this.height = height;
    }

    private void init()
    {
        display = new Display( title, width, height );
        display.getFrame().addKeyListener( KeyController );
    }

    private void tick()
    {
        KeyController.tick();
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

        BufferedImage screen = RobotController.getInstance().createScreenCapture();

        // original
        byte[] bytes = ImageUtil.imageToByte( screen );

        // compactada
        byte[] bytes2 = ImageUtil.resizeImage( bytes, 480, 200 );

        // redimensionada
        byte[] bytes3 = ImageUtil.resizeImage( bytes2, 1280,720);

        ImageIcon a = new ImageIcon( bytes3 );

        graphics.drawImage( a.getImage(), 0, 0, null );

        // fps
        graphics.setFont( new Font("Monospaced", Font.BOLD, 20 ) );
        graphics.setColor( Color.RED );
        graphics.drawString( fps + "", width-50, 40 );

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
