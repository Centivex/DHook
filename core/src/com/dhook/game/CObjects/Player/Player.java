package com.dhook.game.CObjects.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.dhook.game.CEngine.CObject;
import com.dhook.game.CEngine.CObjectDinamico;
import com.dhook.game.General.AnimationModule.SpriteDirection;
import com.dhook.game.General.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Player extends CObjectDinamico {
    @Override
    public float getVelocidadMovimiento() {
        return 3;
    }

    private InputModuleKeyboard moduloMovimiento = new InputModuleKeyboard(this);

    private Circle circleMov;
    private ShapeRenderer circuloMovRender;
    private SpriteDirection direccionPlayer = SpriteDirection.UP;

    private float dashDuration = 0.2f; // Duración del dash en segundos
    private float dashTimer = 0;

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

//            switch (object.getNameId()) {
//                case "Muneco":
//                    if (overlap(this, object)) {
//                        if (object.getCircleColision().x - this.circleMov.x > 0) {
//                            System.out.println("DERECHA");
//                        }
//                        if (object.getCircleColision().x - this.circleMov.x < 0) {
//                            System.out.println("IZQUIERDA");
//                        }
//                        if (object.getCircleColision().y - this.circleMov.y > 0) {
//                            System.out.println("ARRIBA");
//                        }
//                        if (object.getCircleColision().y - this.circleMov.y < 0) {
//                            System.out.println("ABAJO");
//                        }
//                    } else {
//
//                    }
//
//                    break;
//            }
        }
    }


    @Override
    public void draw(SpriteBatch spriteBatch) {
        seeCollision(spriteBatch, circleMov, circuloMovRender);
    }

    public void playerMove(float deltaTime) {
        // Cada frame actualizamos los booleans que indican qué teclas de movimiento hay pulsadas
        moduloMovimiento.resetInputBooleans();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            moduloMovimiento.setInputUp(true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            moduloMovimiento.setInputDown(true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            moduloMovimiento.setInputLeft(true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            moduloMovimiento.setInputRight(true);
        }

        moduloMovimiento.update(deltaTime);

        // Aplicamos el movimiento con el ángulo y porcentaje de movimiento calculados
        float angle = moduloMovimiento.getSmoothedMovingAngle();
        circleMov.x += moduloMovimiento.getMovingPercent() * getVelocidadMovimiento() * Math.cos(MathUtils.degreesToRadians * angle);
        circleMov.y += moduloMovimiento.getMovingPercent() * getVelocidadMovimiento() * Math.sin(MathUtils.degreesToRadians * angle);

        // Compara todas las diferencias de ángulo y elige la dirección de sprite (la cual incluye diagonales) con la diferencia más baja
        SpriteDirection min = Arrays.stream(SpriteDirection.values())
                .min((o1, o2) -> {
                    float diferencia1 = Utils.getAngleDifferenceNotSigned(moduloMovimiento.getSmoothedMovingAngle(), o1.getAngleDegrees());
                    float diferencia2 = Utils.getAngleDifferenceNotSigned(moduloMovimiento.getSmoothedMovingAngle(), o2.getAngleDegrees());
                    return Float.compare(diferencia1, diferencia2);
                }).get();

        direccionPlayer = min;

        System.out.println(min);

//        //------------------------------------------------------------
//        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
//            if (timerTest <= 0) {
//                timerTest = durationTest;
//            }
//            test = true;
//        } else {
//
//            if (timerTest > 0) {
//                timerTest -= Gdx.graphics.getDeltaTime();
//            } else if (timerTest <= 0) {
//                test = false;
//            }
//        }
//
//        System.out.println(direccionPlayer);
    }
}
