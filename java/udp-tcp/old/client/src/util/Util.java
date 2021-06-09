/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.nio.ByteBuffer;
import java.util.List;
import model.DataPackage;

/**
 *
 * @author jonat
 */
public class Util 
{
    public static byte[] shortToByte( short number )
    {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort(number);
        return buffer.array();
    }
   
    public static short bytesToShort( byte[] bytes )
    {        
        return ByteBuffer.wrap(bytes).getShort();
    }
    
    public static byte[] intToByte( int number )
    {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(number);
        return buffer.array();
    }
    
    public static int bytesToInt( byte[] bytes )
    {
        return ByteBuffer.wrap(bytes).getInt();
    }
    
    public static byte[] dataPackagesToImageByte( List<DataPackage> dataPackages )
    {   
        int total_bytes = 0;
        
        short pieces = dataPackages.get(0).getPieces();
        
        ByteBuffer buffer = ByteBuffer.allocate( ( pieces * DataPackage.LENGTH_PACKAGE ) );
        
        short index = 0;
        
        while( index < pieces )
        {
            for( DataPackage dataPackage : dataPackages )
            {
                if( dataPackage.getIndex() == index )
                {
                    byte[] packageBytes = dataPackage.getBody();
                            
                    buffer.put( packageBytes );
                    
                    total_bytes += packageBytes.length;
                    
                    index++;
                    break;
                }
            }
        }
        
        byte[] bytes = new byte[ total_bytes ];
        
        for( int i = 0; i < total_bytes; i++ )
            bytes[i] = buffer.get(i);

        return bytes;
    }
}
