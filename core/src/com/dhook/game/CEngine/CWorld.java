package com.dhook.game.CEngine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class CWorld {
    private ArrayList<CObject> arrayCObject;
    private SpriteBatch spriteBatch;
    public CWorld(SpriteBatch spriteBatch){
        this.spriteBatch= spriteBatch;
        arrayCObject = new ArrayList<CObject>();
    }

    public void add(CObject cObject){
        arrayCObject.add(cObject);
    }

    public void act(){
        for (CObject object : arrayCObject) {
            object.act();
            object.colisiones(arrayCObject);
        }

    }

    public void draw(){
        for (CObject object : arrayCObject) {
            object.draw(spriteBatch);
        }
    }

}
