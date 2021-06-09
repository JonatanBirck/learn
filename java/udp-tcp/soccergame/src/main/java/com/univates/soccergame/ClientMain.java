package com.univates.soccergame;

import java.util.Scanner;

public class ClientMain 
{
    public static void main(String[] args) 
    {
        while( true )
        {
            String serverAddress = "192.168.0.106"; //local
            Client client = new Client( serverAddress );

            Scanner in = new Scanner(System.in);  

            System.out.println("Enter your move: ");  
    
            String name = in.nextLine(); 

            if( name != null && !name.isEmpty() )
            {
                if( name.startsWith("move(") && name.endsWith(")") ) 
                {
                    String response = client.sendMessage( name );
                    client.quit();
                }
                else
                {
                    System.out.println("Please, use move( ${player}, ${action} ) ");  
                }
            }
        }

    }
}
