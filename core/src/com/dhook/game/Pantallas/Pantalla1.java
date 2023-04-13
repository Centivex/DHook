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
import com.dhook.game.General.BaseScreen;

public class Pantalla1 extends BaseScreen {


    //Camara y escenario
    private OrthographicCamera orthographiCam;
    private Viewport viewp;
    private Stage stage;

    //Actores
    private Cam cam;
    private Player player;

    private MunecoPractica munecoPractica;

    public Pantalla1(Game dHook) {
        super(dHook);

        //Camara
        orthographiCam = new OrthographicCamera();
        viewp = new PixelScreenViewport(256, 256, orthographiCam);

        //---------------------------------------------------------------------------------------------------------
        //Actores
        cam = new Cam(orthographiCam);
        player = new Player();
        munecoPractica= new MunecoPractica();

        //---------------------------------------------------------------------------------------------------------
        //Escenario
        stage=new Stage(viewp);
        stage.setDebugAll(true);
        Gdx.input.setInputProcessor(stage);

        stage.addActor(cam);
        stage.addActor(player);



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

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        cam.remove();
        player.remove();
        stage.dispose();

    }


}
