package com.dhook.game.General;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.dhook.game.Pantallas.Pantalla1;

public class DHook extends Game {
	
	@Override
	public void create () {
		setScreen(new Pantalla1(this));
	}


}
