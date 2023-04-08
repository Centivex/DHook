package com.dhook.game.General;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class BaseScreen implements Screen {

    private Game DHook;

    public BaseScreen(Game dHook) {
        this.DHook = dHook;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
