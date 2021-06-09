/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import util.ByteUtil;

/**
 *
 * @author jonat
 */
public class ImagePackage implements DataPackage<ImagePackage>
{
    private byte type;
    private short x;
    private short y;
    private byte[] body;
    
    public static short LENGTH_PACKAGE = 2048;
    private static short OFFSET = 5;
    
    public ImagePackage(){}
            
    public ImagePackage( byte type, short x, short y, byte[] body ) throws Exception 
    {
        this.type = type;
        this.x = x;
        this.y = y;
        
        if( body.length > (int) (LENGTH_PACKAGE - OFFSET) )
            throw new Exception("body exceeds the '"+(int) (LENGTH_PACKAGE - OFFSET)+"' byte limit");
        
        this.body = body;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public short getX() {
        return x;
    }

    public void setX(short x) {
        this.x = x;
    }

    public short getY() {
        return y;
    }

    public void setY(short y) {
        this.y = y;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
    
    @Override
    public List<ImagePackage> createPackages( byte[] bytes ) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImagePackage readPackage( byte[] bytes ) throws Exception 
    {
        if( bytes.length != (int) LENGTH_PACKAGE )
            throw new Exception("size is different from standard package, packate: " + LENGTH_PACKAGE + " bytes" );
        
        byte type       = bytes[0];
        short x         = ByteUtil.bytesToShort( new byte[]{ bytes[1], bytes[2] } );
        short y         = ByteUtil.bytesToShort( new byte[]{ bytes[3], bytes[4] } );
        
        byte[] body = new byte[ LENGTH_PACKAGE - OFFSET ];
        
        for( short i = 0; i < LENGTH_PACKAGE - OFFSET; i++ )
            body[i] = bytes[i + OFFSET];        
        
        ImagePackage imagePackage = new ImagePackage();
        imagePackage.setType( type );
        imagePackage.setX( x );
        imagePackage.setY( y );
        imagePackage.setBody( body );
        
        return imagePackage;
    }

    @Override
    public byte[] toBytes() 
    {        
        byte[] bytes = new byte[LENGTH_PACKAGE];
        
        byte[] xBytes = ByteUtil.shortToByte( this.x );
        byte[] yBytes = ByteUtil.shortToByte( this.y );
        
        // create
        bytes[0] = this.type;
        bytes[1] = xBytes[0];
        bytes[2] = xBytes[1];
        bytes[3] = yBytes[0];
        bytes[4] = yBytes[1];
        
        for( short index = 0; index < body.length ; index++ )
            bytes[index + OFFSET] = body[index];
        
        return bytes;
    }

    
       
}
