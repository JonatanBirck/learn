/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univates.soccergame;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author wolfi
 */
public class MultiGame {

    private static final int MAX_PAUSA = 500;
    private static final int MAX_COL = 50;
    private static final int MAX_LIN = 20;
    private static final char SIM_LIVRE = '.';
    private static final char SIM_BOLA = '@';
    private static final int NUN_PLAYERS = 2;

    private static final int PORTA = 8000;

    private static char campo[][] = new char[MAX_LIN][MAX_COL];

    private static char players[] = new char[NUN_PLAYERS * 2];

    private static DatagramSocket socket = null;
    private static DatagramPacket packet = null;

    private static boolean gameRunning = true;

    private static void inicializaJogo() {
        for (int i = 0; i < MAX_LIN; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                campo[i][j] = SIM_LIVRE;
            }
        }

        // simobols iniciais dos times
        char vetPos[] = {'A', 'a'};

        // sortear posicao inicial dos jogadores
        for (int p = 0; p < 2; p++) {
            for (int i = 0; i < NUN_PLAYERS; i++) {  // time 1
                players[i] = (char) (vetPos[p] + i);

                int lin = -1;
                int col = -1;
                do {
                    lin = (int) (Math.random() * MAX_LIN);
                    col = (int) (Math.random() * MAX_COL);

                } while (campo[lin][col] != SIM_LIVRE);
                campo[lin][col] = players[i];
            }
        }

        // posicao inicial da bola
        int lin = -1;
        int col = -1;
        do {
            lin = (int) (Math.random() * MAX_LIN);
            col = (int) (Math.random() * MAX_COL);

        } while (campo[lin][col] != SIM_LIVRE);
        campo[lin][col] = SIM_BOLA;

        try {

            socket = new DatagramSocket(PORTA);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void limpaTela() {
        /*
        for (int i = 0; i < 25; i++) {
            System.out.println("");
        }
        */
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    private static void mostraCampo() {
        
        limpaTela();

        String console = "";

        console += "\n";
        
        for (int i = 0; i < MAX_COL; i++)
            console += "=";
        
        for (int i = 0; i < MAX_LIN; i++) 
        {
            console += "\n";

            for (int j = 0; j < MAX_COL; j++)
                console += (campo[i][j]);
        }

        console += "\n";

        for (int i = 0; i < MAX_COL; i++)
            console += "=";

        System.out.println( console );
        
        /*
        try {
            Thread.sleep(MAX_PAUSA);
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
    }

    public static void movePlayer(char player, char movimento) {

        int l = getLinPlayer(player);
        int c = getColPlayer(player);

        // movimento para cima
        if (movimento == 'w' || movimento == 'W') {
            if (l > 0 && l < MAX_LIN )
            {
                if (campo[l - 1][c] == SIM_LIVRE) {
                    // swap, troca um livre pelo simbolo
                    char aux = campo[l][c];
                    campo[l][c] = SIM_LIVRE;
                    campo[l - 1][c] = aux;
                } else {
                    // colocar a logica de movimentar a bola
                    if (campo[l - 1][c] == SIM_BOLA) {
                        if( (l - 2) < 0 )
                        {
                            win("A");
                        } 
                        else if( campo[l - 2][c] == SIM_LIVRE )
                        {
                            char aux = campo[l][c];
                            campo[l][c] = SIM_LIVRE;
                            campo[l - 1][c] = aux;
                            campo[l - 2][c] = SIM_BOLA;
                        }
                    }
                }
            }
        }

        // movimento para baixo
        if (movimento == 's' || movimento == 'S') {
            if (l > 0 && l < MAX_LIN ) 
            {
                if (campo[l + 1][c] == SIM_LIVRE) {
                    // swap, troca um livre pelo simbolo
                    char aux = campo[l][c];
                    campo[l][c] = SIM_LIVRE;
                    campo[l + 1][c] = aux;
                } else {
                    // colocar a logica de movimentar a bola
                    if (campo[l + 1][c] == SIM_BOLA) {
                        if( (l + 2) > (MAX_LIN - 1) )
                        {
                            win("a");
                        } 
                        else if( campo[l + 2][c] == SIM_LIVRE )
                        {
                            char aux = campo[l][c];
                            campo[l][c] = SIM_LIVRE;
                            campo[l + 1][c] = aux;
                            campo[l + 2][c] = SIM_BOLA;
                        }
                    }
                }
            }
        }

        // movimento para direita
        if (movimento == 'd' || movimento == 'D') {
            if (c > 0 && c < (MAX_COL-1) ) {
                if (campo[l][c + 1] == SIM_LIVRE) {
                    // swap, troca um livre pelo simbolo
                    char aux = campo[l][c];
                    campo[l][c] = SIM_LIVRE;
                    campo[l][c + 1] = aux;
                } else {
                    // colocar a logica de movimentar a bola
                    if (campo[l][c + 1] == SIM_BOLA) {
                        if( campo[l][c + 2] == SIM_LIVRE )
                        {
                            char aux = campo[l][c];
                            campo[l][c] = SIM_LIVRE;
                            campo[l][c + 1] = aux;
                            campo[l][c + 2] = SIM_BOLA;
                        }
                    }
        
                }
            }
        }

        // movimento para esquerda
        if (movimento == 'a' || movimento == 'A') {
            if (c > 0 && c < (MAX_COL-1) ) 
            {
                if (campo[l][c - 1] == SIM_LIVRE) {
                    // swap, troca um livre pelo simbolo
                    char aux = campo[l][c];
                    campo[l][c] = SIM_LIVRE;
                    campo[l][c - 1] = aux;
                } else {
                    // colocar a logica de movimentar a bola
                    if (campo[l][c - 1] == SIM_BOLA) {
                        if( campo[l][c - 2] == SIM_LIVRE )
                        {
                            char aux = campo[l][c];
                            campo[l][c] = SIM_LIVRE;
                            campo[l][c - 1] = aux;
                            campo[l][c - 2] = SIM_BOLA;
                        }
                    }
                }
            }
        }

        // player, simbolo valido dos jogadores no campo
        // movimentos:
        //         w ou W --> para cima
        //         x ou X --> para baixo
        //         a ou A --> para esquerda
        //         d ou D --> para direita
        //         sempre que for para cima da bola ela vai para o mesmo lado do
        //         movimento, no caso da latera, atravessa e começa no outro lado
        //         sempre que chegar a um extremo, time inverso ganha.
        // aqui será recebida um pacote UDP com a movimentação de determinado jogador
    }

    public static void aguardaPlayer() {
        try 
        {
            byte[] buffer = new byte[50];

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            InetAddress client = packet.getAddress();
            int clientPort = packet.getPort();

            String efetiva = new String(buffer, 0, packet.getLength());

            byte[] bufferResponse = "ok".getBytes();

            DatagramPacket packet2 = new DatagramPacket( bufferResponse, bufferResponse.length, packet.getAddress(), packet.getPort() );
            socket.send( packet2 );
            
            if (efetiva.startsWith("move(") && efetiva.endsWith(")")) {
                
                efetiva = efetiva.replace("move(","").replace(")","").replace(",","").trim();
                
                movePlayer( efetiva.toCharArray()[0], efetiva.toCharArray()[1] );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getColPlayer(char player) {
        int pos = -1;
        for (int i = 0; i < MAX_LIN; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                if (campo[i][j] == player) {
                    pos = j;
                    i = MAX_LIN;  // matar o loop externo rapidamente ou usar return
                    break;
                }
            }
        }
        return pos;
    }

    public static int getLinPlayer(char player) {
        int pos = -1;
        for (int i = 0; i < MAX_LIN; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                if (campo[i][j] == player) {
                    pos = i;
                    j = MAX_COL;  // matar o loop externo rapidamente ou usar return
                    break;
                }
            }
        }
        return pos;
    }

    public static void getColBola() {
        // retorna a posicao do jogador no tabuleiro
    }

    public static void getLinBola() {
        // retorna a posicao do jogador no tabuleiro
    }

    private static void win( String time )
    {
        gameRunning = false;
        limpaTela();
        System.out.print("TIME " + time + " GANHOU!");
    }

    public static void main(String[] args) 
    {
        inicializaJogo();

        while( gameRunning ) 
        {           
            mostraCampo();
            
            aguardaPlayer();
        }
    }
}