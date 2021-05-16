/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import model.Game;

/**
 *
 * @author jonat
 */
public class Laucher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game("Tank 1990", 1280, 765 );
        game.start();
    }
}
