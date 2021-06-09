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
public class Stack<T>
{
    private T[] elements;
    private final int DEFAULT_CAPACITY = 10;
    private int size = 0;
            
    public Stack() 
    {
        elements = (T[]) new Object[ this.DEFAULT_CAPACITY ];
    }

    public void push( T element )
    {
        if( this.size == this.elements.length )
            grow();
        
        this.elements[ this.size ] = element;
        this.size++;
    }
    
    public void pop()
    {
        if( this.size() > 0)
            this.size--;
    }
    
    public T lastElement()
    {
        return this.elements[ this.size - 1];
    }
    
    public int size()
    {
        return this.size;
    }
    
    private void grow()
    {
        T[] elementsClone = (T[]) new Object[ size * 2 ];
        
        for( int i = 0; i < size; i++ )
            elementsClone[i] = this.elements[i];
        
        this.elements = elementsClone;
    }
    
}


