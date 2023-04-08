package com.dhook.game.Pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dhook.game.Actores.Cam;
import com.dhook.game.Actores.Enemies.MunecoPractica;
import com.dhook.game.Actores.Player.Player;
import com.dhook.game.Actores.Player.PlayerAttack;
import com.dhook.game.General.BaseScreen;

import static com.dhook.game.General.Constant.ppm;

public class Pantalla1 extends BaseScreen {

    //mundo
    private World world;
    private Box2DDebugRenderer b2render;

    //Camara y escenario
    private OrthographicCamera orthographiCam;
    private Viewport viewp;
    private Stage stage;

    //Actores
    private Cam cam;
    private Player player;
    private PlayerAttack playerAttack;

    private MunecoPractica munecoPractica;

    public Pantalla1(Game dHook) {
        super(dHook);


        //mundo
        world = new World(new Vector2(0, 0), true);
        b2render = new Box2DDebugRenderer();

        //Camara
        orthographiCam = new OrthographicCamera();
        viewp = new PixelScreenViewport(256* ppm, 256* ppm, orthographiCam);

        //---------------------------------------------------------------------------------------------------------
        //Actores
        cam = new Cam(orthographiCam);
        player = new Player(world);
        playerAttack = new PlayerAttack(world, player);
        munecoPractica= new MunecoPractica(world);

        //---------------------------------------------------------------------------------------------------------
        //Escenario
        stage=new Stage(viewp);

        stage.addActor(cam);
        stage.addActor(player);
        stage.addActor(playerAttack);

        //stage.addActor(munecoPractica);

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewp.update(width, height);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        //escenario y mundo
        world.step(1/60f,6,2);

        stage.draw();
        b2render.render(world,orthographiCam.combined);
    }

    @Override
    public void dispose() {
        cam.remove();
        player.getPlayerbody().destroyFixture(player.getPlayerFixture());
        world.destroyBody(player.getPlayerbody());
        player.remove();
        playerAttack.getPlayerAttackBody().destroyFixture(playerAttack.getPlayerAttackFixture());
        world.destroyBody(playerAttack.getPlayerAttackBody());
        playerAttack.remove();
        stage.dispose();
        b2render.dispose();
        world.dispose();
    }


}
