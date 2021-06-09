/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Enemy;
import entities.Player;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import tiles.Tile;

/**
 *
 * @author jonat
 */
public class GameState extends State 
{
    private World world;
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    
    public GameState( Handler handler, int id ) 
    {
        super( handler );
        world = new World( handler, "res/worlds/world_01.txt");
        handler.setWorld(world);
        
        player = new Player( handler, id, 70, 70 );        
    }
	
    @Override
    public void tick() 
    {
        // game        
        world.tick();
        player.tick();
        
        // socket
        try
        {                      
            JSONObject request = new JSONObject();
            request.put("method","getEnemies");
            
            JSONObject response = Client.request( request );
            
            String data = (String) response.get("data");
            
            if( data != null && !data.isEmpty() )
            {
                String[] countEnemies = data.split(",");
                
                if( countEnemies.length > enemies.size() )
                {
                    ArrayList<Integer> checkEnemies = new ArrayList<Integer>();
                    
                    for( String checkEnemy : countEnemies )
                    {
                        int id = Integer.parseInt( checkEnemy );
                        
                        checkEnemies.add( id );
                    }
                    
                    // tirar
                    for( Enemy enemy : enemies )
                    {
                        if( checkEnemies.contains( enemy.getId() ) )
                            enemies.remove( enemy );
                    }
                    
                    // colocar
                    for( int checkId : checkEnemies )
                    {
                        boolean contain = false;
                        
                        for( Enemy enemy : enemies )
                        {
                            if( enemy.getId() == checkId )
                                contain = true;
                        }
                        
                        if( !contain )
                        {
                            Enemy enemy = new Enemy( handler, checkId, 70, 70 );
                            enemies.add( enemy );                            
                        }
                    }
                }                
            }
        }
        catch( Exception e)
        {
            System.out.println("[ERRO] " + e.getMessage() );
        } 

        for( Enemy enemy : enemies )
        {
            enemy.tick();
        }            
    }

    @Override
    public void render( Graphics graphics ) 
    {
        world.render( graphics );
	player.render( graphics );
        
        
        // socket
        for( Enemy enemy : enemies )
        {
            enemy.render( graphics );
        }    
    }
}