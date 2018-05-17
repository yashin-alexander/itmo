package controllers;

import dao.PointJpaDaoImpl;
import dao.UserDaoImpl;
import models.Point;
import models.User;
import play.libs.Json;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.Validators;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.Index;
import play.mvc.Security;
import controllers.main.ArrayOfPointsOperations;
import controllers.main.SinglePointOperations;


@Transactional
public class Application extends Controller {

    public Result index(){
        return ok(Index.render());
    }

    public Result register(String username, String password) {

        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User(username, password);

        ObjectNode content = Json.newObject();

        if (userDao.isUserExist(user.getName())) {
            content.put("status", 1);
            return ok(content);
        }

        Validators validators = new Validators();
        if (validators.isNameCorrect(user.getName())) {
            content.put("status", 2);
            return ok(content);
        }

        if (validators.isPasswordCorrect(user.getPassword())) {
            content.put("status", 3);
            return ok(content);
        }

            userDao.createUser(user);
            content.put("status", 0);
            content.put("login", username);



            session("name", username);

            System.out.println(username);
            return ok(content);
        }

    public Result login(String username, String password) {
        UserDaoImpl userDao = new UserDaoImpl();
        ObjectNode content = Json.newObject();

        System.out.println("LOGIN");
        if(userDao.isUserValid(username, password)){
            content.put("status", 0);
            content.put("login", username);
            return ok(content);
        }
        else if (userDao.isUserExist(username)) {
            content.put("status", 1);
            return ok(content);
        }
        else {
            content.put("status", 2);
            return ok(content);
        }
    }


    @Security.Authenticated(Secured.class)
    public Result AddPoint(Double x, Double y, Double r) {
        Secured secured = new Secured();
        User user = secured.getUserInfo(ctx());

        return ok(SinglePointOperations.PointAsJson(x,y,r, user.getName()));
    }


    @Security.Authenticated(Secured.class)
    public Result ChangeRadius(String r){
        Secured secured = new Secured();
        User user = secured.getUserInfo(ctx());

        return ok(ArrayOfPointsOperations.ArrayByOwnerAsJson(Double.valueOf(r), user.getName()));
    }

    @Security.Authenticated(Secured.class)
    public Result RemovePoints(){
        Secured secured = new Secured();
        User user = secured.getUserInfo(ctx());
        ArrayOfPointsOperations.ArrayRemove(user.getName());
        return ok();
    }
}
