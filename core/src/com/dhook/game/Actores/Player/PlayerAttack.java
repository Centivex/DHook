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

import javax.print.attribute.standard.PagesPerMinute;

import static com.dhook.game.General.Constant.ppm;

public class PlayerAttack extends ActionPlayer {

    //cuerpo
    private World world;
    private Player player;
    private float posXPlayer = 0 , posYPlayer= 0;

    private Body playerAttackBody;
    private Fixture playerAttackFixture;
    private float radius = 5;


    public PlayerAttack(World world, Player player) {
        super(world, player);
        this.player=player;

        BodyCreatorCircle playerAttackCreated = new BodyCreatorCircle(world, BodyType.DYNAMIC,50,50,radius,"player",true);
        playerAttackBody= playerAttackCreated.getBody();
        playerAttackFixture = playerAttackCreated.getFixture();

        //playerAttackBody.setFixedRotation(true);






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

        if(player.getMovement()){
            playerAttackBody.setLinearVelocity(0,0);
        }else{
            playerAttackBody.setLinearVelocity(player.getPlayerbody().getLinearVelocity());
        }


        System.out.println("distancia con el ataque:" + (player.getPlayerbody().getPosition().x - playerAttackBody.getPosition().x));

        posXPlayer = player.getPlayerbody().getPosition().x;
        posYPlayer = player.getPlayerbody().getPosition().y;

        //playerAttackBody.setTransform((player.getWidth()/2+player.getBodyPositionX()-radius/2)*ppm ,(player.getHeight()*2+player.getBodyPositionY())*ppm,0);

        //moviemiento
        /*if (Gdx.input.isKeyPressed(Input.Keys.I)) {
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
        }*/


    }


}
