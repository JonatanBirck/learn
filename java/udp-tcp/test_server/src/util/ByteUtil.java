/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.nio.ByteBuffer;
import java.util.List;
import model.DataImagePackage;

/**
 *
 * @author jonat
 */
public class ByteUtil 
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
    
    
    
}
