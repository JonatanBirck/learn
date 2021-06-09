/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Graphics;
import model.Assets;
import model.Client;
import model.Handler;
import org.json.JSONObject;

/**
 *
 * @author jonat
 */
public class Enemy extends Creature 
{
    private int id = 0;
    
    public Enemy( Handler handler, int id, float x, float y ) 
    {
	super( handler, id, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT );
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 48;
        bounds.height = 48;
        this.id = id;
    }

    @Override
    public void tick() 
    {
        getInput();
        move();
    }
    
    private void getInput()
    {
        // socket
        try
        {            
            JSONObject request = new JSONObject();
            request.put("method","getEnemy");
            request.put("id", this.id + "" );
            
            JSONObject response = Client.request(request);
            
            String position = (String) response.get("position");
            
            if( position != null  )
            {
                String[] positions = position.split(",");
                
                x = Float.parseFloat(positions[0]);
                y = Float.parseFloat(positions[1]);
            }     
        }
        catch( Exception e)
        {
            System.out.println("[ERRO] " + e.getMessage() );
        }        
    }

    @Override
    public void render( Graphics graphics ) 
    {        
	graphics.drawImage(Assets.ENEMY, (int) x, (int) y, width, height, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
