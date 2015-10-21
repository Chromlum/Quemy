package com.uvg.chemi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.uvg.chemi.ChemistryTriviaGame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.xml.soap.Text;

/**
 * Created by Chromz on 10/14/2015.
 */
public class LevelScreen extends Stage implements Screen {

    private ChemistryTriviaGame game;
    private TextButton[] botones;
    private Nivel megaNivel;
    private ArrayList<Nivel> nivelitos;
    private Label titulo;
    private Dialog dialogo;

    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private float backx;
    private long tiempo;
    private float backy;


    public LevelScreen(ChemistryTriviaGame game, Nivel megaNivel){
        super(new ScreenViewport());
        this.game = game;
        this.megaNivel = megaNivel;
    }

    public LevelScreen(ChemistryTriviaGame game, Nivel megaNivel, Dialog dialog){
        super(new ScreenViewport());
        this.game = game;
        this.megaNivel = megaNivel;
        this.dialogo = dialog;
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(this);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        final TextButton returnButton = new TextButton("Regresar", skin);
        titulo = new Label("NIVEL", skin);

        titulo.setPosition(Gdx.graphics.getWidth() / 2 - titulo.getWidth() / 2,
                Gdx.graphics.getHeight() - titulo.getHeight() - 10);
        titulo.setColor(Color.PURPLE);
        addActor(titulo);

        if(this.dialogo != null){
            dialogo.getContentTable().reset();
            Label label = new Label("Muy bien! sigue con los dem\u00E1s niveles", skin);
            label.setWrap(true);
            dialogo.getContentTable().add(label).prefWidth(350);
            dialogo.show(this);
        }

        backx = 800;
        backy = 800;

        background = game.getBackground();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 400);

        int totalPreguntas = megaNivel.getPosiblesResp().size();
        ArrayList<String> preguntas = new ArrayList<String>();
        ArrayList<String> respuestas = new ArrayList<String>();
        ArrayList<String> tips = new ArrayList<String>();
        ArrayList<String[]> posiblesResp = new ArrayList<String[]>();
        nivelitos = new ArrayList<Nivel>();

        for (int i = 0; i < totalPreguntas; i++){
            preguntas.add(megaNivel.getPregunta().get(i));
            respuestas.add(megaNivel.getRespuesta().get(i));
            tips.add(megaNivel.getTips().get(i));
            posiblesResp.add(megaNivel.getPosiblesResp().get(i));

            if((i + 1)  % 10 == 0){

                nivelitos.add(new Nivel(preguntas, respuestas, posiblesResp, tips));
                preguntas = new ArrayList<String>();
                respuestas = new ArrayList<String>();
                tips = new ArrayList<String>();
                posiblesResp = new ArrayList<String[]>();
            }
        }
        if(totalPreguntas < 10){
            nivelitos.add(new Nivel(preguntas, respuestas, posiblesResp, tips));
        }

        int cantidadDeNiveles = nivelitos.size();

        int cantidadDeCols = 4;

        int cantidadDeFilas = 0;

        for(int j = 0; j < cantidadDeNiveles; j++){
            if(j % cantidadDeCols == 0)
                cantidadDeFilas++;
        }

        int col = Gdx.graphics.getWidth() / cantidadDeCols;
        int row = (int)(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.20f) / cantidadDeFilas;


        ClickListener click = new ClickListener(){

            @Override
            public void clicked(InputEvent ie, float x, float y){
                int nivel = Integer.parseInt(((TextButton)ie.getListenerActor())
                        .getText().toString()) - 1;
                game.setScreen(new QuestionScreen(game, nivelitos.get(nivel), megaNivel));
            }

        };


        botones = new TextButton[cantidadDeNiveles];
        int multiplicador = 1;
        for (int i = 0; i < cantidadDeNiveles; i++){
            botones[i] = new TextButton(Integer.toString(i + 1), skin);
            botones[i].setWidth(70f);
            botones[i].setHeight(70f);
            if ((i + 1) % 5 == 0){
                cantidadDeFilas--;
                multiplicador = 1;
            }else if (i != 0) multiplicador++;
            botones[i].setPosition(col * multiplicador - 85 * 1.5f, row * cantidadDeFilas - 70f);
            botones[i].addListener(click);
            addActor(botones[i]);
        }

        returnButton.setPosition(0, Gdx.graphics.getHeight() - returnButton.getHeight());
        returnButton.setWidth(100f);
        returnButton.getLabel().setFontScale(0.5f);
        returnButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent ie, float x, float y) {
                LevelScreen.this.game.setScreen(new WorldScreen(LevelScreen.this.game));
            }

        });

        addActor(returnButton);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, backx - 800, backy - 800);
        batch.draw(background, backx, backy);
        batch.end();
        super.act(Gdx.graphics.getDeltaTime());
        super.draw();

        if (TimeUtils.nanoTime() - tiempo > 100000){
            backx -= 2;
            backy -= 2;
            tiempo = TimeUtils.nanoTime();
        }

        if(backx == 0){
            backx = 800;
            backy = 800;
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
