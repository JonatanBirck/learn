/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.AWTException;
import java.awt.Robot;
import util.Log;

/**
 *
 * @author jonat
 */
public class ImagePackageWorkerManager
{
    private static ImagePackageWorkerManager imagePackageWorkerManager = null;
    private int width = 1920;            
    private int height = 1050;
    
    public ImagePackageWorkerManager(){}    
    
    public static ImagePackageWorkerManager getInstance()
    {
        if( imagePackageWorkerManager == null )
            imagePackageWorkerManager = new ImagePackageWorkerManager();

        return imagePackageWorkerManager;
    }
    
    doWork()
    {
        
    }
}
