/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author jonat
 */
public class KeyManager implements KeyListener
{            
    private boolean[] keys;
   
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;    
    
    public KeyManager()
    {
        keys = new boolean[256];
    }
    
    public void tick()
    {
        up      = keys[ KeyEvent.VK_W ];
        down    = keys[ KeyEvent.VK_S ];
        left    = keys[ KeyEvent.VK_A ];
        right   = keys[ KeyEvent.VK_D ];
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
        keys[ e.getKeyCode() ] = false;
    }
    
}
