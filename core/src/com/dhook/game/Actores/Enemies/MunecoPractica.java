package com.dhook.game.Actores.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dhook.game.General.Direction;

import static com.dhook.game.General.Constant.ppm;

public class MunecoPractica extends Actor {

    //cuerpo
    private World world;
    private Body munecobody;
    private Fixture munecoFixture;

    public Body getMunecobody() {
        return munecobody;
    }

    public Fixture getMunecoFixture() {
        return munecoFixture;
    }

    public MunecoPractica(World world) {
        this.world = world;

        //--------------------------------------------------------------------------------------------------------
        //cuerpo
        BodyDef defMuneco = new BodyDef();
        defMuneco.position.set(80 * ppm, 20 * ppm);
        defMuneco.type = BodyDef.BodyType.KinematicBody;
        munecobody = world.createBody(defMuneco);


        PolygonShape munecoShape = new PolygonShape();
        munecoShape.setAsBox(15 * ppm, 5 * ppm);
        munecoFixture = munecobody.createFixture(munecoShape, 1);
        munecoShape.dispose();

        //munecobody.setTransform(0, 0, 0);

        munecoFixture.setUserData("muneco");


        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA(), fixtureB = contact.getFixtureB();

                if (fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("muneco")
                        || fixtureA.getUserData().equals("muneco") && fixtureB.getUserData().equals("player")) {
                    System.out.println("ha empezado a  chocar");

                }
            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA(), fixtureB = contact.getFixtureB();

                if (fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("muneco")
                        || fixtureA.getUserData().equals("muneco") && fixtureB.getUserData().equals("player")) {
                    System.out.println("ha terminado de  chocar");
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                Fixture fixtureA = contact.getFixtureA(), fixtureB = contact.getFixtureB();

                if (fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("muneco")
                        || fixtureA.getUserData().equals("muneco") && fixtureB.getUserData().equals("player")) {
                    System.out.println("presolve  chocar");
                }
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                Fixture fixtureA = contact.getFixtureA(), fixtureB = contact.getFixtureB();

                if (fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("muneco")
                        || fixtureA.getUserData().equals("muneco") && fixtureB.getUserData().equals("player")) {
                    System.out.println("pstsolve a  chocar");
                }
            }
        });
    }


    @Override
    public void act(float delta) {
        super.act(delta);

    }


}
