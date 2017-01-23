package se.snrn.anteater.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import se.snrn.anteater.Debuggable;
import se.snrn.anteater.Renderable;
import se.snrn.anteater.Updatable;
import se.snrn.anteater.player.playerstates.GroundState;

import static se.snrn.anteater.GameWorld.GRAVITY;

public class Player implements Updatable, Renderable, Debuggable {


    private Sprite sprite;
    private float x;
    private float y;
    private Rectangle rect;
    private Rectangle nextRect;
    private Vector2 nextFeet;

    private PlayerState playerState;
    private Direction direction;
    private Vector2 velocity;
    private Vector2 playerVector;
    private Vector2 feet;
    private Vector2 nextPlace;

    public Player(float x, float y) {
        this.sprite = new Sprite(new Texture("anteater.png"));
        this.x = x;
        this.y = y;
        sprite.setPosition(x, y);
        rect = sprite.getBoundingRectangle();
        playerState = new GroundState(this);
        velocity = new Vector2(0, 0);
        feet = new Vector2(x + rect.getWidth() / 2, y);
        direction = Direction.LEFT;
        playerVector = new Vector2(this.x, this.y);
        nextPlace = new Vector2(0, 0);
        nextFeet = new Vector2();
        nextRect = new Rectangle(nextPlace.x, nextPlace.y, sprite.getWidth(), sprite.getHeight());
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

    public Vector2 getNextPlace() {
        return nextPlace;
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
        playerVector.add(velocity);
        rect.setPosition(playerVector.x, playerVector.y);
        feet.set(playerVector.x + rect.getWidth() / 2, playerVector.y);


        nextPlace.set(playerVector);
        nextPlace.add(velocity);

        nextRect.setPosition(nextPlace);
        nextFeet.set(nextPlace.x + rect.getWidth() / 2, nextPlace.y);

    }

    @Override
    public void render(Batch batch) {
        sprite.setFlip(direction == Direction.LEFT, false);
        sprite.setPosition(playerVector.x, playerVector.y);
        sprite.draw(batch);

    }

    @Override
    public void debug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        shapeRenderer.rect(nextRect.getX(), nextRect.getY(), nextRect.getWidth(), nextRect.getHeight());
        shapeRenderer.circle(nextFeet.x, nextFeet.y, 4);

    }

    public float getX() {
        return playerVector.x;
    }

    public float getY() {
        return playerVector.y;
    }

    public void setX(float x) {
        playerVector.x = x;
    }

    public void setY(float y) {
        playerVector.y = y;
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

    public Vector2 getFeet() {
        return feet;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public Direction getDirection() {
        return direction;
    }

    public Rectangle getNextRect() {
        return nextRect;
    }

    public Vector2 getNextFeet() {
        return nextFeet;
    }
}
