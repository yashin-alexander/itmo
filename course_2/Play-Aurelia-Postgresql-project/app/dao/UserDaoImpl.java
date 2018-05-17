package dao;

import models.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

import javax.persistence.EntityManager;
import java.util.List;




/**
 * Created by alexander on 30.06.17.
 */
public class UserDaoImpl implements UserDao{

    @Override
    public void createUser(User user){
        user.setPassword(user.toHashCode(user.getPassword()));
        JPA.em().persist(user);
    }

    @Override
    public boolean isUserExist(String name){
        return !JPA.em().createQuery(
                "SELECT user FROM User user WHERE user.name=:name")
                .setParameter("name", name)
                .getResultList().isEmpty();
    }

    @Override
    public boolean isUserValid(String name, String password) {
        password = User.toHashCode(password);

        return !JPA.em().createQuery(
                "SELECT user FROM User user WHERE user.name=:name AND user.password=:password")
                .setParameter("name", name)
                .setParameter("password", password)
                .getResultList().isEmpty();
    }

    @Override
    public User getUser(String name){
        System.out.println(name + "= Name");

        List <User> list = JPA.em().createQuery("SELECT user FROM User user WHERE user.name=:name")
            .setParameter("name", name).getResultList();


        System.out.println("SIZE = " + list.size());
        User user ;
        try{
                user = list.get(0);
        }catch(Exception e){
            user = new User("a", "a");
        }
            return user;
    }
}
