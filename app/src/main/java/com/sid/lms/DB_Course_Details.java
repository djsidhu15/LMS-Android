package com.sid.lms;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sidharth on 28-Jun-16.
 */
public class DB_Course_Details {
    private static final String DATABASE_NAME="lms_db_course";
    private static final String DATABASE_TABLE_NAME="course_details_table";
    private static final int DATABASE_VERSION=1;

    public static final String COLUMN_ID="id";
    public static final String COLUMN_COURSE_NO="course_no";
    public static final String COLUMN_COURSE_TITLE="course_title";
    public static final String COLUMN_DURATION="duration";
    public static final String COLUMN_DESCRIPTION="description";

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    private class DbHelper extends SQLiteOpenHelper{
        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table "+DATABASE_TABLE_NAME
                    +" ("
                    +COLUMN_ID+" integer primary key autoincrement,"
                    +COLUMN_COURSE_NO+" text not null,"
                    +COLUMN_COURSE_TITLE+" text not null,"
                    +COLUMN_DURATION+" text not null,"
                    +COLUMN_DESCRIPTION+" text not null"
                    +");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL("drop table if exists "+DATABASE_TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }

    public DB_Course_Details(Context c){
        ourContext=c;
    }

    public DB_Course_Details open() throws SQLException{
        ourHelper=new DbHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public void Insert_course(String p1,String p2,String p3,String p4){

        ContentValues cv=new ContentValues();
        cv.put(COLUMN_COURSE_NO,p1);
        cv.put(COLUMN_COURSE_TITLE,p2);
        cv.put(COLUMN_DURATION,p3);
        cv.put(COLUMN_DESCRIPTION,p4);
        ourDatabase.insert(DATABASE_TABLE_NAME,null,cv);
    }

    public Cursor get_course(){
        String[] str=new String[]{COLUMN_ID,COLUMN_COURSE_NO,COLUMN_COURSE_TITLE,COLUMN_DURATION,COLUMN_DESCRIPTION};
        Cursor cursor=ourDatabase.query(DATABASE_TABLE_NAME,str,null,null,null,null,null);
        return cursor;
    }

    public Cursor get_course(String a){
        Cursor cursor=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME+" where "+COLUMN_COURSE_TITLE+" = '"+a+"';",null);
        return cursor;
    }

    public Cursor get_info(int clickid){
        Cursor cursor2=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME+" where "+COLUMN_ID+" = "+clickid+";",null);
        return cursor2;

    }
}
