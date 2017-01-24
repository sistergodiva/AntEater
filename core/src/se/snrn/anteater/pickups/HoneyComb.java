package se.snrn.anteater.pickups;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Rectangle;
import se.snrn.anteater.Debuggable;
import se.snrn.anteater.Renderable;
import se.snrn.anteater.Updatable;
import se.snrn.anteater.gameworld.GameWorld;

public class HoneyComb implements Updatable, Renderable, Debuggable {

    private MapObject object;
    private Sprite sprite;
    private Rectangle rect;
    private boolean pickedUp;


    public HoneyComb(MapObject object) {

        this.object = object;
        TiledMapTileMapObject tiledMapTileMapObject = (TiledMapTileMapObject) object;
        rect = new Rectangle(tiledMapTileMapObject.getX(), tiledMapTileMapObject.getY(), GameWorld.TILE_SIZE, GameWorld.TILE_SIZE);

        sprite = new Sprite(tiledMapTileMapObject.getTextureRegion().getTexture());


    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        sprite.setPosition(rect.getX(), rect.y);
        sprite.draw(batch);
    }

    @Override
    public void debug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = true;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }
}
