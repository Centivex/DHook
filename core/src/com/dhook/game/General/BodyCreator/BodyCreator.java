package com.dhook.game.General.BodyCreator;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import static com.dhook.game.General.Constant.ppm;

public abstract class BodyCreator {


    private Body body;
    public BodyCreator(World world, BodyType bodyType, float posX, float posY){

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(posX * ppm, posY * ppm);
        switch (bodyType){
            case STATIC:
                bodyDef.type = BodyDef.BodyType.StaticBody;
                break;
            case DYNAMIC:
                bodyDef.type = BodyDef.BodyType.DynamicBody;
                break;
            case KINEMATIC:
                bodyDef.type = BodyDef.BodyType.KinematicBody;
                break;
        }

        this.body = world.createBody(bodyDef);
        //this.body.setFixedRotation(true);
    }

    public Body getBody() {
        return body;
    }

    public abstract Fixture getFixture();
}
