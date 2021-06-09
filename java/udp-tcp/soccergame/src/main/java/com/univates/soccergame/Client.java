package com.univates.soccergame;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Objects;

public class Client 
{
    private int door = 8000;
    private byte[] buffer;
    private DatagramSocket socket = null;
    private InetAddress address = null;

    public Client() 
    {
        try
        {
            this.socket = new DatagramSocket();
            this.address = InetAddress.getByName("localhost");
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public Client( String address ) 
    {
        try
        {
            this.socket = new DatagramSocket();
            this.address = InetAddress.getByName( address );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public String sendMessage( String message ) 
    {
        String response = "";

        try
        {
            buffer = message.getBytes();

            DatagramPacket packet = new DatagramPacket( buffer, buffer.length, address, door );
            socket.send( packet );

            buffer = new byte[1024];

            DatagramPacket received = new DatagramPacket( buffer, buffer.length, address, door );
            socket.receive( received );

            response = new String( received.getData(), 0, received.getLength() );

            System.out.println("[INFO] [CLIENT] diz: " + message );
            System.out.println("[INFO] [SERVER] diz: " + response );
            System.out.println("[INFO]");
        }
        catch(  Exception e )
        {
            e.printStackTrace();
        }

        return response;
    }

    public int getDoor() {
        return this.door;
    }

    public void setDoor(int door) {
        this.door = door;
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public DatagramSocket getSocket() {
        return this.socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public InetAddress getAddress() {
        return this.address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public void quit() {
        this.socket.close();
    }

    public Client door(int door) {
        setDoor(door);
        return this;
    }

    public Client buffer(byte[] buffer) {
        setBuffer(buffer);
        return this;
    }

    public Client socket(DatagramSocket socket) {
        setSocket(socket);
        return this;
    }

    public Client address(InetAddress address) {
        setAddress(address);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Client)) {
            return false;
        }
        Client echoClient = (Client) o;
        return door == echoClient.door && Objects.equals(buffer, echoClient.buffer) && Objects.equals(socket, echoClient.socket) && Objects.equals(address, echoClient.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(door, buffer, socket, address);
    }

    @Override
    public String toString() {
        return "{" +
            " door='" + getDoor() + "'" +
            ", buffer='" + getBuffer() + "'" +
            ", socket='" + getSocket() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}