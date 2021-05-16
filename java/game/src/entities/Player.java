/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Color;
import java.awt.Graphics;
import model.Assets;
import model.Handler;

/**
 *
 * @author jonat
 */
public class Player extends Creature 
{    
    public Player( Handler handler, float x, float y ) 
    {
	super( handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT );
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 48;
        bounds.height = 48;
    }

    @Override
    public void tick() 
    {
        getInput();
        move();
    }
    
    private void getInput()
    {
        moveX = 0;
        moveY = 0;
        
        if( handler.getKeyManager().up )
            moveY = -speed;
        
        if( handler.getKeyManager().down )
            moveY = speed;
        
        if( handler.getKeyManager().left )
            moveX = -speed;
        
        if( handler.getKeyManager().right )
            moveX = speed;        
    }

    @Override
    public void render( Graphics graphics ) 
    {
	graphics.drawImage(Assets.PLAYER, (int) x, (int) y, width, height, null);
        //graphics.setColor(Color.red);
        //graphics.fillRect( (int) x + bounds.x, (int) y + bounds.y, bounds.width, bounds.height );
    }
}
