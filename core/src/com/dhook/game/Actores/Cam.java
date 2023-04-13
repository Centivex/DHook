package com.dhook.game.Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Cam extends Actor {
    private OrthographicCamera orthographiCam;

    public Cam (OrthographicCamera orthographiCam){
        this.orthographiCam=orthographiCam;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //movimiento de la camara
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            orthographiCam.translate(-16,0);
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            orthographiCam.translate(16,0);
        }else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            orthographiCam.translate(0,16);
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            orthographiCam.translate(0,-16);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setProjectionMatrix(orthographiCam.combined);
        orthographiCam.update();
    }

}
