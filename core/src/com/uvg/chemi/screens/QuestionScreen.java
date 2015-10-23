package com.uvg.chemi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Chromz(Rodrigo Custodio) on 10/15/2015.
 */
public class QuestionScreen extends Stage implements Screen{

    private ChemistryTriviaGame game;
    private int errores;
    private Nivel preguntas;
    private boolean cambioDeNivel;
    private int numeroDePregunta;
    private TextButton[] botones;
    private Dialog tip;
    private Skin dialogSkin;

    private Texture background;
    private Texture interrogation;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private BitmapFont pregunta;
    private String currentQuestion;
    private float backx;
    private long tiempo;
    private Texture base;
    private Nivel megaNivel;
    private Sound bien;
    private Sound mal;
    private float preguntasMalas;

    public QuestionScreen(ChemistryTriviaGame game, Nivel nivel, Nivel megaNivel){
        super(new ScreenViewport());
        Gdx.input.setInputProcessor(this);
        this.game = game;
        this.batch = game.getSpriteBatch();
        this.camera = game.getOrthoCamera();
        this.preguntas = nivel;
        this.megaNivel = megaNivel;
        cambioDeNivel = false;
        numeroDePregunta = 0;
    }

    @Override
    public void show() {

        ((OrthographicCamera)this.getCamera()).setToOrtho(false,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        pregunta = new BitmapFont(Gdx.files.internal("fontAsk.fnt"));
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        base = new Texture(Gdx.files.internal("base.png"));

        mal = Gdx.audio.newSound(Gdx.files.internal("Error.mp3"));
        bien = Gdx.audio.newSound(Gdx.files.internal("Good.mp3"));

        backx = 1024;
        tiempo = TimeUtils.nanoTime();

        interrogation = new Texture(Gdx.files.internal("Interrogation.png"));

        background = game.getBackground();

        dialogSkin = new Skin();
        BitmapFont font = new BitmapFont(Gdx.files.internal("default.fnt"));
        dialogSkin.addRegions(new TextureAtlas(Gdx.files.internal("uiskin1.atlas")));
        dialogSkin.add("default-font", font);
        dialogSkin.load(Gdx.files.internal("uiskin1.json"));

        tip = new Dialog(":) tip", dialogSkin, "default"){
            @Override
            protected void result(Object obj){
                tip.getContentTable().reset();
            }
        };
        tip.button("Ok", false);


        currentQuestion = preguntas.getPregunta().get(0);

        ClickListener clickListener = new ClickListener(){
            @Override
            public void clicked(InputEvent ie, float x, float y) {
                TextButton temp = (TextButton)ie.getListenerActor();
                String respuesta = temp.getText().toString();

                if(respuesta.equals(preguntas.getRespuesta().get(numeroDePregunta))){
                    numeroDePregunta++;
                    cambioDeNivel = true;
                    bien.play(0.5f);
                }else{
                    errores++;
                    mal.play(0.5f);
                    if(errores == 2){
                        tip.getTitleLabel().setText(":)");
                        Label label = new Label(preguntas.getTips().get(numeroDePregunta), dialogSkin);
                        label.setWrap(true);
                        tip.getContentTable().add(label).prefWidth(350);
                        tip.show(QuestionScreen.this);
                    }
                    if(errores > 2){
                        numeroDePregunta++;
                        preguntasMalas++;
                        cambioDeNivel = true;
                        errores = 0;
                    }
                }

            }
        };

        ArrayList<Integer> indexes = new ArrayList<Integer>();
        indexes.add(0);
        indexes.add(1);
        indexes.add(2);
        indexes.add(3);
        Collections.shuffle(indexes);
        botones = new TextButton[4];
        botones[0] = new TextButton(preguntas.getPosiblesResp().get(0)[indexes.get(0)], skin);
        botones[1] = new TextButton(preguntas.getPosiblesResp().get(0)[indexes.get(1)], skin);
        botones[2] = new TextButton(preguntas.getPosiblesResp().get(0)[indexes.get(2)], skin);
        botones[3] = new TextButton(preguntas.getPosiblesResp().get(0)[indexes.get(3)], skin);

        botones[0].setWidth(botones[0].getWidth() + 20f);
        botones[1].setWidth(botones[0].getWidth());
        botones[2].setWidth(botones[0].getWidth());
        botones[3].setWidth(botones[0].getWidth());

        botones[0].setPosition(Gdx.graphics.getWidth() / 2 - botones[0].getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - botones[0].getHeight());
        botones[1].setPosition(Gdx.graphics.getWidth() / 2 - botones[1].getWidth() / 2,
                botones[0].getY() - botones[1].getHeight());
        botones[2].setPosition(Gdx.graphics.getWidth() / 2 - botones[2].getWidth() / 2,
                botones[1].getY() - botones[2].getHeight());
        botones[3].setPosition(Gdx.graphics.getWidth() / 2 - botones[3].getWidth() / 2,
                botones[2].getY() - botones[3].getHeight());

        botones[0].addListener(clickListener);
        botones[1].addListener(clickListener);
        botones[2].addListener(clickListener);
        botones[3].addListener(clickListener);

        for (int i = 0; i < 4; i++){
            botones[i].setHeight(40f);
        }


        addActor(botones[0]);
        addActor(botones[1]);
        addActor(botones[2]);
        addActor(botones[3]);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, backx - 1024, 0);
        batch.draw(background, backx, 0);
        batch.end();
        String nivel = "\u00BFCu\u00E1l es la f\u00F3rmula del " + currentQuestion + "?";
        batch.begin();

        batch.draw(base, 0, Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() * 0.12f,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.15f );
        pregunta.draw(batch, nivel, Gdx.graphics.getWidth() / 2 - pregunta.getSpaceWidth() * nivel.length() / 2,
                Gdx.graphics.getHeight() / 2 + pregunta.getLineHeight() + Gdx.graphics.getHeight() * 0.15f);
        batch.draw(interrogation, Gdx.graphics.getWidth() / 2 - (Gdx.graphics.getHeight() * 0.30f) / 2,
                Gdx.graphics.getHeight() / 2 + pregunta.getLineHeight() + Gdx.graphics.getHeight() * 0.15f,
                Gdx.graphics.getHeight() * 0.30f, Gdx.graphics.getHeight() * 0.30f);
        batch.end();
        super.act();
        super.draw();
        if(cambioDeNivel){
            if(numeroDePregunta < preguntas.getPregunta().size()) {
                ArrayList<Integer> indexes = new ArrayList<Integer>();
                indexes.add(0);
                indexes.add(1);
                indexes.add(2);
                indexes.add(3);
                Collections.shuffle(indexes);
                cambioDeNivel = false;
                errores = 0;
                currentQuestion = preguntas.getPregunta().get(numeroDePregunta);
                botones[0].setText(preguntas.getPosiblesResp().get(numeroDePregunta)[indexes.get(0)]);
                botones[1].setText(preguntas.getPosiblesResp().get(numeroDePregunta)[indexes.get(1)]);
                botones[2].setText(preguntas.getPosiblesResp().get(numeroDePregunta)[indexes.get(2)]);
                botones[3].setText(preguntas.getPosiblesResp().get(numeroDePregunta)[indexes.get(3)]);
            }else{
                float num = preguntas.getPregunta().size();
                if (preguntasMalas / num >= 0.70f){
                    game.setScreen(new LevelScreen(game, megaNivel, tip, false));
                }else {
                    game.setScreen(new LevelScreen(game, megaNivel, tip, true));
                }
            }
        }
        if (TimeUtils.nanoTime() - tiempo > 100000){
            backx -= 1;
            tiempo = TimeUtils.nanoTime();
        }

        if(backx == 0){
            backx = 1024;
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



}
