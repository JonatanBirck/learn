/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics;
import tiles.Tile;

/**
 *
 * @author jonat
 */
public class World 
{
    private Handler handler;
    
    private int width;
    private int height;
    
    private int spawnX;
    private int spawnY;
    
    private int[][] tiles;
    
    public World( Handler handler, String path )
    {
        this.handler = handler;
        loadWorld( path );
    }
    
    public void tick()
    {
        
    }
    
    public void render( Graphics graphics )
    {
        for( int x = 0; x < width; x++ )
            for( int y = 0; y < height; y++ )
                getTile( x, y ).render(graphics, x * Tile.TITLE_WIDTH, y * Tile.TITLE_HEIGHT);
    }
    
    public Tile getTile( int x, int y )
    { 
        Tile tile = Tile.tiles[ tiles[x][y] ];
        
        if( tile == null )
            return Tile.dirtTile;
            
        return tile;
    }
    
    private void loadWorld( String path )
    {
        String file = Utils.loadFileAsString( path );
        String[] lines = file.split( "\\n" );
        
        String[] dimension = lines[0].split(",");
        String[] spawner = lines[1].split(",");
        String[] world = lines[2].split(",");
        
        width = Utils.parseInt( dimension[0] );
        height = Utils.parseInt( dimension[1] );
        
        spawnX = Utils.parseInt( spawner[0] );
        spawnY = Utils.parseInt( spawner[1] );
        
        tiles = new int[width][height];
        
        for( int y = 0; y < height; y++ )
            for( int x = 0; x < width; x++ )
                tiles[x][y] = Utils.parseInt( world[ x + y * width] );
    }
}
