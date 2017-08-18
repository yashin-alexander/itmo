package dao;

import java.util.List;
import models.User;

/**
 * Created by alexander on 30.06.17.
 */
public interface UserDao {
    void createUser(User u);
    boolean isUserExist(String name);
    boolean isUserValid(String name, String password);
    User getUser(String name);
}