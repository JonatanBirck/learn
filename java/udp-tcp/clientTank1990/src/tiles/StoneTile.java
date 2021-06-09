/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiles;

import model.Assets;

/**
 *
 * @author jonat
 */
public class StoneTile extends Tile
{
    public StoneTile( int id ) 
    {
        super( Assets.STONE, id );
    }
    
    @Override
    public boolean isSolid()
    {
        return true;
    }
}
