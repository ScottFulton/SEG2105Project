package project.seg.householdchoremanager;

/**
 * Created by Kevin on 27/11/17.
 *
 * Container class for Users. Contains information regarding a given user.
 * Also helps for passing information to an from the database
 *
 * Security is very clearly not something we're worrying about.
 */

public class User {
    private String name;
    private String password; //Yeah security isn't our top priority around these parts...
    private boolean adult;  //Yes/no if this user is an adult
    private int points;

    public User(String nom, String passwd, boolean adlt, int ponts){
        name = nom;
        password = passwd;
        adult = adlt;
        points = ponts;
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

    public int getPoints(){
        return points;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setAdult(boolean adult){
        this.adult = adult;
    }

    public void setPoints(int howMany){
        this.points = howMany;
    }
}
