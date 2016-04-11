package com.mygdx.game.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {
	
	/** Les blocs qui composent le monde **/
    Array<Block> blocks = new Array<Block>();
    /** Notre hero!! **/
    Bob bob;
 
    // Getters -----------
    public Array<Block> getBlocks() {
        return blocks;
    }
    public Bob getBob() {
        return bob;
    }
    // --------------------
 
    public World() {
        createDemoWorld();
    }
 
    private void createDemoWorld() {
        bob = new Bob(new Vector2(7, 2));
 
        for (int col = 0; col < 10; col++) {
            blocks.add(new Block(new Vector2(col, 0)));              
            blocks.add(new Block(new Vector2(col, 6)));
            if (col > 2)
                blocks.add(new Block(new Vector2(col, 1)));
        }
 
        blocks.add(new Block(new Vector2(9, 2)));
        blocks.add(new Block(new Vector2(9, 3)));
        blocks.add(new Block(new Vector2(9, 4)));
        blocks.add(new Block(new Vector2(9, 5)));
 
        blocks.add(new Block(new Vector2(6, 3)));
        blocks.add(new Block(new Vector2(6, 4)));
        blocks.add(new Block(new Vector2(6, 5)));
    }
    
}
