package controllers;

import dao.UserDaoImpl;
import models.User;
import controllers.Validators;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.RegisterFormData;
import views.html.Main;
import views.html.Login;
import views.html.Index;
import views.formdata.LoginFormData;
import play.mvc.Security;
import controllers.main.ArrayOfPointsOperations;
import controllers.main.SinglePointOperations;
import views.html.Register;

@Transactional
public class Application extends Controller {

    public Result index(){
        return ok(Index.render());
    }

    public Result login() {

        session().clear();
        Form<LoginFormData> loginFormData = Form.form(LoginFormData.class);
        Secured secured = new Secured();

        return ok(Login.render("Login", secured.isLoggedIn(ctx()), secured.getUserInfo(ctx()), loginFormData));
    }

    public Result register() {

        session().clear();
        Form<RegisterFormData> registerFormData = Form.form(RegisterFormData.class);
        Secured secured = new Secured();

        return ok(Register.render("Register", secured.isLoggedIn(ctx()), secured.getUserInfo(ctx()), registerFormData));
    }

    public Result postLogin() {

        Form<LoginFormData> loginFormData = Form.form(LoginFormData.class).bindFromRequest();
        UserDaoImpl userDao = new UserDaoImpl();


        if(userDao.isUserValid(loginFormData.get().name, loginFormData.get().password)){
            session().clear();
            session("name", userDao.getUser(loginFormData.get().name).getName());
            return redirect(routes.Application.main());

        }
        else {
            Secured secured = new Secured();
            flash("error_login", "Login credentials not valid.");
            return badRequest(Login.render("Login", secured.isLoggedIn(ctx()), secured.getUserInfo(ctx()), loginFormData));
        }
    }

    public Result postRegister() {

        Form<RegisterFormData> registerFormData = Form.form(RegisterFormData.class).bindFromRequest();
        Secured secured = new Secured();
        UserDaoImpl userDao = new UserDaoImpl();

        User user = new User(registerFormData.get().registerName, registerFormData.get().registerPassword);

        if(userDao.isUserExist(user.getName())){
            flash("error_register", "User already exist");
            return badRequest(Register.render("Login", secured.isLoggedIn(ctx()), secured.getUserInfo(ctx()), registerFormData));
        }

        Validators validators = new Validators();
        if(validators.isNameCorrect(user.getName())){
            flash("error_register", "Please, turn your name in correct (e-mail/nickname) format!");
            return badRequest(Register.render("Login", secured.isLoggedIn(ctx()), secured.getUserInfo(ctx()), registerFormData));
        }

        if(validators.isPasswordCorrect(user.getPassword())){
            flash("error_register", "Password length must be more than 4 characters!");
            return badRequest(Register.render("Login", secured.isLoggedIn(ctx()), secured.getUserInfo(ctx()), registerFormData));
        }

        userDao.createUser(user);

        session().clear();
        session("name", user.getName());
        return redirect(routes.Application.main());
    }

    @Security.Authenticated(Secured.class)
    public Result logout() {
        session().clear();
        return redirect(routes.Application.login());
    }

    @Security.Authenticated(Secured.class)
        public Result main() {

        Secured secured = new Secured();
        return ok(Main.render("Main", secured.isLoggedIn(ctx()), secured.getUserInfo(ctx())));
    }

    @Security.Authenticated(Secured.class)
    public Result AddPoint(String x, String y, String r) {

        Secured secured = new Secured();
        User user = secured.getUserInfo(ctx());

        return ok(SinglePointOperations.PointAsJson(Double.valueOf(x), Double.valueOf(y), Double.valueOf(r), user.getName()));
    }

    @Security.Authenticated(Secured.class)
    public Result ChangeRadius(String r){

        Secured secured = new Secured();
        User user = secured.getUserInfo(ctx());

        return ok(ArrayOfPointsOperations.ArrayByOwnerAsJson(Double.valueOf(r),user.getName()));
    }

    @Security.Authenticated(Secured.class)
    public Result RemovePoints(){

        Secured secured = new Secured();
        User user = secured.getUserInfo(ctx());
        ArrayOfPointsOperations.ArrayRemove(user.getName());
        return ok();
    }
}
