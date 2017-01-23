package se.snrn.anteater;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class MapReader {

    private ArrayList<Rectangle> walls;
    private TiledMap tiledMap;
    private MapLayer layer;
    private ArrayList<HoneyComb> honeyCombs;

    public MapReader(TiledMap tiledMap) {

        honeyCombs = new ArrayList<HoneyComb>();
        walls = new ArrayList<Rectangle>();
        this.tiledMap = tiledMap;

         layer = this.tiledMap.getLayers().get("pickups");


        for (MapObject object: layer.getObjects()
             ) {
            honeyCombs.add(new HoneyComb(object));
        }

        layer = this.tiledMap.getLayers().get("walls");


        for (MapObject object: layer.getObjects()
                ) {
            RectangleMapObject wall = (RectangleMapObject) object;
            walls.add(wall.getRectangle());
        }


    }

    public ArrayList<HoneyComb> getHoneyCombs() {
        return honeyCombs;
    }

    public ArrayList<Rectangle> getWalls() {
        return walls;
    }
}
