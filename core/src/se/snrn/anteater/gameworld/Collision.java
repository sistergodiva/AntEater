package se.snrn.anteater.gameworld;


import com.badlogic.gdx.math.Rectangle;

public class Collision {

    private final Rectangle rect;
    private final RectSide side;

    public Collision(Rectangle rect, RectSide side) {

        this.rect = rect;
        this.side = side;
    }

    public Rectangle getRect() {
        return rect;
    }

    public RectSide getRectSide() {
        return side;
    }
}
