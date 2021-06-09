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
public class StackExample 
{
    public static void main(String[] args) 
    {
        doExample1();
        doExample2();
    }
    
    public static void addTask( Stack stack, String task )
    {
        System.out.println("Adicionando atividade: " + task );
        stack.push( task );
    }
    
    public static void removeTask( Stack stack )
    {
        System.out.println("Resolvendo atividade: " + stack.lastElement() );
        stack.pop();
        System.out.println("Resolvido!");
    }
    
    public static void doExample1()
    {
        System.out.println("Exemplo 01");
        System.out.println("");
        
        Stack<String> activities = new Stack<>();
        
        for( int i = 0; i < 15; i++ )
            addTask( activities, "Atividade " + (i+1) );
        
        int sizeActivities = activities.size();
                
        for( int i = 0; i < sizeActivities; i++ )
            removeTask( activities ); 
    }
    
    public static void doExample2()
    {
        System.out.println("Exemplo 02");
        System.out.println("");
        
        Stack<String> activities = new Stack<>();
        
        int task = 1;
        
        for( int i = 0; i < 10; i++ )
        {
            addTask( activities, "Atividade " + task++ );
            addTask( activities, "Atividade " + task++ );
            removeTask( activities );         
        }
        
        int sizeActivities = activities.size();
                
        for( int i = 0; i < sizeActivities; i++ )
            removeTask( activities );
    }
   
    
}
