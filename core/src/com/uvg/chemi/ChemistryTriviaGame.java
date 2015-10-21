package com.uvg.chemi;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.uvg.chemi.screens.MenuScreen;

/**
 * Created by Chromz(Rodrigo Custodio) on 10/15/2015.
 */
public class ChemistryTriviaGame extends Game {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture background;
    private Stage stage;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

	@Override
	public void create () {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        stage = new Stage(new ScreenViewport());
        background = new Texture(Gdx.files.internal("Quemi.jpg"));
        MenuScreen menu = new MenuScreen(this);
        setScreen(menu);
    }

    public SpriteBatch getSpriteBatch(){
        return batch;
    }

    public Stage getStage(){
        return stage;
    }

    public Texture getBackground(){
        return background;
    }

    public OrthographicCamera getOrthoCamera(){
        return camera;
    }

    @Override
    public  void setScreen(Screen screen){
        if (this.screen != null) this.screen.dispose();
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }



}
