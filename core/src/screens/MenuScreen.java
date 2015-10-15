package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Chromz on 10/14/2015.
 */
public class MenuScreen implements Screen {
    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private float backx;
    private long tiempo;
    private float backy;
    private TextButton inicio;
    private TextButton salir;
    private Label titulo;
    private Stage stage;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin();
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        titulo = new Label("Chemi Trivia", skin);
        titulo.setColor(Color.BLACK);

        inicio = new TextButton("Inicio", skin);
        salir = new TextButton("Salir", skin);

        stage.addActor(inicio);
        stage.addActor(salir);
        stage.addActor(titulo);

        inicio.setX(Gdx.graphics.getWidth() / 2 - inicio.getWidth() / 2);
        inicio.setY(Gdx.graphics.getHeight() / 2);

        salir.setX(Gdx.graphics.getWidth() / 2 - salir.getWidth() / 2);
        salir.setY(Gdx.graphics.getHeight() / 2 - inicio.getHeight() - 10);

        titulo.setX(Gdx.graphics.getWidth() / 2 - titulo.getWidth() / 2);
        titulo.setY(Gdx.graphics.getHeight() / 2 + inicio.getHeight() + 20);

        backx = 800;
        backy = 800;
        tiempo = TimeUtils.nanoTime();

        background = new Texture(Gdx.files.internal("Quemi.jpg"));
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 400);
    }

    @Override
    public void dispose() {

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
        batch.begin();
        batch.draw(background, backx - 800, backy - 800);
        batch.draw(background, backx, backy);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

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
        inicio.setX(Gdx.graphics.getWidth() / 2 - inicio.getWidth() / 2);
        inicio.setY(Gdx.graphics.getHeight() / 2);

        salir.setX(Gdx.graphics.getWidth() / 2 - salir.getWidth() / 2);
        salir.setY(Gdx.graphics.getHeight() / 2 - inicio.getHeight() - 10);

        titulo.setX(Gdx.graphics.getWidth() / 2 - titulo.getWidth() / 2);
        titulo.setY(Gdx.graphics.getHeight() / 2 + inicio.getHeight() + 20);
    }

    @Override
    public void pause() {

    }
}
