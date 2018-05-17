package dao;

import models.Point;

import java.util.List;

/**
 * Created by alexander on 30.06.17.
 */

public interface PointDao {
    List<Point> findAll();

    List <Point> findByOwner(String owner);
    void addByOwner(Point point);
    void deleteByOwner(String owner);
}



