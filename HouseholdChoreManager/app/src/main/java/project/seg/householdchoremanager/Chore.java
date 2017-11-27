package project.seg.householdchoremanager;

/**
 * Created by Kevin on 27/11/17.
 *
 * Container class for chores. Easier to use an object rather than pass around absurd function
 * signatures
 */

public class Chore {
    public String name;
    private String description;
    private String[] resources;
    private String group;
    private int reward;

    public Chore(String nam, String desc, String[] rescource, String grp, int rewrd){
        name = nam;
        description = desc;
        resources = rescource;
        group = grp;
        reward = rewrd;
    }

    public Chore(){
        name = null;
        description = null;
        resources = null;
        group = null;
        reward = 0;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getGroup(){
        return group;
    }

    public String getResources(){
        String retString = "";
        for(String resource : resources){
            retString += resource + ", ";
        }
        return retString;
    }

    public int getReward(){
        return reward;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public void setGroup(String group){
        this.group = group;
    }

    //Convert string of format "thing, thing1, thing2" into an array of resources
    public void setResources(String rescs){
        String[] retString;
        retString = rescs.split(", ");
    }

    public void addResource(String resource){

    }

    public void setReward(int reward){
        this.reward = reward;
    }
}
