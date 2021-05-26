/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import util.Log;
import util.Util;

/**
 *
 * @author jonat
 */
public class DataPackage 
{
    private short length;
    private byte type;
    private short pieces;
    private short index;
    private byte[] body;
    
    public static int LENGTH_PACKAGE = 1024;
    public static int CONFIG_BYTES_SIZE = 7;
    
    public DataPackage(){}
            
    public DataPackage( byte type, short pieces, short index, byte[] body ) 
    {
        this.type = type;
        this.pieces = pieces;
        this.index = index;
        this.body = body;
    }

    public short getLength() 
    {
        return length;
    }

    public void setLength(short length) 
    {
        this.length = length;
    }
    
    public byte getType() 
    {
        return type;
    }

    public void setType(byte type) 
    {
        this.type = type;
    }

    public short getPieces() {
        return pieces;
    }

    public void setPieces(short pieces) 
    {
        this.pieces = pieces;
    }

    public short getIndex() {
        return index;
    }

    public void setIndex(short index) 
    {
        this.index = index;
    }

    public byte[] getBody() 
    {
        return body;
    }

    public void setBody(byte[] body) 
    {
        this.body = body;
    }
    
    
    public byte[] toBytes()
    {
        short length = (short) (body.length + CONFIG_BYTES_SIZE);
        
        byte[] lengthPackaget = Util.shortToByte( length );
        byte[] piecesBytes = Util.shortToByte(pieces);
        byte[] indexBytes = Util.shortToByte(index);
        
        // create
        byte[] bytes = new byte[LENGTH_PACKAGE];
        
        bytes[0] = lengthPackaget[0];
        bytes[1] = lengthPackaget[1];
        bytes[2] = type;
        bytes[3] = piecesBytes[0];
        bytes[4] = piecesBytes[1];
        bytes[5] = indexBytes[0];
        bytes[6] = indexBytes[1];
        
        for( short index = (short)CONFIG_BYTES_SIZE; index < length; index++ )
            bytes[index] = body[index-CONFIG_BYTES_SIZE];
        
        return bytes;
    }
    
    public static DataPackage read( byte[] bytes )
    {
        if( bytes.length != LENGTH_PACKAGE )
            return null;
        
        short length    = Util.bytesToShort( new byte[]{ bytes[0], bytes[1] } );
        byte type       = bytes[2];
        short pieces    = Util.bytesToShort( new byte[]{ bytes[3], bytes[4] } );
        short index     = Util.bytesToShort( new byte[]{ bytes[5], bytes[6] } );
        
        byte[] body = new byte[ length - CONFIG_BYTES_SIZE ];
        
        for( int i = CONFIG_BYTES_SIZE; i < length; i++ )
            body[i-CONFIG_BYTES_SIZE] = bytes[i];        
        
        DataPackage udp = new DataPackage();
        udp.setLength( length );
        udp.setType( type );
        udp.setPieces( pieces );
        udp.setIndex( index );
        udp.setBody( body );
        
        return udp;
    }
    
    public static List<DataPackage> createPackages( byte[] bytes )
    {
        List<DataPackage> packages = new ArrayList<>();
        
        byte type = 0;
        short pieces = 0;
        
        // get initial amount packages
        if( bytes.length % LENGTH_PACKAGE > 0 )
        {
            pieces = (short) ((bytes.length / LENGTH_PACKAGE) + 1);
        }
        else
        {
            pieces = (short) (bytes.length / LENGTH_PACKAGE);
        }
        
        // add coonfig bytes
        int totalLegth = (pieces * CONFIG_BYTES_SIZE ) + bytes.length;
        
        if( totalLegth % LENGTH_PACKAGE > 0 )
        {
            pieces = (short) ((totalLegth / LENGTH_PACKAGE) + 1);
        }
        else
        {
            pieces = (short) (totalLegth / LENGTH_PACKAGE);
        }
        
        for( short i = 0; i < pieces; i++ )
            packages.add( newPackage( type, pieces, i, bytes ) );
        
        return packages;
    }
    
    private static DataPackage newPackage( byte type, short pieces, short index, byte[] bytes )
    {
        int sizeBody = LENGTH_PACKAGE - CONFIG_BYTES_SIZE;
        
        if( index == (pieces - 1) )
            sizeBody = bytes.length - (sizeBody * (pieces - 1));
    
        byte[] body = new byte[ sizeBody ];
        
        for( int i = 0; i < sizeBody; i++ )
        {
            int bytesIndex = (index * sizeBody) + i;

            if( bytes.length > bytesIndex )
            {
                body[i] = bytes[ bytesIndex ];
            }
            else
            {
                break;
            }
        }

        return new DataPackage( type, pieces, index, body );  
    }
}
