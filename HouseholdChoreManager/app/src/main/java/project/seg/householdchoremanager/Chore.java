package project.seg.householdchoremanager;

import android.util.Log;

/**
 * Created by Kevin on 27/11/17.
 *
 * Container class for chores. Easier to use an object rather than pass around absurd function
 * signatures
 */

public class Chore {
    public String name;
    private String description;
    private String resources;
    private String group;
    private int reward;
    private int duedate;
    private String assigned;
    private boolean isCompleted;

    public Chore(String nam, String desc, String rescource, String grp, int rewrd, int dudate,String assign){
        name = nam;
        description = desc;
        resources = rescource;
        group = grp;
        reward = rewrd;
        duedate = dudate;
        assigned = assign;
        isCompleted  = false;
    }

    //Chore constructor for backwards compatability before String assign and boolean assignedTo became a thing
    public Chore(String nam, String desc, String rescource, String grp, int rewrd, int dudate){
        name = nam;
        description = desc;
        resources = rescource;
        group = grp;
        reward = rewrd;
        duedate = dudate;
        assigned = "";      //Opted for value of "" rather than null, to avoid NullPointerException
        isCompleted = false;
    }



    public Chore(){
        name = null;
        description = null;
        resources = null;
        group = null;
        reward = 0;
        duedate = 0;
        assigned = null;
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
        return resources;
    }

    public int getReward(){
        return reward;
    }

    public int getDueDate(){
        return duedate;
    }

    public String getAssigned(){
        return assigned;
    }

    public boolean getIsCompleted() { return isCompleted; }

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
        return;
    }

    public void setReward(int reward){
        this.reward = reward;
    }

    public void setDuedate(int duedate){
        this.duedate = duedate;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }
    //sets isComplete to true and resets the assign
    public void complete(){
        this.setAssigned("No one");
        isCompleted = true;
    }

    public String[] getResourcesArray(){
        return resources.split(", ");    //Regex value might have to be changed later
    }
}
