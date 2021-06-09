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
public class Queue<T>
{
    private T[] elements;
    private final int DEFAULT_CAPACITY = 10;
    private int indexStart = 0;
    private int indexEnd = 0;
    private int size = 0;

    public Queue() 
    {
        elements = (T[]) new Object[ this.DEFAULT_CAPACITY ];
    }   
    
    public void enqueue( T element )
    {
        if( size() == this.elements.length )
            grow();
        
        this.elements[ this.indexEnd ] = element;
        
        this.indexEnd++;
        
        if( this.indexEnd == this.elements.length )
            this.indexEnd = 0;
        
        this.size++;
    }
    
    public void dequeue()
    {
        if( this.size > 0  )
        {
            this.indexStart++;
            
            if( this.indexStart == this.elements.length )
                this.indexStart = 0;
            
            this.size--;
        }
    }
    
    public T next()
    {
        return this.elements[ this.indexStart ];
    }
    
    public int size()
    {
        return this.size;
    }
    
    private void grow()
    {
        T[] elementsClone = (T[]) new Object[ this.size * 2 ];
        
        int sizeQueue = this.size;
        
        for( int i = 0; i < sizeQueue; i++ )
        {
            elementsClone[i] = this.elements[ this.indexStart++ ];
            
            if( this.indexStart == this.elements.length )
                this.indexStart = 0;
        }
        
        this.indexStart = 0;
        this.indexEnd = sizeQueue;        
        
        this.elements = elementsClone;
    }    
}
