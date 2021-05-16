import model.User;
import views.ScreenView;

public class Laucher
{
    public static void main( String[] args )
    {
        try
        {
            //new MainView( new User() );

            ScreenView screen = new ScreenView( new User(), "titulo", 1280, 720 );
            screen.start();
        }
        catch( Exception e )
        {
            System.out.println( "[ERROR] Problems in main method: " + e.getMessage() );
        }
    }
}
