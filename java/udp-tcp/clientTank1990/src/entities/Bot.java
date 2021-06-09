/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Graphics;
import model.Assets;
import model.Handler;

/**
 *
 * @author jonat
 */
public class Bot extends Creature 
{
    public Bot( Handler handler, int id, float x, float y ) 
    {
	super( handler, id, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT );
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 48;
        bounds.height = 48;        
    }

    @Override
    public void tick() {}

    @Override
    public void render( Graphics graphics ) 
    {
	graphics.drawImage(Assets.BOT, (int) x, (int) y, null);
    }
}
