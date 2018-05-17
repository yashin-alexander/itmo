import models.User;
import play.Application;
import play.GlobalSettings;
import dao.UserDaoImpl;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

import java.util.List;

public class Global extends GlobalSettings {

@Transactional
public void onStart(Application app) {
//    UserInfoDB.addUserInfo("John Smith", "smith@example.com", "password");
//    UserInfoDB.addUserInfo("John Smith", "1", "1");

//    UserDaoImpl UserDaoImpl = new UserDaoImpl();
//
//
//    List<User> list = UserDaoImpl.findList();
//
//    for(int i = 0; i< list.size(); i++){
//      System.out.println("HUI");
//      System.out.println(list.get(i).getName());
//
//    }

  }
}
