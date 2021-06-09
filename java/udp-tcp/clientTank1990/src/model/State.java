/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics;

/**
 *
 * @author jonat
 */
public abstract class State
{
    private static State state = null;
    protected Handler handler;
    
    public static State getState() 
    {
        return state;
    }

    public static void setState( State state ) 
    {
        State.state = state;
    }
    
    public State( Handler handler )
    {
        this.handler = handler;
    }
    
    public abstract void tick();
    
    public abstract void render( Graphics graphics );
}
