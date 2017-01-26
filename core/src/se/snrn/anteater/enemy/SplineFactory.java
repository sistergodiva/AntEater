package se.snrn.anteater.enemy;

import com.badlogic.gdx.math.BSpline;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Vector2;

import static se.snrn.anteater.gameworld.GameWorld.TILE_SIZE;

public class SplineFactory {


    public static BSpline getSplineFromPolyLine(Polyline polyline, Polyline polyline2){

        Vector2 points[] = new Vector2[2];
        points[0] = new Vector2(polyline.getX(),polyline.getY()-TILE_SIZE);
        points[1] = new Vector2(polyline2.getX()+polyline2.getLength(),polyline2.getY()-TILE_SIZE);
        return new BSpline(points,0,true);
    }

}
