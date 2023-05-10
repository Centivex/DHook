package com.dhook.game.Scene2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ExperimentoCuerpo extends Actor {

    Rectangle rectangle, rectangle2;
    Circle circle;
    ShapeRenderer shapeRenderer, shapeRenderer2, circleRender;

    public ExperimentoCuerpo(){
        rectangle= new Rectangle(50,50,90,20);
        rectangle2= new Rectangle(30,50,90,20);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer2 = new ShapeRenderer();

        circle = new Circle(40,100,25);
        circleRender = new ShapeRenderer();


    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(rectangle.overlaps(rectangle2)) {
            // Rectángulos se superponen
            // Agrega aquí el código que deseas ejecutar cuando los rectángulos se superponen.
            //System.out.println("holaaaaa");
        }


        if (Intersector.overlaps(circle,rectangle)){
            System.out.println("miamiiiiiii");
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        shapeRenderer.end();

        shapeRenderer2.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer2.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer2.rect(rectangle2.getX(), rectangle2.getY(), rectangle2.getWidth(), rectangle2.getHeight());
        shapeRenderer2.end();

        circleRender.setProjectionMatrix(batch.getProjectionMatrix());
        circleRender.setColor(Color.GREEN);
        circleRender.begin(ShapeRenderer.ShapeType.Filled);
        circleRender.circle(circle.x,circle.y,circle.radius);
        circleRender.end();

    }
}
