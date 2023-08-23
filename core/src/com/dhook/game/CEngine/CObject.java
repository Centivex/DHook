package com.dhook.game.CEngine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public abstract class CObject {

    private String nameId;

    public CObject() {
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getNameId() {
        return nameId;
    }

    public abstract void act(float deltaTime);

    public abstract void draw(SpriteBatch spriteBatch);

    public abstract void colisiones(ArrayList<CObject> arrayCObject);


    public Circle getCircleColision() {
        return null;
    }

    public Rectangle getRectangleColision() {
        return null;
    }

    public boolean overlap(CObject CO1, CObject CO2) {

        if (CO1.getCircleColision() != null) {

            if (CO2.getCircleColision() != null) {
                return Intersector.overlaps(CO1.getCircleColision(), CO2.getCircleColision());

            } else if (CO2.getRectangleColision() != null) {
                return Intersector.overlaps(CO1.getCircleColision(), CO2.getRectangleColision());

            }

        } else if (CO1.getRectangleColision() != null) {

            if (CO2.getCircleColision() != null) {
                return Intersector.overlaps(CO2.getCircleColision(), CO1.getRectangleColision());

            } else if (CO2.getRectangleColision() != null) {
                return Intersector.overlaps(CO1.getRectangleColision(), CO2.getRectangleColision());

            }
        }

        return false;
    }

    public void seeCollision(SpriteBatch spriteBatch,Circle circle ,ShapeRenderer shapeRenderer){
        shapeRenderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(circle.x,circle.y,circle.radius);
        shapeRenderer.end();
    }

    public void seeCollision(SpriteBatch spriteBatch,Rectangle rectangle ,ShapeRenderer shapeRenderer){
        shapeRenderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        shapeRenderer.end();
    }


}
