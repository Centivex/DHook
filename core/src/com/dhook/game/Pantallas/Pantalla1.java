package com.dhook.game.Pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dhook.game.CEngine.CObject;
import com.dhook.game.CEngine.CWorld;
import com.dhook.game.CObjects.Enemies.MunecoPruebas;
import com.dhook.game.CObjects.Player.Player;
import com.dhook.game.Scene2d.Cam;
import com.dhook.game.General.BaseScreen;

public class Pantalla1 extends BaseScreen {


    //Camara y escenario
    private OrthographicCamera orthographiCam;
    private SpriteBatch spriteBatch;
    private Viewport viewp;
    private Stage stage;

    //Actores
    private Cam cam;

    private CWorld cWorld;
    private CObject player, muneco;



    public Pantalla1(Game dHook) {
        super(dHook);
        spriteBatch = new SpriteBatch();

        //Camara
        orthographiCam = new OrthographicCamera();
        viewp = new PixelScreenViewport(256, 256, orthographiCam);

        //---------------------------------------------------------------------------------------------------------
        //Scene2d
        //Actores
        cam = new Cam(orthographiCam);
        //Escenario
        stage=new Stage(viewp, spriteBatch);
        stage.setDebugAll(true);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(cam);
        //---------------------------------------------------------------------------------------------------------

        cWorld= new CWorld(spriteBatch);
        player= new Player();
        muneco = new MunecoPruebas();
        cWorld.add(player);
        cWorld.add(muneco);



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

        cWorld.act(Gdx.graphics.getDeltaTime());
        cWorld.draw();
    }

    @Override
    public void dispose() {
        cam.remove();
        stage.dispose();



    }


}
