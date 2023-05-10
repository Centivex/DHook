package com.dhook.game.CObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.dhook.game.CEngine.CObject;

import java.util.ArrayList;

public class Player extends CObject {

    private Circle circleMov;
    private ShapeRenderer circuloMovRender;





    public Player() {
        circleMov = new Circle(0,0,40);
        circuloMovRender = new ShapeRenderer();
        setNameId("Player");
    }

    @Override
    public Circle getCircleColision() {
        return circleMov;
    }


    @Override
    public void act() {
        //moviemiento
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            circleMov.setY(circleMov.y +1);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            circleMov.setY(circleMov.y -1);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            circleMov.setX(circleMov.x -1);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            circleMov.setX(circleMov.x +1);
        }else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

        } else {

        }



    }

    @Override
    public void colisiones(ArrayList<CObject> arrayCObject) {

        for (CObject object : arrayCObject) {

            switch (object.getNameId()){

                case "Muneco":
                   if(overlap(this,object )){
                       System.out.println("Hola");

                   }

                    break;

            }

        }
    }


    @Override
    public void draw(SpriteBatch spriteBatch) {
        seeCollision(spriteBatch, circleMov,circuloMovRender);
    }




}
