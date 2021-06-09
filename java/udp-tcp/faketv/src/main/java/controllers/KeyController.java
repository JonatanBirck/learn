package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener
{
    private boolean[] keys;

    public KeyController()
    {
        keys = new boolean[256];
    }

    public void tick()
    {

    }

    @Override
    public void keyTyped( KeyEvent e )
    {
        // nothing
    }

    @Override
    public void keyPressed( KeyEvent e )
    {
        keys[ e.getKeyCode() ] = true;
    }

    @Override
    public void keyReleased( KeyEvent e )
    {
        // nothing
    }
}
