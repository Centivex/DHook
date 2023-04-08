package com.dhook.game.General.BodyCreator;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.dhook.game.General.Constant.ppm;
import static com.dhook.game.General.Constant.ppmR;

public class BodyCreatorBox extends BodyCreator {

    private Fixture fixture;
    private float  widthPpm, HeightPpm;

    public BodyCreatorBox(World world, BodyType bodyType, float posX, float posY, float width, float height, String nameColision) {
        super(world, bodyType, posX, posY);
        CreateFixture(width, height, nameColision, false);
    }

    public BodyCreatorBox(World world, BodyType bodyType, float posX, float posY, float width, float height, String nameColision, boolean sensor) {
        super(world, bodyType, posX, posY);
        CreateFixture(width, height, nameColision, sensor);

    }

    @Override
    public Fixture getFixture() {
        return fixture;
    }

    public float getHeight() {
        return HeightPpm * ppmR;
    }

    public float getWidth() {
        return widthPpm * ppmR;
    }

    public float getHeightPpm() {
        return HeightPpm;
    }

    public float getWidthPpm() {
        return widthPpm;
    }

    private void CreateFixture(float width, float height, String nameColision, boolean sensor) {
        PolygonShape polygonShape = new PolygonShape();
        this.widthPpm = width * ppm;
        this.HeightPpm = height * ppm;
        polygonShape.setAsBox(this.widthPpm, this.HeightPpm);
        fixture = getBody().createFixture(polygonShape, 1);
        polygonShape.dispose();
        fixture.setUserData(nameColision);
        fixture.setSensor(sensor);

    }

}
