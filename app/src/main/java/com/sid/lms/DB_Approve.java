package com.sid.lms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sidharth on 03-Jul-16.
 */
public class DB_Approve {

    private static final String DATABASE_NAME="lms_db_approve";
    private static final String DATABASE_TABLE_NAME="approve";
    private static final int DATABASE_VERSION=1;

    public static final String COLUMN_ID="id";
    public static final String COLUMN_TRAINEE_USERNAME="trainee_username";
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
                    +COLUMN_TRAINEE_USERNAME+" text not null,"
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
    public DB_Approve(Context c){
        ourContext=c;
    }

    public DB_Approve open() throws SQLException {
        ourHelper=new DbHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }

    public void close()throws SQLException{
        ourHelper.close();
    }

    public void Insert(String p1,String p2,String p3)throws SQLException{
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_TRAINEE_USERNAME,p1);
        cv.put(COLUMN_COURSE_NAME,p2);
        cv.put(COLUMN_TRAINER_NAME,p3);
        ourDatabase.insert(DATABASE_TABLE_NAME,null,cv);
    }

    public Cursor Get_Info()throws SQLException{
        Cursor cursor=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME,null);
        return cursor;
    }

    public Cursor Get_Info(String b)throws SQLException{
        //for getting course and trainer
        Cursor cursor=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME+" where "+COLUMN_TRAINEE_USERNAME+" = '"+b+"';",null);
        return cursor;
    }
    public Cursor Get_Info(int clickid)throws SQLException{
        Cursor cursor2=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME+" where "+COLUMN_ID+" = "+clickid+";",null);
        return cursor2;

    }

    public Cursor Get_Trainee(String b)throws SQLException{
        //for getting course and trainer
        Cursor cursor=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME+" where "+COLUMN_COURSE_NAME+" = '"+b+"';",null);
        return cursor;
    }
}
