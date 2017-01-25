package se.snrn.anteater.enemy;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import se.snrn.anteater.Debuggable;
import se.snrn.anteater.Renderable;
import se.snrn.anteater.Updatable;

public class Enemy implements Updatable, Renderable, Debuggable {

    private float x;
    private float y;
    private Sprite sprite;
    private Rectangle rect;

    public Enemy(float x, float y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        rect = sprite.getBoundingRectangle();
        rect.setPosition(x, y);
    }

    @Override
    public void update(float delta) {
        rect.setPosition(x, y);
    }

    @Override
    public void render(Batch batch) {
        sprite.setPosition(x, y);
        sprite.draw(batch);
    }

    @Override
    public void debug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }
}
