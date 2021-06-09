package Model;

import Util.Log;

import java.net.*;

public class Client
{
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;

    public Client()
    {
        try
        {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
        }
        catch( SocketException | UnknownHostException e )
        {
            Log.erro( e );
        }
    }

    public String sendEcho( String msg )
    {
        buf = msg.getBytes();

        DatagramPacket packet = new DatagramPacket( buf, buf.length, address, 4445);

        socket.send( packet );

        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(
                packet.getData(), 0, packet.getLength());
        return received;
    }

    public void close() {
        socket.close();
    }
}
