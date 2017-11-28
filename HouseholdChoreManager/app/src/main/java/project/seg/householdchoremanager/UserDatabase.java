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

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_PASSWORDS, user.getPassword());
        values.put(COLUMN_ISADULT, user.isAdult());
        values.put(COLUMN_POINTS, user.getPoints());

        db.insert(TABLE_USERS, null, values);
        db.close(); //Save our changes to the database
    }

    public boolean getUser(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " +TABLE_USERS+ " WHERE " + COLUMN_NAME + " = \"" + name + "\"";


        Cursor cursor = db.rawQuery(query, null);

        User retUser = new User(null, null, false, 0);

        if(cursor.moveToFirst()){
            User.setName(cursor.getString(0));
            User.setPassword(cursor)
        }

    }

    public boolean awardPoints(String user, int points){
        SQLiteDatabase db = this.getWritableDatabase();
    }
}