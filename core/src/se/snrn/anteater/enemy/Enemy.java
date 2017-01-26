package se.snrn.anteater.enemy;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.BSpline;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import se.snrn.anteater.Debuggable;
import se.snrn.anteater.Renderable;
import se.snrn.anteater.Updatable;

public class Enemy implements Updatable, Renderable, Debuggable {

    private Vector2 vectorPos;
    private float x;
    private float y;
    private Sprite sprite;
    private Rectangle rect;
    private BSpline<Vector2> spline;

    public Enemy(float x, float y, Sprite sprite, BSpline<Vector2> spline) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        rect = sprite.getBoundingRectangle();
        this.spline = spline;
        rect.setPosition(x, y);
        vectorPos = new Vector2(x, y);




    }

    @Override
    public void update(float delta) {

        spline.valueAt(vectorPos, delta);
        x = vectorPos.x;
        y = vectorPos.y;
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

    public Rectangle getRect() {
        return rect;
    }
}
