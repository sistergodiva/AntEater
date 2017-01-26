package se.snrn.anteater.gameworld;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import se.snrn.anteater.pickups.HoneyComb;

import java.util.ArrayList;

public class MapReader {

    private ArrayList<PolylineMapObject> enemyPaths;
    private ArrayList<Rectangle> walls;
    private TiledMap tiledMap;
    private MapLayer layer;
    private ArrayList<HoneyComb> honeyCombs;

    public MapReader(TiledMap tiledMap) {

        honeyCombs = new ArrayList<HoneyComb>();
        walls = new ArrayList<Rectangle>();
        enemyPaths = new ArrayList<PolylineMapObject>();
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

        layer = this.tiledMap.getLayers().get("enemy");


        for (MapObject object: layer.getObjects()
                ) {
            PolylineMapObject enemyPath = (PolylineMapObject) object;
            enemyPaths.add(enemyPath);
            System.out.println(enemyPath.getPolyline().getVertices().length);
        }



    }

    public ArrayList<HoneyComb> getHoneyCombs() {
        return honeyCombs;
    }

    public ArrayList<Rectangle> getWalls() {
        return walls;
    }

    public ArrayList<PolylineMapObject> getEnemyPaths() {
        return enemyPaths;
    }
}
