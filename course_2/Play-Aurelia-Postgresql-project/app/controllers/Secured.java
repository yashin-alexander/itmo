/**
 * Created by alexander on 28.08.17.
 */
package controllers;

import dao.UserDaoImpl;
import models.User;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.Http.Context;

public class Secured extends Security.Authenticator {

    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("name");
    }

    @Override
    public Result onUnauthorized(Context context) {
        return redirect(routes.Application.index());
    }

    public String getUser(Context ctx) {
        return ctx.session().get("name");
    }

    public boolean isLoggedIn(Context ctx) {
        return (getUser(ctx) != null);
    }

    public User getUserInfo(Context ctx) {
        return (isLoggedIn(ctx) ? userDao.getUser(getUser(ctx)) : null);
    }

    public User createUserInfo(Context ctx){
        return (isLoggedIn(ctx) ? userDao.getUser(getUser(ctx)) : null);
    }
}

