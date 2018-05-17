package dao;



import models.Point;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeArray.lastIndexOf;

/**
 * Created by alexander on 30.06.17.
 */
public class PointJpaDaoImpl implements PointDao {



    @Override
    public List<Point> findAll() {
        return JPA.em().createNamedQuery("Point.findAll").getResultList();
    }

    @Override
    public List <Point> findByOwner(String owner) {
        return JPA.em().createQuery("SELECT point FROM Point point WHERE point.owner=:owner")
                .setParameter("owner", owner)
                .getResultList();
    }

    @Override
    public void addByOwner(Point point){
        JPA.em().persist(point);
        System.out.println("Success! new added point!");
    }

    @Override
    public void deleteByOwner(String owner) {
        List<Point> points = findByOwner(owner);

        for(int i = 0; i<points.size(); i++ ){
            JPA.em().remove(points.get(i));
        }







//        JPA.em().remove(JPA.em().merge("Point"));

        System.out.println("Success! all points delete for user!");
    }
}