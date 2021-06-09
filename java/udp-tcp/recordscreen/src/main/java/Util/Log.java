package Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log
{
    private static final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private static void log( String prefix, String message )
    {
        String now = DATA_FORMAT.format( new Date( System.currentTimeMillis() ) );
        System.out.println( now + " " + prefix + " " + message );
    }

    public static void info()
    {
        log( "INFO:", "" );
    }

    public static void info( String message )
    {
        log( "INFO:", message );
    }

    public static void warn()
    {
        log( "WARN:", "" );
    }

    public static void warn( String message )
    {
        log( "WARN:", message );
    }

    public static void erro()
    {
        log( "ERRO:", "" );
    }

    public static void erro( String message )
    {
        log( "ERRO:", message );
    }

    public static void erro( Exception exception )
    {
        log( "ERRO:", exception.getMessage() );
    }

    public static void erro( Exception exception, String message )
    {
        log( "ERRO:", message + " " + exception.getMessage() );
    }
}
