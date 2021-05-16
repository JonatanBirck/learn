/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import model.Client;
import model.Game;
import view.AuthView;

/**
 *
 * @author jonat
 */
public class Laucher {

    /**
     * @param args the command line arguments
     */
    public static void main( String[] args ) 
    {
        Client client = new Client();
        
        AuthView authView = new AuthView( client );
        authView.setBounds(0, 0, 705, 620);
        authView.setVisible(true);
        authView.setResizable(false);
        authView.setLocationRelativeTo(null);
        
        /*
        Game game = new Game("Tank 1990", 1280, 765 );
        game.start();
        */
        

    }
}
