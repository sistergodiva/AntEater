package se.snrn.anteater;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.snrn.anteater.gameworld.GameWorld;

public class AntEater extends Game {
    SpriteBatch batch;
    private GameWorld gameWorld;


    @Override
    public void create() {
        batch = new SpriteBatch();
        gameWorld = new GameWorld(batch);
        setScreen(gameWorld);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
