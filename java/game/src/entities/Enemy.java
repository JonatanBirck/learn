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
public class Enemy extends Creature 
{
    public Enemy( Handler handler, float x, float y ) 
    {
	super( handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT );
    }

    @Override
    public void tick() {}

    @Override
    public void render( Graphics graphics ) 
    {
	graphics.drawImage(Assets.ENEMY, (int) x, (int) y, null);
    }
}
