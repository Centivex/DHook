package com.dhook.game.Actores.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dhook.game.Actores.Player.Player;
import com.dhook.game.General.BodyCreator.BodyCreator;
import com.dhook.game.General.BodyCreator.BodyCreatorCircle;
import com.dhook.game.General.BodyCreator.BodyType;

import static com.dhook.game.General.Constant.ppm;

public class PlayerAttack extends ActionPlayer {

    //cuerpo
    private World world;
    private Player player;
    private Body playerAttackBody;
    private Fixture playerAttackFixture;

    private WeldJointDef weldJointDef;

    private float radius = 5;


    public PlayerAttack(World world, Player player) {
        super(world, player);
        this.player=player;

        BodyCreatorCircle playerAttackCreated = new BodyCreatorCircle(world, BodyType.DYNAMIC,player.getWidth()/2+player.getBodyPositionX()-radius/2,player.getHeight()*2+player.getBodyPositionY()+10,radius,"player",true);
        playerAttackBody= playerAttackCreated.getBody();
        playerAttackFixture = playerAttackCreated.getFixture();

        //playerAttackBody.setFixedRotation(true);

        RevoluteJointDef revoluteJointDef= new RevoluteJointDef();
        revoluteJointDef.bodyA = playerAttackBody;
        revoluteJointDef.bodyB = player.getPlayerbody();
        revoluteJointDef.collideConnected = false;


        /*RevoluteJointDef revoluteJointDef = new RevoluteJointDef();
        revoluteJointDef.initialize(player.getPlayerbody(),playerAttackBody , new Vector2(player.getPlayerbody().getPosition().x, player.getPlayerbody().getPosition().y));
        revoluteJointDef.enableMotor = true;
        revoluteJointDef.motorSpeed = 5;
        world.createJoint(revoluteJointDef);*/

        /*DistanceJointDef jointDef = new DistanceJointDef();
        jointDef.bodyA = player.getPlayerbody();
        jointDef.bodyB = playerAttackBody;
        jointDef.localAnchorA.set(0, 10*ppm);
        jointDef.localAnchorB.set(0, 10*ppm);
        jointDef.length = 10*ppm;
        jointDef.frequencyHz = 1f;
        jointDef.dampingRatio = 0.1f;
        world.createJoint(jointDef);*/

        /*PrismaticJointDef prismaticJointDef = new PrismaticJointDef();
        prismaticJointDef.bodyA = player.getPlayerbody();
        prismaticJointDef.bodyB = playerAttackBody;
        prismaticJointDef.localAnchorA.set(0, 0);
        prismaticJointDef.localAnchorB.set(0, 0);
        prismaticJointDef.localAxisA.set(1, 1); // dirección de la unión
        prismaticJointDef.enableLimit = true; // activar límites de movimiento
        prismaticJointDef.lowerTranslation = 0f; // límite inferior de movimiento
        prismaticJointDef.upperTranslation = 5f; // límite superior de movimiento
        world.createJoint(prismaticJointDef);*/

        /*PulleyJointDef pulleyJointDef = new PulleyJointDef();
        // Define los cuerpos
        pulleyJointDef.bodyA =  player.getPlayerbody();;
        pulleyJointDef.bodyB = playerAttackBody;
        // Define las anclas
        pulleyJointDef.groundAnchorA.set(0, 0);
        pulleyJointDef.groundAnchorB.set(10*ppm, 0);
        pulleyJointDef.localAnchorA.set(0, 0);
        pulleyJointDef.localAnchorB.set(0, 0);
        // Define las longitudes y relaciones de los cables
        pulleyJointDef.lengthA = 5*ppm;
        pulleyJointDef.lengthB = 10*ppm;
        pulleyJointDef.ratio = 2*ppm;
        PulleyJoint joint = (PulleyJoint) world.createJoint(pulleyJointDef);*/


        /*WheelJointDef wheelJointDef = new WheelJointDef();
        wheelJointDef.initialize(player.getPlayerbody(), playerAttackBody, player.getPlayerbody().getWorldCenter(), new Vector2(0, 10*ppm));
        wheelJointDef.frequencyHz = 2;
        wheelJointDef.maxMotorTorque = 10;
        wheelJointDef.motorSpeed = 0;
        wheelJointDef.enableMotor = true;
        world.createJoint(wheelJointDef);*/

        /*weldJointDef = new WeldJointDef();
        weldJointDef.bodyA =  player.getPlayerbody();
        weldJointDef.bodyB = playerAttackBody;
        weldJointDef.localAnchorA.set(10*ppm, 0);
        weldJointDef.localAnchorB.set(-5*ppm, 0);
        world.createJoint(weldJointDef);*/




        /*PolygonShape sensorShape = new PolygonShape();
        sensorShape.setAsBox(20*ppm, 20*ppm);

        FixtureDef sensorFixtureDef = new FixtureDef();
        sensorFixtureDef.shape = sensorShape;
        sensorFixtureDef.isSensor = true;
        player.getPlayerbody().createFixture(sensorFixtureDef);


        player.getPlayerbody().getFixtureList().get(1).setSensor(true);
        player.getPlayerbody().getFixtureList().get(1).setUserData("front sensor");
*/


    }

    public Body getPlayerAttackBody() {
        return playerAttackBody;
    }

    public Fixture getPlayerAttackFixture() {
        return playerAttackFixture;
    }


    @Override
    public void act(float delta) {
        super.act(delta);

        //playerAttackBody.setTransform((player.getWidth()/2+player.getBodyPositionX()-radius/2)*ppm ,(player.getHeight()*2+player.getBodyPositionY())*ppm,0);

        //moviemiento
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            //weldJointDef.localAnchorA.set(0, 10*ppm);
            playerAttackBody.setLinearVelocity(0, 50 * ppm);
            //playerbody.applyForceToCenter(0,5*ppm,true);
            //playerbody.applyLinearImpulse(0, 0.5f * ppm, 0, 0, true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.K)) {
            playerAttackBody.setLinearVelocity(0, -50 * ppm);
        } else if (Gdx.input.isKeyPressed(Input.Keys.J)) {
            playerAttackBody.setLinearVelocity(-50 * ppm, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            playerAttackBody.setLinearVelocity(50 * ppm, 0);
        } else {
            playerAttackBody.setLinearVelocity(0, 0);
        }


    }


}
