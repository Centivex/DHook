package com.dhook.game.Actores.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dhook.game.General.BodyCreator.BodyType;
import com.dhook.game.General.BodyCreator.BodyCreator;
import com.dhook.game.General.BodyCreator.BodyCreatorBox;
import com.dhook.game.General.Direction;

import static com.dhook.game.General.Constant.ppm;
import static com.dhook.game.General.Constant.ppmR;

public class Player extends Actor {

    //cuerpo

    private Body playerbody;
    private Fixture playerFixture;

    private float width, height;
    private Direction direction = Direction.DOWN;

    public Body getPlayerbody() {
        return playerbody;
    }

    public Fixture getPlayerFixture() {
        return playerFixture;
    }

    public float getBodyPositionX(){
        return playerbody.getPosition().x * ppmR;
    }

    public float getBodyPositionY(){
        return playerbody.getPosition().y *ppmR;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public Player(World world) {

        BodyCreatorBox playerCreated = new BodyCreatorBox(world, BodyType.DYNAMIC, 20, 20, 5, 5, "player");
        playerbody = playerCreated.getBody();
        playerFixture = playerCreated.getFixture();
        width = playerCreated.getWidth();
        height = playerCreated.getHeight();

        //playerbody.setFixedRotation(true);

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        //moviemiento
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            playerbody.setLinearVelocity(0, 50 * ppm);
            direction = Direction.UP;
            //playerbody.applyForceToCenter(0,5*ppm,true);
            //playerbody.applyLinearImpulse(0, 0.5f * ppm, 0, 0, true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            playerbody.setLinearVelocity(0, -50 * ppm);
            direction = Direction.DOWN;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            playerbody.setLinearVelocity(-50 * ppm, 0);
            direction = Direction.LEFT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            playerbody.setLinearVelocity(50 * ppm, 0);
            direction = Direction.RIGHT;
        } else {
            playerbody.setLinearVelocity(0, 0);
        }
    }


}
