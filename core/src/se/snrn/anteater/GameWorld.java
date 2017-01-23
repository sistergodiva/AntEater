package se.snrn.anteater;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import se.snrn.anteater.player.Player;


import java.util.ArrayList;

public class GameWorld implements Screen {

    public static Vector2 GRAVITY = new Vector2(0, -1f);
    private ArrayList<Rectangle> walls;
    private ArrayList<HoneyComb> honeyCombs;

    private Batch batch;
    private TmxMapLoader tmxMapLoader;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private OrthographicCamera orthographicCamera;
    private Viewport viewport;
    private Player player;
    private InputManager inputManager;
    private ShapeRenderer shapeRenderer;
    private MapReader mapReader;
    public static final float TILE_SIZE = 32;
    CollisionManager collisionManager;


    public GameWorld(Batch batch) {
        this.batch = batch;
        orthographicCamera = new OrthographicCamera();
        viewport = new FitViewport(1280, 720, orthographicCamera);
        orthographicCamera.update();
        viewport.apply();
        tmxMapLoader = new TmxMapLoader();
        tiledMap = tmxMapLoader.load("map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledMapRenderer.setView(orthographicCamera);
        player = new Player(32, 32);
        inputManager = new InputManager(player);
        shapeRenderer = new ShapeRenderer();

        mapReader = new MapReader(tiledMap);

        honeyCombs = mapReader.getHoneyCombs();
        walls = mapReader.getWalls();

        collisionManager = new CollisionManager(player, honeyCombs, walls);

        Gdx.input.setInputProcessor(inputManager);

    }

    @Override
    public void show() {
        orthographicCamera.position.set(player.getX(), player.getY(), 0);
        orthographicCamera.update();
        viewport.apply();
        Gdx.input.setInputProcessor(inputManager);

    }

    private void update(float delta) {
        player.update(delta);
        collisionManager.update(delta);
        orthographicCamera.position.set(player.getX(), player.getY(), 0);
        orthographicCamera.update();

    }


    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        tiledMapRenderer.setView(orthographicCamera);
        batch.setProjectionMatrix(orthographicCamera.combined);
        batch.begin();
        tiledMapRenderer.render();

        batch.end();
        batch.begin();
        player.render(batch);
        for (HoneyComb honeyComb: honeyCombs
                ) {
            if(!honeyComb.isPickedUp()) {
                honeyComb.render(batch);
            }
        }
        batch.end();
        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        player.debug(shapeRenderer);
        for (HoneyComb honeyComb: honeyCombs
                ) {
            honeyComb.debug(shapeRenderer);
        }
        for (Rectangle wall: walls
                ) {
            shapeRenderer.rect(wall.getX(), wall.getY(),wall.getWidth(),wall.getHeight());
        }
        shapeRenderer.end();
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

    }
}
