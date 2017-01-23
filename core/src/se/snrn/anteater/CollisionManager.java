package se.snrn.anteater;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import se.snrn.anteater.player.Player;


import java.util.ArrayList;

import static java.lang.Math.abs;
import static se.snrn.anteater.player.PlayerInput.LAND;
import static se.snrn.anteater.player.PlayerInput.LEFT_STOP;
import static se.snrn.anteater.player.PlayerInput.RIGHT_STOP;


public class CollisionManager implements Updatable {

    private final Player player;
    private final ArrayList<HoneyComb> honeyCombs;
    private ArrayList<Rectangle> walls;

    public CollisionManager(Player player, ArrayList<HoneyComb> honeyCombs, ArrayList<Rectangle> walls) {

        this.player = player;
        this.honeyCombs = honeyCombs;
        this.walls = walls;
    }


    @Override
    public void update(float delta) {
        for (HoneyComb honeyComb : honeyCombs) {
            if (honeyComb.getRect().overlaps(player.getRect())) {
                honeyComb.setPickedUp(true);
            }
        }

        for (Rectangle wall : walls) {
            if(getSideWithRect(player.getNextRect(),wall) == RectSide.LEFT) {
                player.handleInput(RIGHT_STOP);
            }

            if(getSideWithRect(player.getNextRect(),wall) == RectSide.RIGHT) {
                player.handleInput(LEFT_STOP);
            }


            if (getSideWithPoint(player.getNextFeet(), wall) == RectSide.TOP) {
                player.setYVelocity(MathUtils.clamp(player.getVelocity().y, 0, 99));
                if (player.getFeet().y < wall.getY() + wall.getHeight()) {
                    player.setY(wall.getY() + wall.getHeight());
                    player.handleInput(LAND);
                }
            }
        }
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
                    return RectSide.TOP;
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
}
