/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.ServerSocket;
import java.net.Socket;
import controller.PlayerController;
import util.Log;

/**
 *
 * @author jonat
 */
public class Server extends Thread
{
    public ServerSocket server = null;
    public Integer port = 5000;
    public boolean running = false;
 
    public Server()
    {
        init( this.port );       
    }
    
    public Server( int port ) 
    {   
        init( port );
    }  
    
    public void init( int port )
    {
        try
        {
            this.port = port;
            this.running = true;

            Log.info("iniciando servidor...");

            server = new ServerSocket( this.port );
            this.start();

            Log.info("servidor iniciado na porta: " +this.port+ "!");
            Log.info();
        } 
        catch( Exception e ) 
        {
            Log.erro(e);
        }
    }
    
    public void run() 
    {
        try 
        {
            while( running )
            {                
                Socket socket = server.accept();
                
                Player player = new Player( socket );

                Log.info("nova conexão! ip: " + player.getSocket().getInetAddress().getHostAddress() );
                
                PlayerController.getInstance().addPlayer(player);
            }
        }
        catch( Exception e ) 
        {
            Log.erro(e);
        }
    }
    
    public void close()
    {
        try
        {
            if( this.server != null )
                this.server.close();
        }
        catch( Exception e )
        {
            Log.erro(e);
        }
    }   
}