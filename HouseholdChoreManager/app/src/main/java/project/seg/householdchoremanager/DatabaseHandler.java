package project.seg.householdchoremanager;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;
import java.util.LinkedList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HouseChores.db";
    public static final String TABLE_CHORES = "chores";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CHORENAME = "chorename";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_RESOURCES = "resources";
    public static final String COLUMN_GROUPNAME = "groupname";
    public static final String COLUMN_REWARD = "reward";
    public static final String COLUMN_DUEDATE = "duedate";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //Creates both tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CHORES_TABLE = "CREATE TABLE " + TABLE_CHORES + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_CHORENAME +" TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " + COLUMN_RESOURCES + " TEXT, " + COLUMN_GROUPNAME
                + " TEXT, " + COLUMN_REWARD + " INTEGER, " + COLUMN_DUEDATE + " INTEGER" + ");";
        db.execSQL(CREATE_CHORES_TABLE);
    }

    //Overridden onUpgrade(). Updates the database, again for both chores.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHORES);
        onCreate(db);
    }
    public void addChore(Chore chore){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHORENAME, chore.getName());
        values.put(COLUMN_DESCRIPTION, chore.getDescription());
        values.put(COLUMN_RESOURCES, chore.getResources());
        values.put(COLUMN_GROUPNAME, chore.getGroup());
        values.put(COLUMN_REWARD, chore.getReward());
        values.put(COLUMN_DUEDATE, chore.getDueDate());
        db.insertOrThrow(TABLE_CHORES, null, values);
        db.close();
    }

    public Chore[] getAllChores(){
        //created by Chris 2017/11/27
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Chore> choreList = new ArrayList<Chore>();
        //cursor is used to parse through the rows of table used with .moveToNext
        Cursor cursorDB = db.rawQuery("SELECT * FROM chores",null);

        if(cursorDB.moveToFirst()){
            String name = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_CHORENAME));
            String desc = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_DESCRIPTION));
            String res = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_RESOURCES));
            String grp = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_GROUPNAME));
            int reward = cursorDB.getInt(cursorDB.getColumnIndex(this.COLUMN_REWARD));
            int date = cursorDB.getInt(cursorDB.getColumnIndex(this.COLUMN_DUEDATE));
            Chore newChore = new Chore(name,desc,res,grp,reward,date);

            choreList.add(newChore);

            while(cursorDB.moveToNext()){
                name = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_CHORENAME));
                desc = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_DESCRIPTION));
                res = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_RESOURCES));
                grp = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_GROUPNAME));
                reward = cursorDB.getInt(cursorDB.getColumnIndex(this.COLUMN_REWARD));
                date = cursorDB.getInt(cursorDB.getColumnIndex(this.COLUMN_DUEDATE));
                newChore = new Chore(name,desc,res,grp,reward,date);
                choreList.add(newChore);
            }
        }
        Chore[] newChoreList = choreList.toArray(new Chore[choreList.size()]);
        cursorDB.close();
        db.close();
        return newChoreList;
    }
}
