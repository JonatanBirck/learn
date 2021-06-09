/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import util.Log;

public class Client 
{
    private DatagramSocket clientSocket = null;
    private InetAddress serverAddress = null;
    public static int port = 5001;

    public Client()
    {
        init( "192.168.0.105", this.port );
    }
    
    public Client( String serverAddress )
    {
        init( serverAddress, this.port );
    }
    
    public Client( String serverAddress, int port )
    {
        init( serverAddress, port );
    }
    
    private void init( String address, int port )
    {
        try
        {
            clientSocket = new DatagramSocket();
            serverAddress = InetAddress.getByName( address );
        }
        catch( Exception e )
        {
            Log.erro(e);
            System.exit(1);
        }
    }
    
    public void send( byte[] bytes )
    {              
        try
        {
            DatagramPacket datagramPacket = new DatagramPacket( bytes, bytes.length, this.serverAddress, this.port );
            clientSocket.send( datagramPacket );
        }
        catch( Exception e )
        {
            Log.erro(e);
            System.exit(1);
        }
    }  
    
    public void close()
    {
        try
        {
            clientSocket.close();
        }
        catch( Exception e )
        {
            Log.erro(e);
            System.exit(1);
        }  
    }
}