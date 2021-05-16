/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import model.Handler;
import tiles.Tile;

/**
 *
 * @author jonat
 */
public abstract class Creature extends Entity 
{
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 2.0f;
    
    public static final int DEFAULT_CREATURE_WIDTH = 48;
    public static final int DEFAULT_CREATURE_HEIGHT = 48;
    
    protected int health;
    protected float speed;
    
    protected float moveX;
    protected float moveY;

    public Creature( Handler handler, float x, float y, int width, int height ) 
    {
	super( handler, x, y, width, height );
        
	health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        
        moveX = 0;
        moveY = 0;
    }
    
    public void move()
    {
        moveX();
        moveY();
        
    }
    
    public void moveX()
    {
        if( moveX > 0 ) // right
        {
            int futureMove = (int) ( x + moveX + bounds.x + bounds.width ) / Tile.TITLE_WIDTH;
            int tempYUp = (int) ( y + bounds.y ) / Tile.TITLE_HEIGHT;
            int tempYDown = (int) ( y + bounds.y + bounds.height ) / Tile.TITLE_HEIGHT;
            
            if( !collisionWithTile( futureMove, tempYUp ) && !collisionWithTile( futureMove, tempYDown ) )
            {
                x += moveX;
            }
            else
            {
                x = futureMove * Tile.TITLE_WIDTH - bounds.x - bounds.width - 1;
            }
        }
        else if( moveX < 0 ) // left
        {
            int futureMove = (int) ( x + moveX + bounds.x ) / Tile.TITLE_WIDTH;
            int tempYUp = (int) ( y + bounds.y ) / Tile.TITLE_HEIGHT;
            int tempYDown = (int) ( y + bounds.y + bounds.height ) / Tile.TITLE_HEIGHT;
            
            if( !collisionWithTile( futureMove, tempYUp ) && !collisionWithTile( futureMove, tempYDown ) )
            {
                x += moveX;
            }
            else
            {
                x = futureMove * Tile.TITLE_WIDTH + Tile.TITLE_WIDTH - bounds.x;
            }
        }
    }
    
    public void moveY()
    {
        if( moveY > 0 ) // down
        {
            int futureMove = (int) ( y + moveY + bounds.y + bounds.height ) / Tile.TITLE_HEIGHT;
            int tempXLeft = (int) ( x + bounds.x ) / Tile.TITLE_WIDTH;
            int tempXRight = (int) ( x + bounds.x + bounds.width ) / Tile.TITLE_WIDTH;
            
            if( !collisionWithTile( tempXLeft, futureMove ) && !collisionWithTile( tempXRight, futureMove ) )
            {
                y += moveY;
            }
            else
            {
                y = futureMove * Tile.TITLE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
        else if( moveY < 0 ) // up
        {
            int futureMove = (int) ( y + moveY + bounds.y ) / Tile.TITLE_HEIGHT;
            int tempXLeft = (int) ( x + bounds.x ) / Tile.TITLE_WIDTH;
            int tempXRight = (int) ( x + bounds.x + bounds.width ) / Tile.TITLE_WIDTH;
            
            if( !collisionWithTile( tempXLeft, futureMove ) && !collisionWithTile( tempXRight, futureMove ) )
            {
                y += moveY;
            }
            else
            {
                y = futureMove * Tile.TITLE_HEIGHT + Tile.TITLE_HEIGHT - bounds.y;
            }
        }
    }
    
    protected boolean collisionWithTile( int x, int y )
    {
        return handler.getWorld().getTile( x, y ).isSolid();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getMoveX() {
        return moveX;
    }

    public void setMoveX(float moveX) {
        this.moveX = moveX;
    }

    public float getMoveY() {
        return moveY;
    }

    public void setMoveY(float moveY) {
        this.moveY = moveY;
    }
}