package project.seg.householdchoremanager;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
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



    /*Likely future operations:
        - Read ALL chore values from table for information (implemented)
        - Compare user login info to tables (working on it)
        - Probably more
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

    public UserDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //Creates both tables
    @Override
    public void onCreate(SQLiteDatabase db){
        //Create the table for users
        String CREATE_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + COLUMN_NAME +
                " TEXT PRIMARY KEY," + COLUMN_PASSWORDS + " TEXT," + COLUMN_POINTS + " INTEGER," +
                COLUMN_ISADULT + " INTEGER)";
        db.execSQL(CREATE_TABLE);


    }

    //Overridden onUpgrade(). Updates the database, again for both chores.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        int adult;
        int points = user.getPoints();

        if(user.isAdult()){
            adult = 1;
        } else{
            adult = 0;
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_PASSWORDS, user.getPassword());
        values.put(COLUMN_ISADULT, adult);
        values.put(COLUMN_POINTS, user.getPoints());

        db.insert(TABLE_USERS, null, values);

        db.close(); //Save our changes to the database
    }

    //Will check to see if a user with "name" as their name exists
    public boolean checkUser(String name){
        User[] userList = getAllUsers();

        for(User u : userList){
            String tName = u.getName();
            if(tName.equals(name)){
                return true;
            }
        }

        return false;
    }

    public String nameUser(){
        User[] userList = getAllUsers();
        return userList[0].getName();
    }





    public User[] getAllUsers(){
        //created by Chris 2017/11/27
        SQLiteDatabase db = this.getReadableDatabase();
        User[] userList = new User[1000];
        int arrayLocation = 0;

        //cursor is used to parse through the rows of table used with .moveToNext
        String[] columns = {this.COLUMN_NAME, this.COLUMN_PASSWORDS,this.COLUMN_POINTS,this.COLUMN_ISADULT};
        Cursor cursorDB = db.rawQuery("SELECT * FROM UserInformation",null);

        if(cursorDB.moveToFirst()){
            String name = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_NAME));
            String pass = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_PASSWORDS));
            int points = cursorDB.getInt(cursorDB.getColumnIndex(this.COLUMN_POINTS));
            int adult = cursorDB.getInt(cursorDB.getColumnIndex(this.COLUMN_ISADULT));

            boolean bAdult = false;
            if(adult==0){
                bAdult = true;
            } else{
                bAdult = false;
            }

            User newUser = new User(name,pass,bAdult,points);

            userList[arrayLocation] = newUser;
            arrayLocation++;

            while(cursorDB.moveToNext()){
                name = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_NAME));
                pass = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_PASSWORDS));
                points = cursorDB.getInt(cursorDB.getColumnIndex(this.COLUMN_POINTS));
                adult = cursorDB.getInt(cursorDB.getColumnIndex(this.COLUMN_ISADULT));

                if(adult==0){
                    bAdult = true;
                } else{
                    bAdult = false;
                }

                newUser = new User(name,pass,bAdult,points);
                userList[arrayLocation] = newUser;
                arrayLocation++;
            }
        }
        User newUser = new User("a","a",false,0);

        userList[arrayLocation] = newUser;
        cursorDB.close();
        db.close();
        return userList;
    }
}