package Model;

import Util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server extends Thread
{
    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[512];
    private int port = 5001;

    public Server()
    {
        try
        {
            socket = new DatagramSocket( port );
        }
        catch( SocketException e )
        {
            Log.erro( e );
        }
    }

    public void run()
    {
        running = true;

        while ( running )
        {
            try
            {
                DatagramPacket packet = new DatagramPacket( buf, buf.length );

                socket.receive( packet );

                InetAddress address = packet.getAddress();

                int port = packet.getPort();

                packet = new DatagramPacket(buf, buf.length, address, port);

                String received = new String( packet.getData(), 0, packet.getLength());

                if( received.equals("end") )
                {
                    running = false;
                    continue;
                }

                socket.send( packet );
            }
            catch( Exception e )
            {
                Log.erro( e );
            }
        }

        socket.close();
    }
}
