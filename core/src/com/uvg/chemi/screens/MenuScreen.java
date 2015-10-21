package com.uvg.chemi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.uvg.chemi.ChemistryTriviaGame;

/**
 * Created by Chromz(Rodrigo Custodio) on 10/15/2015.
 */
public class MenuScreen implements Screen {
    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private float backx;
    private long tiempo;
    private TextButton inicio;
    private TextButton salir;
    private BitmapFont font;
    private Texture logo;
    private Stage stage;
    private ChemistryTriviaGame game;

    public MenuScreen(ChemistryTriviaGame game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = game.getStage();
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin();
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        font = new BitmapFont(Gdx.files.internal("fontChemi.fnt"));

        logo = new Texture(Gdx.files.internal("chemiLogo.png"));



        inicio = new TextButton("Inicio", skin);
        salir = new TextButton("Salir", skin);

        stage.addActor(inicio);
        stage.addActor(salir);

        inicio.setX(Gdx.graphics.getWidth() / 2 - inicio.getWidth() / 2);
        inicio.setY(Gdx.graphics.getHeight() / 2 - 150);

        salir.setX(Gdx.graphics.getWidth() / 2 - salir.getWidth() / 2);
        salir.setY(Gdx.graphics.getHeight() / 2 - inicio.getHeight() - 160);



        backx = 2417;
        tiempo = TimeUtils.nanoTime();

        background = new Texture(Gdx.files.internal("Quemi.jpg"));
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        batch = game.getSpriteBatch();
        camera = game.getOrthoCamera();

        inicio.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent ie, float x, float y) {
                game.setScreen(new WorldScreen(game));
            }
        });

        salir.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent ie, float x, float y){
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void dispose() {
        /*stage.dispose();
        background.dispose();
        batch.dispose();*/
        stage.clear();
    }

    @Override
    public void hide() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, backx - 2417, 0);
        batch.draw(background, backx, 0);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.begin();
        font.draw(batch, "NOMECLATRIVIA", Gdx.graphics.getWidth() / 2 - 230,
                Gdx.graphics.getHeight() / 2);
        batch.draw(logo, Gdx.graphics.getWidth() / 2 - 50,
                Gdx.graphics.getHeight() / 2 + 150, 83, 100);
        batch.end();

        if (TimeUtils.nanoTime() - tiempo > 100000){
            backx -= 1;
            tiempo = TimeUtils.nanoTime();
        }

        if(backx == 0){
            backx = 2417;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }
}
