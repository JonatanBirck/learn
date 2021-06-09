package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;

@Getter
@Setter
@ToString
public class User
{
    private String name;
    private InetAddress ip;
    private String password;

    public User()
    {
        this.ip = getLocalAddress();
        this.name = ip.getHostName();
        changePassword();
    }

    private static InetAddress getLocalAddress()
    {
        try
        {
            Enumeration enumeration = NetworkInterface.getNetworkInterfaces();

            while ( enumeration.hasMoreElements() )
            {
                NetworkInterface i = (NetworkInterface) enumeration.nextElement();

                for ( Enumeration en2 = i.getInetAddresses(); en2.hasMoreElements(); )
                {
                    InetAddress address = (InetAddress) en2.nextElement();

                    if ( !address.isLoopbackAddress() )
                        if (address instanceof Inet4Address)
                            return address;
                }
            }

            return null;
        }
        catch ( SocketException e )
        {
            System.out.println( "[ERROR] Problems when getting IP: " + e.getMessage() );
        }

        return null;
    }

    public void changePassword()
    {
        this.password = UUID.randomUUID().toString().split("-")[0];
    }
}
