/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import manager.KeyManager;
import org.json.JSONObject;

/**
 *
 * @author jonat
 */
public class Game implements Runnable
{
    private Display display;
    
    private String title;
    private int playerId;
    private int width;
    private int height;
    
    private Thread thread;
    private boolean running = false;
    
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    
    private BufferedImage texture;
    private SpriteSheet sheet;
    
    private State gameState;
    
    private KeyManager keyManager;
    
    private Handler handler;
    
    public Game(String title, int playerId, int width, int height) 
    {
        this.title = title;
        this.playerId = playerId;
        this.width = width;
        this.height = height;
        
        keyManager = new KeyManager();
    }
    
    public KeyManager getKeyManager()
    {
        return this.keyManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
    
    private void init()
    {
        display = new Display( title, width, height );
        display.getFrame().addKeyListener( keyManager );
        
        Assets.init();
        
        handler = new Handler( this );
        
        gameState = new GameState( handler, this.playerId );
        State.setState(gameState);
    }
    
    private void tick()
    {
        keyManager.tick();
        
        if( State.getState() != null )
            State.getState().tick();
    }
    
    private void render()
    {
        bufferStrategy = display.getCanvas().getBufferStrategy();
        
        if( bufferStrategy == null )
        {
            display.getCanvas().createBufferStrategy( 3 );
            return;
        }
        
        graphics = bufferStrategy.getDrawGraphics();
                
        //clear
        graphics.clearRect(0, 0, width, height);
        
        //draw
        if( State.getState() != null )
            State.getState().render( graphics );
        
        //show
        bufferStrategy.show();
        graphics.dispose();
    }
    
    @Override
    public void run() 
    {
        init();
        
        int fps = 60;
        double timePerTick = 1000000000 / fps;
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
                ticks = 0;
                timer = 0;
            }
        }
        
        stop();
    }
}
