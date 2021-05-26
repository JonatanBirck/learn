/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.PlayerController;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import org.json.JSONObject;
import util.Log;

/**
 *
 * @author jonat
 */
public class Player extends Thread {
    
    private int id = 0;
    private String userName = "unnamed";
    private String password = "";
    private boolean online = false;
    private boolean auth = false;
    
    private Socket socket = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    
    private float x = 0;
    private float y = 0;
    
    public Player( Socket socket )
    {
        try
        {
            Log.info("novo jogador!");
            
            this.socket = socket;
            this.online = true;

            out = new ObjectOutputStream( this.socket.getOutputStream() );
            in = new ObjectInputStream( this.socket.getInputStream() );

            this.start();
        }
        catch( Exception e )
        {
            System.out.println("DEU BOSTA: " + e.getMessage() );
        }
    }
    
    public void run()
    {
        while( online )
        {
            try
            {   
                //request
                String json = ((String) in.readObject()).trim();
                
                JSONObject request = new JSONObject( json );                
                
                // do stuff
                JSONObject response = PlayerController.getInstance().command( this, request );
                
                //response
                out.flush();
                out.writeObject( response.toString() );
            }
            catch( Exception e )
            {
                Log.erro(e, "problemas na conexão!");
                disconect();
            }
        }
    }
   
    public void disconect()
    {
        try
        {
            Log.info("jogador desconectado!");
            this.online = false;
            out.close();
            in.close();
            socket.close();
        }
        catch( Exception e )
        {
            Log.erro(e, "problemas ao encerar conexão!");
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }  

    public int getIdPlayer() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}