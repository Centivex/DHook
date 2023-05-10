package com.dhook.game.CObjects.Enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.dhook.game.CEngine.CObject;

import java.util.ArrayList;

public class MunecoPruebas extends CObject {

    private Rectangle rectangle;
    private ShapeRenderer shapeRenderer;

    private Circle circleMuneco;
    private ShapeRenderer circuloMunecoRender;

    public MunecoPruebas(){

        setNameId("Muneco");

        rectangle= new Rectangle(50,50,90,20);
        shapeRenderer = new ShapeRenderer();

        circleMuneco = new Circle(100,100,40);
        circuloMunecoRender = new ShapeRenderer();
    }

    @Override
    public Circle getCircleColision() {
        return circleMuneco;
    }


    @Override
    public void colisiones(ArrayList<CObject> arrayCObject) {

    }

    @Override
    public void act() {

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        shapeRenderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        shapeRenderer.end();

        circuloMunecoRender.setProjectionMatrix(spriteBatch.getProjectionMatrix());
        circuloMunecoRender.setColor(Color.PURPLE);
        circuloMunecoRender.begin(ShapeRenderer.ShapeType.Line);
        circuloMunecoRender.circle(circleMuneco.x,circleMuneco.y,circleMuneco.radius);
        circuloMunecoRender.end();
    }


}
