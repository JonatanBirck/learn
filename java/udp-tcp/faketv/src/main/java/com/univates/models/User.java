package com.univates.models;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.UUID;

public class User 
{
    private String name;
    private InetAddress ip;
    private String password;


    public User() 
    {
        init( null );
    }

    public User( String name ) 
    {
        init( name );
    }

    private void init( String name )
    {
        try 
        {
            // name
            if( name != null && !name.isEmpty() )
            {
                this.name = name;
            }
            else
            {
                this.name = "Guest";
            }

            // ip
            this.ip = getIP();

            // password
            this.password = UUID.randomUUID().toString().split("-")[0];
        } 
        catch( SocketException e ) 
        {
            System.out.println("[ERRO] Problemas ao criar usuário: " + e.getMessage() );
        }
    }

    private static InetAddress getIP() throws SocketException 
    { 
        Enumeration enumeration = NetworkInterface.getNetworkInterfaces(); 
        
        while( enumeration.hasMoreElements() ) 
        { 
            NetworkInterface i = (NetworkInterface) enumeration.nextElement(); 
            
            for( Enumeration en2 = i.getInetAddresses(); en2.hasMoreElements(); )
            { 
                InetAddress address = (InetAddress) en2.nextElement(); 
                
                if (!address.isLoopbackAddress()) 
                { 
                    if (address instanceof Inet4Address) 
                    {                       
                        return address; 
                    } 
                } 
            } 
        } 
        
        return null; 
    }
    
    public String getName() 
    {
        return this.name;
    }

    public void setName( String name ) 
    {
        this.name = name;
    }

    public InetAddress getIp() 
    {
        return this.ip;
    }

    public String getPassword() 
    {
        return this.password;
    }

    public void setPassword( String password ) 
    {
        this.password = password;
    }

    public void changePassword()
    {
        this.password = UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public String toString() 
    {
        return "{name='" + getName() + "'}";
    }

}
