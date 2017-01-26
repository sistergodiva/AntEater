package se.snrn.anteater.gameworld;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import se.snrn.anteater.pickups.HoneyComb;
import se.snrn.anteater.Updatable;


import java.util.ArrayList;

import static java.lang.Math.abs;
import static se.snrn.anteater.gameworld.RectSide.TOP;


public class CollisionManager implements Updatable {

    private final ArrayList<HoneyComb> honeyCombs;
    private ArrayList<Rectangle> walls;

    public CollisionManager(ArrayList<HoneyComb> honeyCombs, ArrayList<Rectangle> walls) {


        this.honeyCombs = honeyCombs;
        this.walls = walls;
    }


    public boolean isInsideWall(Vector2 point) {
        for (Rectangle wall : walls) {
            if (getSideWithPoint(point, wall) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(float delta) {

    }

    public RectSide getSideWithPoint(Vector2 a, Rectangle b) {
        double w = 0.5 * (1 + b.getWidth());
        double h = 0.5 * (1 + b.getHeight());
        Vector2 vectorA;
        Vector2 vectorB = new Vector2();
        vectorA = a;
        b.getCenter(vectorB);
        double dx = vectorA.x - vectorB.x;
        double dy = vectorA.y - vectorB.y;

        return getSide(dx, dy, w, h);
    }

    public RectSide getSide(double dx, double dy, double w, double h) {
        if (abs(dx) <= w && abs(dy) <= h) {
            double wy = w * dy;
            double hx = h * dx;

            if (wy > hx)
                if (wy > -hx) {
                    return TOP;
                } else {
                    return RectSide.LEFT;
                }
            else if (wy > -hx) {
                return RectSide.RIGHT;
            } else {
                return RectSide.BOTTOM;
            }
        }
        return null;
    }

    public RectSide getSideWithRect(Rectangle a, Rectangle b) {
        double w = 0.5 * (a.getWidth() + b.getWidth());
        double h = 0.5 * (a.getHeight() + b.getHeight());
        Vector2 vectorA = new Vector2();
        Vector2 vectorB = new Vector2();
        a.getCenter(vectorA);
        b.getCenter(vectorB);
        double dx = vectorA.x - vectorB.x;
        double dy = vectorA.y - vectorB.y;

        return getSide(dx, dy, w, h);
    }

    public Collision getCollision(Vector2 point) {
        for (Rectangle wall : walls) {
            RectSide rectSide = getSideWithPoint(point, wall);

            if (rectSide != null) {
                return new Collision(wall, rectSide);
            }
        }
        return null;
    }

    public Collision getRectCollision(Rectangle rectangle) {
        for (Rectangle wall : walls) {
            RectSide rectSide = getSideWithRect(rectangle, wall);

            if (rectSide != null) {
                return new Collision(wall, rectSide);
            }
        }
        return null;
    }

    public Rectangle getGroundCollision(Rectangle rectangle) {
        for (Rectangle wall : walls) {
            RectSide rectSide = getSideWithRect(rectangle, wall);

            if (rectSide == TOP) {
                return wall;
            }
        }
        return null;
    }

    public Rectangle getWallCollision(Rectangle rectangle) {
        for (Rectangle wall : walls) {
            RectSide rectSide = getSideWithRect(rectangle, wall);

            if (rectSide == RectSide.LEFT || rectSide == RectSide.RIGHT) {
                return wall;
            }
        }
        return null;
    }
}
