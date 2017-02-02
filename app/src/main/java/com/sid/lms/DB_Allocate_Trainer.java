package com.sid.lms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sidharth on 30-Jun-16.
 */
public class DB_Allocate_Trainer {

    private static final String DATABASE_NAME="lms_db_allocate_trainer";
    private static final String DATABASE_TABLE_NAME="allocate_trainer_table";
    private static final int DATABASE_VERSION=1;

    public static final String COLUMN_ID="id";
    public static final String COLUMN_COURSE_NAME="course_name";
    public static final String COLUMN_TRAINER_NAME="trainer_name";

    private final Context ourContext;
    private SQLiteDatabase ourDatabase;
    private DbHelper ourHelper;

    private class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table "+DATABASE_TABLE_NAME
                    +"("
                    +COLUMN_ID+" integer primary key autoincrement,"
                    +COLUMN_COURSE_NAME+" text not null,"
                    +COLUMN_TRAINER_NAME+" text not null"
                    +");"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL("drop table if exists "+DATABASE_TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }
    public DB_Allocate_Trainer(Context c){
        ourContext=c;
    }

    public DB_Allocate_Trainer open() throws SQLException {
        ourHelper=new DbHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public void Insert(String p1,String p2){
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_COURSE_NAME,p1);
        cv.put(COLUMN_TRAINER_NAME,p2);
        ourDatabase.insert(DATABASE_TABLE_NAME,null,cv);
    }

    public Cursor Get_Info(){
        Cursor cursor=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME,null);
        return cursor;
    }

    public String Get_Info(String title) throws SQLException{
        //return Trainer
        Cursor cursor2=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME+" where "+COLUMN_COURSE_NAME+" = '"+title+"';",null);
        cursor2.moveToFirst();
        String Trainer=cursor2.getString(2);
        return Trainer;

    }

    public String Get_Course(String title) throws SQLException{
        //return Course
        Cursor cursor2=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME+" where "+COLUMN_TRAINER_NAME+" = '"+title+"';",null);
        cursor2.moveToFirst();
        String Course=cursor2.getString(1);
        return Course;

    }
}
