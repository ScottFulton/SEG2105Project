package project.seg.householdchoremanager;

/**
 * Created by Kevin on 27/11/17.
 *
 * Container class for Users. Security is very clearly not something we're worrying about
 */

public class User {
    private String name;
    private String password; //Yeah security isn't our top priority around these parts...
    private boolean adult;  //Yes/no if this user is an adult

    public User(String nom, String passwd, boolean adlt){
        name = nom;
        password = passwd;
        adult = adlt;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public boolean isAdult(){
        return adult;
    }
}