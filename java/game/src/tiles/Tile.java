/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author jonat
 */
public class Tile 
{
    public static Tile[] tiles = new Tile[256];
    public static Tile dirtTile  = new DirtTile( 0 );
    public static Tile grassTile = new GrassTile( 1 );
    public static Tile stoneTile = new StoneTile( 2 );
    public static Tile brickTile = new BrickTile( 3 );
    
    
    public static final int TITLE_WIDTH = 64;
    public static final int TITLE_HEIGHT = 64;
    
    protected BufferedImage texture;
    protected final int id;
    
    public Tile( BufferedImage texture, int id )
    {
        this.texture = texture;
        this.id = id;
        
        tiles[id] = this;
    }
    
    public void tick()
    {
        
    }
    
    public void render( Graphics graphics, int x, int y )
    {
        graphics.drawImage( texture, x, y, TITLE_WIDTH, TITLE_HEIGHT, null );
    }
    
    public boolean isSolid()
    {
        return false;
    }
    
    public int getId()
    {
        return this.id;
    }
}
