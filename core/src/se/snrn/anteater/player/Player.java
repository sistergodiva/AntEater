package se.snrn.anteater.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import se.snrn.anteater.Debuggable;
import se.snrn.anteater.Renderable;
import se.snrn.anteater.Updatable;
import se.snrn.anteater.gameworld.Collision;
import se.snrn.anteater.gameworld.CollisionManager;
import se.snrn.anteater.gameworld.RectSide;
import se.snrn.anteater.player.playerstates.GroundState;

import static se.snrn.anteater.gameworld.GameWorld.GRAVITY;
import static se.snrn.anteater.player.PlayerInput.LAND;
import static se.snrn.anteater.player.PlayerInput.LEFT_STOP;
import static se.snrn.anteater.player.PlayerInput.RIGHT_STOP;

public class Player implements Updatable, Renderable, Debuggable {


    private Sprite sprite;
    private float x;
    private float y;
    private Rectangle rect;

    private PlayerState playerState;
    private Direction direction;
    private Vector2 velocity;
    private Vector2 north;
    private Vector2 east;
    private Vector2 south;
    private Vector2 west;

    private CollisionManager collisionManager;
    private Collision feetCollision;
    private Rectangle nextRect;

    public Player(float x, float y, CollisionManager collisionManager) {
        this.collisionManager = collisionManager;
        this.sprite = new Sprite(new Texture("anteater.png"));
        this.x = x;
        this.y = y;
        sprite.setPosition(x, y);
        rect = sprite.getBoundingRectangle();
        playerState = new GroundState(this);
        velocity = new Vector2(0, 0);
        direction = Direction.LEFT;
        north = new Vector2(this.x + rect.getWidth() / 2, this.y + rect.getHeight());
        east = new Vector2(this.x + rect.getWidth(), this.y + rect.getHeight() / 2);
        south = new Vector2(this.x + rect.getWidth() / 2, this.y);
        west = new Vector2(this.x, this.y + rect.getHeight() / 2);
        feetCollision = null;
        nextRect = new Rectangle(x, y, rect.getWidth(), rect.getHeight());
    }

    @Override
    public void update(float delta) {
        playerState.update(delta);


        if (velocity.x > 0) {
            direction = Direction.RIGHT;
        } else if (velocity.x < 0) {
            direction = Direction.LEFT;
        }

        velocity.add(GRAVITY);

        Vector2 bajs = new Vector2(100, 0);





        nextRect.setPosition(x + velocity.x, y + velocity.y);




        if (collisionManager.getWallCollision(nextRect) != null) {
            Rectangle wall = collisionManager.getWallCollision(nextRect);
            if (x > wall.getX() - 4) {
                if (collisionManager.getSideWithRect(nextRect, wall) == RectSide.RIGHT) {
                    x = wall.getX() + wall.getWidth();
                    handleInput(LEFT_STOP);
                }
            }
            if (x < wall.getX() + 4) {
                if (collisionManager.getSideWithRect(nextRect, wall) == RectSide.LEFT) {
                    x = wall.getX() - rect.getWidth();
                    handleInput(RIGHT_STOP);
                }
            }
        }

        if (collisionManager.getGroundCollision(nextRect) != null) {
            Rectangle ground = collisionManager.getGroundCollision(nextRect);
            handleInput(LAND);
            velocity.y = MathUtils.clamp(velocity.y, 0, 99);
            if (y > ground.getY() - 4) {
                y = ground.getY() + ground.getHeight();
            }
        }


        x += velocity.x;
        y += velocity.y;

        rect.setPosition(x, y);

        north.set(x + rect.getWidth() / 2, y + rect.getHeight());
        east.set(x + rect.getWidth(), y + rect.getHeight() / 2);
        south.set(x + rect.getWidth() / 2, y);
        west.set(x, y + rect.getHeight() / 2);

    }

    public void handleInput(PlayerInput playerInput) {

        PlayerState tmpState = playerState.handleInput(playerInput);
        if (tmpState != null) {
            if (playerState != null) {
                playerState.exit();
                System.out.println("exited " + playerState);
            }
            playerState = tmpState;
            playerState.enter();
            System.out.println("entered " + playerState);

        }
    }


    @Override
    public void render(Batch batch) {
        sprite.setFlip(direction == Direction.LEFT, false);
        sprite.setPosition(x, y);
        sprite.draw(batch);

    }

    @Override
    public void debug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        shapeRenderer.rect(nextRect.getX(), nextRect.getY(), nextRect.getWidth(), nextRect.getHeight());
        shapeRenderer.circle(north.x, north.y, 4);
        shapeRenderer.circle(east.x, east.y, 4);
        shapeRenderer.circle(south.x, south.y, 4);
        shapeRenderer.circle(west.x, west.y, 4);
    }


    public void addYVelocity(float yVelocity) {
        velocity.y += yVelocity;
    }

    public void addXVelocity(float xVelocity) {
        velocity.x += xVelocity;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setXVelocity(float xVelocity) {
        this.velocity.x = xVelocity;
    }

    public void setYVelocity(float yVelocity) {
        this.velocity.y = yVelocity;
    }

    public Rectangle getRect() {
        return rect;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public Direction getDirection() {
        return direction;
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
