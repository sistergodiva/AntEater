package se.snrn.anteater.enemy;


import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Polyline;
import se.snrn.anteater.assets.ResourceManager;

import java.util.ArrayList;

public class EnemyFactory {



    public static Enemy createEnemy(float x, float y, ArrayList<PolylineMapObject> polylines){
         Polyline polyline = polylines.get(0).getPolyline();

        return new Enemy(x,y, ResourceManager.snake, SplineFactory.getSplineFromPolyLine(polyline, polyline));
    }

}


