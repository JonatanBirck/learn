/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import util.ImageUtil;
import util.Log;
import util.Util;
import view.ScreenView;

/**
 *
 * @author jonat
 */
public class Server extends Thread
{
    DatagramSocket serverSocket = null;
    public Integer port = 5001;
    public boolean running = false;
    
    byte[] receiveData = new byte[DataPackage.LENGTH_PACKAGE];
    
    int temp_packages = 0;
    ArrayList<DataPackage> temp_dataPackages = new ArrayList<>();
    ScreenView tempScreenView = null;
 
    public Server()
    {
        init( this.port );       
    }
    
    public Server( int port ) 
    {   
        init( port );
    }  

    public Server( ScreenView view ) 
    {   
        init( this.port );
        this.tempScreenView = view;
    }      
    
    public void init( int port )
    {
        try
        {
            this.port = port;
            this.running = true;

            Log.info("iniciando servidor...");

            serverSocket = new DatagramSocket( this.port );
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
                DatagramPacket receivePacket = new DatagramPacket( receiveData, DataPackage.LENGTH_PACKAGE );
                serverSocket.receive( receivePacket );
                
                // to refactor
                DataPackage dataPackage = DataPackage.read(receiveData);
                temp_dataPackages.add(dataPackage);
                
                temp_packages = dataPackage.getPieces();
                
                if( temp_dataPackages.size() == temp_packages )
                {
                    byte[] bytes = Util.dataPackagesToImageByte( temp_dataPackages );
                    
                    Log.info( bytes.length + "<--"  );
                    
                    tempScreenView.setBytes(bytes);
                    
                    temp_dataPackages = new ArrayList<>();
                }

                //Log.info("new package! ip: " + receivePacket.getAddress().getHostAddress() );
            }
        }
        catch( Exception e ) 
        {
            Log.erro(e);
            receiveData = new byte[DataPackage.LENGTH_PACKAGE];
        }
    }
    
    public void close()
    {
        try
        {
            if( this.serverSocket != null )
                this.serverSocket.close();
        }
        catch( Exception e )
        {
            Log.erro(e);
        }
    }   
}
