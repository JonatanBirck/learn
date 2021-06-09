/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author jonat
 */
public class QueueExample 
{
    public static void main(String[] args) 
    {
        doExample1();
        doExample2();
    }
    
    public static void addContact( Queue queue, String contact )
    {
        System.out.println("Adicionando contato: " + contact );
        queue.enqueue( contact );
    }
    
    public static void removeContact( Queue queue )
    {
        System.out.println("Atendendo contato: " + queue.next() );
        queue.dequeue();
        System.out.println("Atendido!");
    }
    
    public static void doExample1()
    {
        System.out.println("Exemplo 01");
        System.out.println("");
        
        Queue<String> callCenter = new Queue<>();
        
        addContact( callCenter, "João" );
        addContact( callCenter, "Pedro" );
        addContact( callCenter, "Maria" );
        addContact( callCenter, "Luisa" );
        addContact( callCenter, "Jeferson" );
        addContact( callCenter, "Bruno" );
        
        int size = callCenter.size();
        
        for( int i = 0; i < size; i++ )
            removeContact( callCenter );
    }
    
    public static void doExample2()
    {
        System.out.println("Exemplo 02");
        System.out.println("");

        Queue<String> callCenter = new Queue<>();

        int contacts = 1;
        
        for( int i = 0; i < 20; i++ )
        {
            addContact( callCenter, "Contato " + (contacts++) );
            addContact( callCenter, "Contato " + (contacts++) );
            removeContact( callCenter );
        }

        int size = callCenter.size();
                
        for( int i = 0; i < size; i++ )
            removeContact( callCenter );
    }    
}
