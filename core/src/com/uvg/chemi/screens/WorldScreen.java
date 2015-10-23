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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.TimeUtils;
import com.uvg.chemi.ChemistryTriviaGame;

import java.util.ArrayList;

/**
 * Created by Chromz(Rodrigo Custodio) on 10/20/2015.
 */
public class WorldScreen implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture background;
    private Stage stage;
    private ChemistryTriviaGame game;
    private float backx;
    private long tiempo;
    private BitmapFont titulo;

    public WorldScreen(ChemistryTriviaGame game){
        this.game = game;
        batch = game.getSpriteBatch();
        camera = game.getOrthoCamera();
        stage = game.getStage();
        Gdx.input.setInputProcessor(stage);
        background = game.getBackground();
        titulo = new BitmapFont(Gdx.files.internal("fontTitle.fnt"));
    }

    public void show() {
        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
        final TextButton level1 = new TextButton("Sales Binarias", uiSkin);
        final TextButton level2 = new TextButton("Compuestos Especiales", uiSkin);
        final TextButton level3 = new TextButton("Hidr\u00F3xidos y \u00D3x\u00E1cidos", uiSkin);
        final TextButton level4 = new TextButton("Hidruros", uiSkin);
        final TextButton level5 = new TextButton("\u00D3xidos", uiSkin);
        final TextButton level6 = new TextButton("Compuestos Cuaternarios", uiSkin);
        final TextButton returnButton = new TextButton("Regresar", uiSkin);

        level1.getLabel().setWrap(true);
        level2.getLabel().setWrap(true);
        level3.getLabel().setWrap(true);
        level4.getLabel().setWrap(true);
        level5.getLabel().setWrap(true);
        level6.getLabel().setWrap(true);

        level1.getLabel().setFontScale(0.5f);
        level2.getLabel().setFontScale(0.5f);
        level3.getLabel().setFontScale(0.5f);
        level4.getLabel().setFontScale(0.5f);
        level5.getLabel().setFontScale(0.5f);
        level6.getLabel().setFontScale(0.4f);

        level1.setWidth(120f);
        level2.setWidth(120f);
        level3.setWidth(120f);
        level4.setWidth(120f);
        level5.setWidth(120f);
        level6.setWidth(120f);

        level1.setHeight(60f);
        level2.setHeight(60f);
        level3.setHeight(60f);
        level4.setHeight(60f);
        level5.setHeight(60f);
        level6.setHeight(60f);


        stage.addActor(level1);
        stage.addActor(level2);
        stage.addActor(level3);
        stage.addActor(level4);
        stage.addActor(level5);
        stage.addActor(level6);

        float col = Gdx.graphics.getWidth() / 3 ;
        float row = Gdx.graphics.getHeight() / 3.5f;

        level1.setPosition(Gdx.graphics.getWidth() * 0.10f, row * 2);
        level2.setPosition(col + Gdx.graphics.getWidth() * 0.10f, row * 2);
        level3.setPosition(col * 2 + Gdx.graphics.getWidth() * 0.10f, row * 2);

        level4.setPosition(Gdx.graphics.getWidth() * 0.10f, row);
        level5.setPosition(col + Gdx.graphics.getWidth() * 0.10f, row);
        level6.setPosition(col * 2 + Gdx.graphics.getWidth() * 0.10f, row);

        level1.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent ie, float x, float y) {
                try {
                    JsonValue jsonValue = new JsonReader().parse(Gdx.files.internal("SalesBinarias.json"));
                    ArrayList<String> compuestos = new ArrayList<String>();
                    ArrayList<String[]> posiblesResp = new ArrayList<String[]>();
                    ArrayList<String> respuestas = new ArrayList<String>();
                    ArrayList<String> tips = new ArrayList<String>();

                    for (JsonValue js : jsonValue) {
                        compuestos.add(js.getString("compuesto"));
                        posiblesResp.add(js.getString("posiblesResp").split(" "));
                        tips.add(js.getString("tips"));
                        respuestas.add(js.getString("respuesta"));
                    }

                    game.setScreen(new LevelScreen(game,
                            new Nivel(compuestos, respuestas, posiblesResp, tips)));
                }catch (Exception e){
                    Gdx.app.log("ERROR", "BAD PARSE ON JSON");
                }
            }

        });


        level2.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent ie, float x, float y){
                try {
                    JsonValue jsonValue = new JsonReader().parse(Gdx.files.internal("CompuestosEsp.json"));
                    ArrayList<String> compuestos = new ArrayList<String>();
                    ArrayList<String[]> posiblesResp = new ArrayList<String[]>();
                    ArrayList<String> respuestas = new ArrayList<String>();
                    ArrayList<String> tips = new ArrayList<String>();

                    for (JsonValue js : jsonValue) {
                        compuestos.add(js.getString("compuesto"));
                        posiblesResp.add(js.getString("posiblesResp").split(" "));
                        tips.add(js.getString("tips"));
                        respuestas.add(js.getString("respuesta"));
                    }

                    game.setScreen(new QuestionScreen(game,
                            new Nivel(compuestos, respuestas, posiblesResp, tips),
                            new Nivel(compuestos, respuestas, posiblesResp, tips)));
                }catch (Exception e){
                    Gdx.app.log("ERROR", "BAD PARSE ON JSON");
                }
            }

        });

        level3.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent ie, float x, float y){
                try {
                    JsonValue jsonValue = new JsonReader().parse(Gdx.files.internal("HidroyOx.json"));
                    ArrayList<String> compuestos = new ArrayList<String>();
                    ArrayList<String[]> posiblesResp = new ArrayList<String[]>();
                    ArrayList<String> respuestas = new ArrayList<String>();
                    ArrayList<String> tips = new ArrayList<String>();

                    for (JsonValue js : jsonValue) {
                        compuestos.add(js.getString("compuesto"));
                        posiblesResp.add(js.getString("posiblesResp").split(" "));
                        tips.add(js.getString("tips"));
                        respuestas.add(js.getString("respuesta"));
                    }

                    game.setScreen(new LevelScreen(game,
                            new Nivel(compuestos, respuestas, posiblesResp, tips)));
                }catch (Exception e){
                    Gdx.app.log("ERROR", "BAD PARSE ON JSON");
                }
            }

        });

        level4.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent ie, float x, float y){
                try {
                    JsonValue jsonValue = new JsonReader().parse(Gdx.files.internal("Hidruros.json"));
                    ArrayList<String> compuestos = new ArrayList<String>();
                    ArrayList<String[]> posiblesResp = new ArrayList<String[]>();
                    ArrayList<String> respuestas = new ArrayList<String>();
                    ArrayList<String> tips = new ArrayList<String>();

                    for (JsonValue js : jsonValue) {
                        compuestos.add(js.getString("compuesto"));
                        posiblesResp.add(js.getString("posiblesResp").split(" "));
                        tips.add(js.getString("tips"));
                        respuestas.add(js.getString("respuesta"));
                    }

                    game.setScreen(new QuestionScreen(game,
                            new Nivel(compuestos, respuestas, posiblesResp, tips),
                            new Nivel(compuestos, respuestas, posiblesResp, tips)));
                }catch (Exception e){
                    Gdx.app.log("ERROR", "BAD PARSE ON JSON");
                }
            }

        });

        level5.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent ie, float x, float y){
                try {
                    JsonValue jsonValue = new JsonReader().parse(Gdx.files.internal("Oxidos.json"));
                    ArrayList<String> compuestos = new ArrayList<String>();
                    ArrayList<String[]> posiblesResp = new ArrayList<String[]>();
                    ArrayList<String> respuestas = new ArrayList<String>();
                    ArrayList<String> tips = new ArrayList<String>();

                    for (JsonValue js : jsonValue) {
                        compuestos.add(js.getString("compuesto"));
                        posiblesResp.add(js.getString("posiblesResp").split(" "));
                        tips.add(js.getString("tips"));
                        respuestas.add(js.getString("respuesta"));
                    }

                    game.setScreen(new LevelScreen(game,
                            new Nivel(compuestos, respuestas, posiblesResp, tips)));
                }catch (Exception e){
                    Gdx.app.log("ERROR", "BAD PARSE ON JSON");
                }
            }

        });


        level6.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent ie, float x, float y){
                try {
                    JsonValue jsonValue = new JsonReader().parse(Gdx.files.internal("Cuaternarios.json"));
                    ArrayList<String> compuestos = new ArrayList<String>();
                    ArrayList<String[]> posiblesResp = new ArrayList<String[]>();
                    ArrayList<String> respuestas = new ArrayList<String>();
                    ArrayList<String> tips = new ArrayList<String>();

                    for (JsonValue js : jsonValue) {
                        compuestos.add(js.getString("compuesto"));
                        posiblesResp.add(js.getString("posiblesResp").split(" "));
                        tips.add(js.getString("tips"));
                        respuestas.add(js.getString("respuesta"));
                    }

                    game.setScreen(new LevelScreen(game,
                            new Nivel(compuestos, respuestas, posiblesResp, tips)));
                }catch (Exception e){
                    Gdx.app.log("ERROR", "BAD PARSE ON JSON");
                }
            }

        });


        returnButton.setPosition(0, Gdx.graphics.getHeight() - returnButton.getHeight());
        returnButton.setWidth(100f);
        returnButton.getLabel().setFontScale(0.5f);
        returnButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent ie, float x, float y){
                WorldScreen.this.game.setScreen(new MenuScreen(WorldScreen.this.game));
            }

        });

        stage.addActor(returnButton);

        backx = 2147;
        tiempo = TimeUtils.nanoTime();

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, backx - 2417, 0);
        batch.draw(background, backx, 0);
        titulo.draw(batch, "Temas", Gdx.graphics.getWidth() / 2 - 10 * 5,
                Gdx.graphics.getHeight() - titulo.getSpaceWidth() - 10);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        if (TimeUtils.nanoTime() - tiempo > 100000){
            backx -= 1;
            tiempo = TimeUtils.nanoTime();
        }

        if(backx == 0){
            backx = 2417;
        }

    }

    public void resize(int width, int height) {

    }


    public void pause() {

    }


    public void resume() {

    }


    public void hide() {

    }


    public void dispose() {
        stage.clear();
    }
}
