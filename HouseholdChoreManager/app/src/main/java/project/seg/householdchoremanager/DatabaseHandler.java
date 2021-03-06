package project.seg.householdchoremanager;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;
import java.util.LinkedList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HouseChores.db";
    public static final String TABLE_CHORES = "chores";
    public static final String COLUMN_CHORENAME = "chorename";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_RESOURCES = "resources";
    public static final String COLUMN_GROUPNAME = "groupname";
    public static final String COLUMN_REWARD = "reward";
    public static final String COLUMN_DUEDATE = "duedate";
    public static final String COLUMN_ASSIGNED = "assigned";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //Creates both tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CHORES_TABLE = "CREATE TABLE " + TABLE_CHORES + " ("+COLUMN_CHORENAME+" TEXT PRIMARY KEY, " +
                COLUMN_DESCRIPTION + " TEXT, " + COLUMN_RESOURCES + " TEXT, " + COLUMN_GROUPNAME
                + " TEXT, " + COLUMN_REWARD + " INTEGER, " + COLUMN_DUEDATE + " INTEGER, " + COLUMN_ASSIGNED
                + " TEXT " + ")";
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
        values.put(COLUMN_ASSIGNED, chore.getAssigned());
        db.insert(TABLE_CHORES, null, values);
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
            String ass = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_ASSIGNED));
            Chore newChore = new Chore(name,desc,res,grp,reward,date,ass);

            choreList.add(newChore);

            while(cursorDB.moveToNext()){
                name = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_CHORENAME));
                desc = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_DESCRIPTION));
                res = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_RESOURCES));
                grp = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_GROUPNAME));
                reward = cursorDB.getInt(cursorDB.getColumnIndex(this.COLUMN_REWARD));
                date = cursorDB.getInt(cursorDB.getColumnIndex(this.COLUMN_DUEDATE));
                ass = cursorDB.getString(cursorDB.getColumnIndex(this.COLUMN_ASSIGNED));
                newChore = new Chore(name,desc,res,grp,reward,date,ass);
                choreList.add(newChore);
            }
        }

        Chore[] newChoreList = choreList.toArray(new Chore[choreList.size()]);

        cursorDB.close();
        db.close();
        return newChoreList;
    }
    //Finds chore based on chore name and removes it from the database
    public boolean deleteChore(String chorename){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM " + TABLE_CHORES + " WHERE " + COLUMN_CHORENAME + " = \"" + chorename + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            String[] name = {chorename};
            db.delete(TABLE_CHORES, COLUMN_CHORENAME + "=?", name);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    /**
     * Finds the location in the sql database using the name
     * of the chore object and updates those values accordingly.
     * @param chore
     */
    public void updateChore(Chore chore) {
        //getting database and building a new content values variable from the passed user
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CHORENAME, chore.getName());
        cv.put(COLUMN_DESCRIPTION, chore.getDescription());
        cv.put(COLUMN_RESOURCES, chore.getResources());
        cv.put(COLUMN_GROUPNAME, chore.getGroup());
        cv.put(COLUMN_REWARD, chore.getReward());
        cv.put(COLUMN_DUEDATE, chore.getDueDate());
        cv.put(COLUMN_ASSIGNED, chore.getAssigned());
        //building the search query and finding the proper value
        String query = "Select * FROM " + TABLE_CHORES + " WHERE " + COLUMN_CHORENAME + " = \'" + chore.getName() + "\'";
        String[] name = {chore.getName()};
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            db.update(TABLE_CHORES,cv, COLUMN_CHORENAME + "=?", name);
            cursor.close();
        }
        db.close();
    }

    /**
     * Alternate version of updateChore instead of using the name of the chore
     * in order to find it in the database, it uses another parameter, oldName.
     * Everything functions the same as above.
     * @param chore
     * @param oldName
     */
    public void updateChore(Chore chore, String oldName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CHORENAME, chore.getName());
        cv.put(COLUMN_DESCRIPTION, chore.getDescription());
        cv.put(COLUMN_RESOURCES, chore.getResources());
        cv.put(COLUMN_GROUPNAME, chore.getGroup());
        cv.put(COLUMN_REWARD, chore.getReward());
        cv.put(COLUMN_DUEDATE, chore.getDueDate());
        cv.put(COLUMN_ASSIGNED, chore.getAssigned());
        String query = "Select * FROM " + TABLE_CHORES + " WHERE " + COLUMN_CHORENAME + " = \'" + oldName + "\'";
        String[] name = {oldName};
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            db.update(TABLE_CHORES,cv, COLUMN_CHORENAME + "=?", name);
            cursor.close();
        }
        db.close();
    }
}