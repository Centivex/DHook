package com.dhook.game.CObjects.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.dhook.game.CEngine.CObject;
import com.dhook.game.CObjects.Player.Enum.UltimatePressAD;
import com.dhook.game.CObjects.Player.Enum.UltimatePressWS;
import com.dhook.game.General.Direction;

import java.util.ArrayList;

public class Player extends CObject {

    private Circle circleMov;
    private ShapeRenderer circuloMovRender;
    private float velocityX = 100, velocityY = 100;
    private UltimatePressWS uPressWS;
    private boolean pressW, pressS, pressA, pressD, movY, movX;
    private UltimatePressAD uPressAD;

    private Direction directionPlayerX, directionPlayerY = Direction.UP;
    private Direction directionPlayer = Direction.UP;

    private float dashDuration = 0.2f; // Duración del dash en segundos
    private float dashTimer = 0;

    private boolean test =false;
    private float durationTest = 1f;
    private float timerTest = 0;


    public Player() {
        circleMov = new Circle(0, 0, 40);
        circuloMovRender = new ShapeRenderer();
        setNameId("Player");
    }

    @Override
    public Circle getCircleColision() {
        return circleMov;
    }


    @Override
    public void act(float deltaTime) {

        playerMove(deltaTime);


        // Si el temporizador del dash está activo, actualiza la posición del círculo
        if (dashTimer > 0) {
            float dashSpeed = 500; // Velocidad del dash
            float dashDeltaX = 1 * dashSpeed * Gdx.graphics.getDeltaTime();
            circleMov.x += dashDeltaX;
        }

        if (dashTimer > 0) {
            dashTimer -= Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

            if (dashTimer <= 0) {
                dashTimer = dashDuration;
            }


        } else {

        }


    }

    @Override
    public void colisiones(ArrayList<CObject> arrayCObject) {

        for (CObject object : arrayCObject) {

            switch (object.getNameId()) {

                case "Muneco":
                    if (overlap(this, object)) {


                        if (object.getCircleColision().x - this.circleMov.x > 0) {
                            System.out.println("DERECHA");

                        }
                        if (object.getCircleColision().x - this.circleMov.x < 0) {
                            System.out.println("IZQUIERDA");

                        }

                        if (object.getCircleColision().y - this.circleMov.y > 0) {
                            System.out.println("ARRIBA");

                        }
                        if (object.getCircleColision().y - this.circleMov.y < 0) {
                            System.out.println("ABAJO");

                        }
                    } else {

                    }

                    break;

            }

        }
    }


    @Override
    public void draw(SpriteBatch spriteBatch) {
        seeCollision(spriteBatch, circleMov, circuloMovRender);
    }

    public void playerMove(float deltaTime) {

        //moviemiento
        //mov horizontal-----------------------------

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            uPressWS = UltimatePressWS.W;
            pressW = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.W) && uPressWS == UltimatePressWS.W) {
            directionPlayerY = Direction.UP;
            circleMov.y += velocityY * deltaTime;
        } else if (!Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            pressW = false;
            uPressWS = UltimatePressWS.S;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            pressS = true;
            uPressWS = UltimatePressWS.S;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S) && uPressWS == UltimatePressWS.S) {
            directionPlayerY = Direction.DOWN;
            circleMov.y -= velocityY * deltaTime;
        } else if (!Gdx.input.isKeyPressed(Input.Keys.S)) {
            pressS = false;
            uPressWS = UltimatePressWS.W;
        }

        if (pressW || pressS) {
            movY = true;
        } else {
            movY = false;
        }


        //mov vertical-------------------------------------------------

        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            uPressAD = UltimatePressAD.A;
            pressA = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A) && uPressAD == UltimatePressAD.A) {
            directionPlayerX = Direction.LEFT;
            circleMov.x -= velocityX * deltaTime;
        } else if (!Gdx.input.isKeyPressed(Input.Keys.A)) {
            pressA = false;
            uPressAD = UltimatePressAD.D;
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            uPressAD = UltimatePressAD.D;
            pressD = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D) && uPressAD == UltimatePressAD.D) {
            directionPlayerX = Direction.RIGHT;
            circleMov.x += velocityX * deltaTime;
        } else if (!Gdx.input.isKeyPressed(Input.Keys.D)) {
            pressD = false;
            uPressAD = UltimatePressAD.A;
        }

        if (pressA || pressD) {
            movX = true;
        } else {
            movX = false;
        }

        //mov lateral----------------------------------------------------

        if (movY == true && movX == false) {
            directionPlayer = directionPlayerY;
        } else if (movY == false && movX == true) {
            directionPlayer = directionPlayerX;
        } else if (movY == true && movX == true) {
            if (directionPlayerY == Direction.UP && directionPlayerX == Direction.RIGHT) {
                directionPlayer = Direction.UPRIGHT;
            } else if (directionPlayerY == Direction.UP && directionPlayerX == Direction.LEFT) {
                directionPlayer = Direction.UPLEFT;
            } else if (directionPlayerY == Direction.DOWN && directionPlayerX == Direction.RIGHT) {
                directionPlayer = Direction.DOWNRIGHT;
            } else if (directionPlayerY == Direction.DOWN && directionPlayerX == Direction.LEFT) {
                directionPlayer = Direction.DOWNLEFT;
            }
        }


        //------------------------------------------------------------
       if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            if (timerTest <= 0) {
                timerTest = durationTest;
            }
            test = true;
        }else {

            if (timerTest > 0) {
                timerTest -= Gdx.graphics.getDeltaTime();
            }else if (timerTest <= 0) {
                test = false;
            }

        }












        System.out.println(directionPlayer);


    }


}
