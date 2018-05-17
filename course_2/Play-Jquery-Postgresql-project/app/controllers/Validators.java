package controllers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexander on 13.07.17.
 */
public class Validators {

    public boolean isNameCorrect(String field){

        Pattern pattern = Pattern.compile("^[a-z_0-9]*@[a-z]*.[a-z]{2,15}$");
        Matcher matcher = pattern.matcher(field);
        return !matcher.matches();
    }
    public boolean isPasswordCorrect(String password){
        Pattern pattern = Pattern.compile(".{4,}");
        Matcher matcher = pattern.matcher(password);
        return !matcher.matches();
    }
}
