package com.dhook.game.General.BodyCreator;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.dhook.game.General.Constant.ppm;
import static com.dhook.game.General.Constant.ppmR;

public class BodyCreatorCircle extends  BodyCreator{

    private Fixture fixture;

    private float  radius;
    public BodyCreatorCircle(World world, BodyType bodyType, float posX, float posY, float radius, String nameColision) {
        super(world, bodyType, posX, posY);
        CreateFixture(radius, nameColision, false);
    }

    public BodyCreatorCircle(World world, BodyType bodyType, float posX, float posY, float radius, String nameColision, boolean sensor) {
        super(world, bodyType, posX, posY);
        CreateFixture(radius, nameColision, sensor);
    }


    @Override
    public Fixture getFixture() {
        return fixture;
    }

    public float getRadius() {
        return radius;
    }

    private void CreateFixture(float radius, String nameColision, boolean sensor) {
        this.radius=radius;
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius * ppm);
        fixture = getBody().createFixture(circleShape, 1);
        circleShape.dispose();
        fixture.setUserData(nameColision);
        fixture.setSensor(sensor);

    }
}
