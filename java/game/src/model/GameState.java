/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Player;
import java.awt.Graphics;
import tiles.Tile;

/**
 *
 * @author jonat
 */
public class GameState extends State 
{
    private World world;
    private Player player;
    
    public GameState( Handler handler ) 
    {
        super( handler );
        world = new World( handler, "res/worlds/world_01.txt");
        handler.setWorld(world);
        player = new Player( handler, 70, 70 );
    }
	
    @Override
    public void tick() 
    {
        world.tick();
        player.tick();
    }

    @Override
    public void render( Graphics graphics ) 
    {
        world.render( graphics );
	player.render( graphics );
    }
}