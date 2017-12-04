package project.seg.householdchoremanager;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Kevin on 26/11/17.
 *
 * Using an additional database handler to avoid a merge issue.
 * This database is responsible for handling user authentication (original plan was that both tables
 * would be in the same database. This is not the case anymore).
 *
 * Format:
 * User|Password|isAdult|Points
 */





public class UserDatabase extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserInfo.db";

    //Database attributes responsible for user information
    public static final String TABLE_USERS = "UserInformation";
    public static final String COLUMN_NAME = "Users";         //User's name
    public static final String COLUMN_PASSWORDS = "Passwords";  //This is a joke security wise
    public static final String COLUMN_POINTS = "Points";    //Points accumulated by user
    public static final String COLUMN_ISADULT = "isAdult";    //Boolean if user is parent or not
    public static final String COLUMN_ICON = "icon";
    
    public UserDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //Creates both tables
    @Override
    public void onCreate(SQLiteDatabase db){
        //Create the table for users
        String CREATE_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + COLUMN_NAME +
                " TEXT PRIMARY KEY," + COLUMN_PASSWORDS + " TEXT," + COLUMN_POINTS + " INTEGER," +
                COLUMN_ISADULT + " INTEGER, " + COLUMN_ICON + " TEXT)";
        db.execSQL(CREATE_TABLE);


    }

    //Overridden onUpgrade(). Updates the database tables.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        /*
        int adult;
        int points = user.getPoints();

        if(user.isAdult()){
            adult = 1;
        } else{
            adult = 0;
        }
        */
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_PASSWORDS, user.getPassword());
        values.put(COLUMN_POINTS, user.getPoints());
        values.put(COLUMN_ISADULT, user.isAdult());
        values.put(COLUMN_ICON, user.getDrawableIcon());


        db.insert(TABLE_USERS, null, values);

        db.close(); //Save our changes to the database
    }



    //Will check to see if a user with "name" as their name exists
    public boolean checkUser(String name){
        User[] userList = getAllUsers();

        /*
        I'm aware that this code is a crime against software engineering,
        but it also works, and that is good enough for me at this point
         */
        try{
            for(User u : userList) {
                String tName = u.getName();
                if (tName.equals(name)) {
                    return true;
                }
            }
        } catch (Exception E){
            return false;
        }

        return false;
    }

    //Authenticates a user given the login and password information
    public Boolean authUser(String name, String pass){
        User[] userList = getAllUsers();

        /*
        It's pretty much the method above but not
         */
        try{
            for(User u : userList) {
                String tName = u.getName();
                String tPass = u.getPassword();
                if (tName.equals(name) && tPass.equals(pass)) {
                    return true;
                }
            }
        } catch (Exception E){
            return false;
        }

        return false;
    }

    //Returns a USER type object
    public User getUserByName(String name){
        User[] userList = getAllUsers();

        try{
            for(User u : userList) {
                String tName = u.getName();
                if (tName.equals(name)) {
                    return u;
                }
            }
        } catch (Exception E){
            return null;
        }

        return null;
    }



    public User[] getAllUsers(){
        //Chris made this for DatabaseHandler, I just copied the relevant bits to be used in
        //a user database -Kevin
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<User> userList = new ArrayList<User>();

        //cursor is used to parse through the rows of table used with .moveToNext
        String[] columns = {this.COLUMN_NAME, this.COLUMN_PASSWORDS,this.COLUMN_POINTS,this.COLUMN_ISADULT};
        Cursor cursorDB = db.rawQuery("SELECT * FROM UserInformation",null);

        if(cursorDB.moveToFirst()){
            String name = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_NAME));
            String pass = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_PASSWORDS));
            int points = cursorDB.getInt(cursorDB.getColumnIndex(this.COLUMN_POINTS));
            int adult = cursorDB.getInt(cursorDB.getColumnIndex(this.COLUMN_ISADULT));
            String drawableIcon = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_ICON));

            boolean bAdult = false;
            if(adult==0){
                bAdult = true;
            } else{
                bAdult = false;
            }

            User newUser = new User(name,pass,bAdult,points,drawableIcon);

            userList.add(newUser);

            while(cursorDB.moveToNext()){
                name = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_NAME));
                pass = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_PASSWORDS));
                points = cursorDB.getInt(cursorDB.getColumnIndex(this.COLUMN_POINTS));
                adult = cursorDB.getInt(cursorDB.getColumnIndex(this.COLUMN_ISADULT));
                drawableIcon = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_ICON));

                if(adult==0){
                    bAdult = true;
                } else{
                    bAdult = false;
                }

                newUser = new User(name,pass,bAdult,points,drawableIcon);
                userList.add(newUser);
            }
        }
        User[] newUserList = userList.toArray(new User[userList.size()]);
        cursorDB.close();
        db.close();
        return newUserList;
    }


    //unused
    public void deleteUser(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_NAME + " = \"" + name + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_USERS, COLUMN_NAME + " = " + idStr, null);
            cursor.close();
        }
        db.close();
    }
    //updates everything except for adult status
    public void updateUser(User updatedUser) {
        //getting database and building a new content values variable from the passed user
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_POINTS, updatedUser.getPoints());
        cv.put(COLUMN_NAME, updatedUser.getName());
        cv.put(COLUMN_PASSWORDS, updatedUser.getPassword());
        cv.put(COLUMN_ICON, updatedUser.getDrawableIcon());
        //building the search query and finding the proper value
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_NAME + " = \'" + updatedUser.getName() + "\'";
        String[] name = {updatedUser.getName()};
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            //where clause should use selection args, unsanitzed sql inputs are dangerous and prone
            //to injection
            db.update(TABLE_USERS,cv, COLUMN_NAME + "=?", name);
            cursor.close();
        }
        db.close();
    }
}
