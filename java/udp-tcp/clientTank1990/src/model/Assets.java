/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;

/**
 *
 * @author jonat
 */
public class Assets 
{
    public static final int width = 64;
    public static final int height = 64;
    
    public static BufferedImage BOT;
    public static BufferedImage PLAYER;
    public static BufferedImage ENEMY;
    public static BufferedImage BRICK;
    public static BufferedImage STONE;
    public static BufferedImage GRASS;
    public static BufferedImage DIRT;

    public static void init()
    {             
        SpriteSheet sheet = new SpriteSheet( ImageLoader.loadImage("/textures/textures.png") );
        
        // materials
        BRICK   = sheet.crop(0, 0, width, height);
        STONE   = sheet.crop(width, 0, width, height);
        GRASS   = sheet.crop(width*2, 0, width, height);
        DIRT   = sheet.crop(width*3, 0, width, height);
        
        // players
        BOT     = ImageLoader.loadImage("/textures/white_tank.png");
        PLAYER  = ImageLoader.loadImage("/textures/yellow_tank.png");
        ENEMY   = ImageLoader.loadImage("/textures/red_tank.png");
        

    }
}
