/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author jonat
 */
public class Utils 
{
    public static String loadFileAsString( String path )
    {
        StringBuilder builder = new StringBuilder();
        
        try
        {
            BufferedReader bufferedReader = new BufferedReader( new FileReader( path ) );
            String line;
            
            while( ( line = bufferedReader.readLine() ) != null )
                builder.append( line + "\n" );
            
        }
        catch( IOException e )
        {
            System.out.println("[ERRO] " + e.getMessage() );
        }
        
        return builder.toString();
    }
    
    public static int parseInt( String number )
    {
        try
        {
           return Integer.parseInt( number ) ;
        }
        catch( NumberFormatException e )
        {
            System.out.println("[ERRO] " + e.getMessage() );
            return 0;
        }
    }
}
