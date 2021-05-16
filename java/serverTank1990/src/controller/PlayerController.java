/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Player;
import org.json.JSONObject;
import util.Log;

/**
 *
 * @author jonat
 */
public class PlayerController {
    
    private static PlayerController instance = null;
    private String DEFAULT_PASSWORD_SERVER = "senha123";
    public List<Player> players = new ArrayList<>();
    
    public static PlayerController getInstance() {
	if(instance == null) {
            instance = new PlayerController();
        }
        return instance;
    }
   
    public JSONObject command( Player player, JSONObject request )
    {
        JSONObject response = new JSONObject();
        
        try
        {
            if( !player.isAuth() )
                return auth( player, request, response );
            
            String method = (String) request.get("method");
            
            switch( method )
            {
                case "update": 
                    return updatePosition( player, request, response );
                case "getEnemy":
                    return getEnemy( player, request, response );
                case "getEnemies":
                    return getEnemies( player, request, response );                    
            }
        }
        catch( Exception e )
        {
            Log.erro(e);
        }
        
        return response;
    }
    
    private JSONObject getEnemies( Player player, JSONObject request, JSONObject response )  throws Exception
    {
        String enemies = "";
        
        for( Player enemy : players )
        {
            if( enemy.getId() != player.getId() )
            {
                enemies += enemies.isEmpty() ? enemy.getId() + "" : "," + enemy.getId();
            }
        }

        response.put("data", enemies );
        
        return response;
    }
    
    private JSONObject getEnemy( Player player, JSONObject request, JSONObject response )  throws Exception
    {
        int id = Integer.parseInt((String) request.get("id"));
        
        for( Player enemy : players )
        {
            if( enemy.getId() == id )
            {
                response.put("position", enemy.getX() + "," + enemy.getY() );
                
                return response;
            }
        }
        
        return response;        
    }
    
    private JSONObject updatePosition( Player player, JSONObject request, JSONObject response )  throws Exception
    {
        String[] positions = ((String) request.get("position")).split(",");
        
        player.setX( Float.parseFloat( positions[0] ) );
        player.setY( Float.parseFloat( positions[1] ) );
                
        return response;
    }

    private JSONObject auth( Player player, JSONObject request, JSONObject response ) throws Exception
    {        
        String userName = (String) request.get("username");
        String password = (String) request.get("password");
        
        if( !password.equals(DEFAULT_PASSWORD_SERVER) )
        {
            response.put("status", "fail");
            response.put("message", "senha incorreta!");
            return response;
        }
            
        if( userName.length() < 4 )
        {
            response.put("status", "fail");
            response.put("message", "nome muito curto!");
            return response;
        }
        
        player.setAuth(true);
        player.setName(userName);
        
        response.put("status", "ok");
        response.put("message", "jogador '" + userName + "' autenticado!");
        response.put("data", player.getId() + "");
        
        Log.info("jogador '"+ userName + "(" + player.getId() + ")' autenticado!");
        
        return response;
    }
    
    public void addPlayer( Player player )
    {
        Integer id = players.size() + 1;
       
        player.setId( id );
       
        players.add( player );
    }
    
    public void removePlayer( int id )
    {
        players.remove(id);
    }
}
