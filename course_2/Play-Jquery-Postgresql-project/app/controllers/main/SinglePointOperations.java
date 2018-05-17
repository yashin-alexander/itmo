package controllers.main;

/**
 * Created by alexander on 19.06.17.
 */

import com.fasterxml.jackson.databind.node.ObjectNode;
import dao.PointJpaDaoImpl;
import models.Point;
import play.libs.Json;


public class SinglePointOperations {
    private Double x;
    private Double y;
    private Double r;


    public SinglePointOperations(Double x, Double y, Double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public static boolean checkArea(Double x, Double y, Double r) {
        if (x > 0 && y > 0)
            return (x < r / 2 && y < r / 2 && x * x + y * y < r * r / 4);
        if (x >= 0 && y <= 0)
            return (x < r  && y > -r);
        if (x < 0 && y < 0)
            return (x > -r / 2 && y > -r && -y - 2*x - r < 0);
        else
            return false;
    }

    public static ObjectNode PointAsJson(Double x, Double y, Double r, String owner) {


        ObjectNode content = Json.newObject();
        Boolean isInside;
        String color;

        if (SinglePointOperations.checkArea(x, y, r)) {
            isInside = true;
            color = "green";
        } else {
            isInside = false;
            color = "red";
        }

        Point point = new Point(x,y,r,isInside, color, owner);


        PointJpaDaoImpl pointImpl = new PointJpaDaoImpl();
        pointImpl.addByOwner(point);

        content.put("X", x);
        content.put("Y", y);
        content.put("R", r);
        content.put("isInside", isInside);
        content.put("color", isInside);

        return content;
    }
}
