package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.GameScreen;;

public class MyGdxGame extends Game {

	
	@Override
	public void create () {
		setScreen(new GameScreen());
	}

 
    @Override
    public void dispose() {
    }
 
 
    @Override
    public void resize(int width, int height) {
    }
 
    @Override
    public void pause() {
    }
 
    @Override
    public void resume() {
    }
}
