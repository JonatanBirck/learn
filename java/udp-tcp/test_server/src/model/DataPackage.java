/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author jonat
 */
public interface DataPackage<T>
{       
    public List<T> createPackages( byte[] bytes );

    public T readPackage( byte[] bytes ) throws Exception;
    
    public byte[] toBytes();
}
