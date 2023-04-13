package com.dhook.game.Actores.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dhook.game.General.Direction;

import java.util.ArrayList;


public class Player extends Actor {

    private Direction direction = Direction.DOWN;


    public Direction getDirection() {
        return direction;
    }


    public Player() {

        setSize(20, 20);

    }

    @Override
    public void act(float delta) {
        super.act(delta);



        //moviemiento
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            direction = Direction.UP;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            direction = Direction.DOWN;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            direction = Direction.LEFT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            direction = Direction.RIGHT;
        }else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

        } else {

        }


    }


}
