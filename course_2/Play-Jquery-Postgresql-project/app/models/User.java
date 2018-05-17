package models;


import play.data.validation.Constraints;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="users")
public class User implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="Id")
    private Integer id;
    private String name;
    private String password;

  public User(){};

  public User(String name, String password) {
    this.name = name;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public static String toHashCode(String password){
      String hash = "";
      int letter;
      int garbge;

      System.out.println("INPUT = " + password);

      for (int i =0 ;i < password.length(); i++){
          letter = Integer.valueOf(password.charAt(i));
          if(i<15)
              if (i % 2 == 0)
                  letter = letter + i;
              else
                  letter = letter + 5;
          else
              if (i % 2 == 0)
                  letter = letter - i%15;
              else
                  letter = letter + 4;

          garbge = (letter) * 3;

          hash += (char)letter;
          hash += (char)garbge;
      }

      return hash;
  }
}
