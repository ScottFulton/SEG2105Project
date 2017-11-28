package project.seg.householdchoremanager;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.sql.ResultSet;
import java.util.LinkedList;
/**
 * Created by Kevin on 26/11/17.
 * Just a reimplementation of the code found in the Android Lab on SQLite.
 * Handles all of the database aspects of our code, and can add new users
 * as required.
 *
 * Columns look something like this for UserInformation:
 * User/Password/Child Boolean/Points
 *
 * ChoreInformation:
 * Chore/Description/Resources/Group/Reward
 */



    /*Likely future operations:
        - Read ALL chore values from table for information (implemented)
        - Compare user login info to tables (working on it)
        - Probably more
    */

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HouseChores.db";

    //Database attributes responsible for user information
    public static final String TABLE_USERS = "UserInformation";
    public static final String COLUMN_NAMES = "Users";         //User's name
    public static final String COLUMN_PASSWORDS = "Passwords";  //This is a joke security wise
    public static final String COLUMN_POINTS = "Points";    //Points accumulated by user
    public static final String COLUMN_PARENT = "Parent";    //Boolean if user is parent or not

    //Attributes for chore handling
    public static final String TABLE_CHORES = "ChoreInformation";
    public static final String COLUMN_CHORES = "Chore";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_RESOURCES = "Resources";
    public static final String COLUMN_GROUP = "Group";
    public static final String COLUMN_REWARD = "Reward";


    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //Creates both tables
    @Override
    public void onCreate(SQLiteDatabase db){
        //Create the table for users
        String CREATE_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + COLUMN_NAMES +
                " TEXT PRIMARY KEY," + COLUMN_PASSWORDS + " TEXT," + COLUMN_POINTS + " INTEGER," +
                COLUMN_PARENT + " INTEGER)";
        db.execSQL(CREATE_TABLE);

        //Create the table for chores
        CREATE_TABLE = "CREATE TABLE " + TABLE_CHORES + "(" + COLUMN_CHORES + " TEXT PRIMARY KEY, "
                + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_RESOURCES + " TEXT, " + COLUMN_GROUP +
                " TEXT, " + COLUMN_REWARD + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    //Overridden onUpgrade(). Updates the database, again for both chores.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHORES);
        onCreate(db);
    }



    public void addChore(Chore chore){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_CHORES, chore.getName());
        values.put(COLUMN_DESCRIPTION, chore.getDescription());
        values.put(COLUMN_RESOURCES, chore.getResources());
        values.put(COLUMN_GROUP, chore.getGroup());
        values.put(COLUMN_REWARD, chore.getReward());

        db.insert(TABLE_CHORES, null, values);
        db.close();
    }

    public Chore getChoreByName(String name){
        //Not implemented yet! I got stuck trying to figure out how to put resources
        //into the chore that will be returned

        /*
        Chore chore = new Chore();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CHORES + " WHERE " + COLUMN_CHORES + " = \"" + name
                + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            chore.setName(cursor.getString(0));
            chore.setDescription(cursor.getString(1));
            chore.setResources();

        }
        cursor.close();
        */

        return null;
    }

    public Chore[] getAllChores(){
        SQLiteDatabase db = this.getReadableDatabase();
//        int choreCount = db.execSQL("SELECT COUNT(*) FROM [Chore]");
        Chore[] choreList = new Chore[1000];
        int arrayLocation = 0;
        //cursor is used to parse through the rows of table used with .moveToNext
        Cursor cursorDB = db.rawQuery("SELECT * FROM TABLE",null);
        if(cursorDB.moveToFirst()){
            String name = cursorDB.getString(cursorDB.getColumnIndex("Chore"));
            String desc = cursorDB.getString(cursorDB.getColumnIndex("Description"));
            String res = cursorDB.getString(cursorDB.getColumnIndex("Resources"));
            String grp = cursorDB.getString(cursorDB.getColumnIndex("Group"));
            String reward = cursorDB.getString(cursorDB.getColumnIndex("Reward"));
            int rewardInt = Integer.parseInt(reward);

            Chore newChore = new Chore(name,desc,res,grp,rewardInt);
            // TODO - res is asking for String[] but is stored as String
            choreList[arrayLocation] = newChore;
            arrayLocation++;

            while(cursorDB.moveToNext()){
                name = cursorDB.getString(cursorDB.getColumnIndex("Chore"));
                desc = cursorDB.getString(cursorDB.getColumnIndex("Description"));
                res = cursorDB.getString(cursorDB.getColumnIndex("Resources"));
                grp = cursorDB.getString(cursorDB.getColumnIndex("Group"));
                reward = cursorDB.getString(cursorDB.getColumnIndex("Reward"));
                rewardInt = Integer.parseInt(reward);

                newChore = new Chore(name,desc,res,grp,rewardInt);
                // TODO - res is asking for String[] but is stored as String
                choreList[arrayLocation] = newChore;
                arrayLocation++;
            }
        }

        return choreList;
    }

    /*OTHER METHODS WE SHOULD ADD:
        - getAllChores()              Return an array of every chore we have. Makes it easier later on to
                                      list them all in ChoreGroups.

        - deleteChore(String name)    Remove a chore completely from the database

        - editChore(Chore chore)      Use deleteChore() to remove the old entry, and just replace it
                                      with "chore"
    */


}
