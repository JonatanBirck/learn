/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import org.json.JSONObject;
import util.Log;

public class Client 
{
    public static Socket socket = null;
    public static int port = 5000;
    public static ObjectInputStream in = null;
    public static ObjectOutputStream out = null;

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
    
    private void init( String serverAddress, int port )
    {
        try
        {
            socket = new Socket( serverAddress, port );
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        }
        catch( Exception e )
        {
            Log.erro(e);
            System.exit(1);
        }
    }

    public static JSONObject request( JSONObject request ) throws Exception
    {      
        out.writeObject( request.toString() );
        
        String json = (String) in.readObject();
        return new JSONObject( json );
    }
    
    public void close()
    {
        try
        {
            out.close();
            in.close();
            socket.close();
        }
        catch( Exception e )
        {
            Log.erro(e);
            System.exit(1);
        }  
    }
}