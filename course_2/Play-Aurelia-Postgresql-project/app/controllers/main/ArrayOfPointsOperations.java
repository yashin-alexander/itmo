package controllers.main;

import com.fasterxml.jackson.databind.node.ObjectNode;
import dao.PointJpaDaoImpl;
import models.Point;
import play.libs.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander on 30.06.17.
 */
public class ArrayOfPointsOperations {

    public static ObjectNode ArrayByOwnerAsJson(double r, String owner) {

        ObjectNode content = Json.newObject();

        List<Point> pointList;
        PointJpaDaoImpl pointImpl = new PointJpaDaoImpl();

        pointList = pointImpl.findByOwner(owner);

        content.put("fields", pointList.size());
        List<Double> X = new ArrayList<>();
        List<Double> Y = new ArrayList<>();
        List<Double> R = new ArrayList<>();
        List<Boolean> isInside = new ArrayList<>();
        List<Boolean> color = new ArrayList<>();

        for(int i=0; i<pointList.size(); i++){

            Point tmp = pointList.get(i);

            X.add(tmp.getX());
            Y.add(tmp.getY());
            R.add(tmp.getR());
            isInside.add(tmp.getIsInside());

            if(SinglePointOperations.checkArea(tmp.getX(), tmp.getY(), r))
                color.add(true);
            else
                color.add(false);

        }

        content.put("X", X.toString());
        content.put("Y", Y.toString());
        content.put("R", R.toString());
        content.put("isInside", isInside.toString());
        content.put("color", color.toString());

        return content;
    }

    public static void ArrayRemove(String owner){
        PointJpaDaoImpl pointImpl = new PointJpaDaoImpl();

        pointImpl.deleteByOwner(owner);
        return;
    }
}
