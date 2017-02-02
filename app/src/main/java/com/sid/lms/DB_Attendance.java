package com.sid.lms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sidharth on 05-Jul-16.
 */
public class DB_Attendance {
    private static final String DATABASE_NAME="lms_db_attendance";
    private static final String DATABASE_TABLE_NAME="attendance";
    private static final int DATABASE_VERSION=1;

    public static final String COLUMN_ID="id";
    public static final String COLUMN_DATE="date";

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    private class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table "+DATABASE_TABLE_NAME
                    +" ("
                    +COLUMN_ID+" integer primary key autoincrement,"
                    +COLUMN_DATE+" text not null"
                    +");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL("drop table if exists "+DATABASE_TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }

    public DB_Attendance(Context c){
        ourContext=c;
    }

    public DB_Attendance open() throws SQLException {
        ourHelper=new DbHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public void Insert_Data(String p1,String p2){

        ourDatabase.rawQuery("insert into "+DATABASE_TABLE_NAME+" ("+p2+")"+" values ('1');",null);
    }

    public void Insert_Date(String p1){

        ContentValues cv=new ContentValues();
        cv.put(COLUMN_DATE,p1);
        ourDatabase.insert(DATABASE_TABLE_NAME,null,cv);
//        ourDatabase.rawQuery("insert into "+DATABASE_TABLE_NAME+" ("+COLUMN_ID+","+COLUMN_DATE+") values ("+"'','"+p1+"');",null);
    }

    public void Insert_Present(String p1,String p2){

        ourDatabase.execSQL("update "+DATABASE_TABLE_NAME+" set "+p1+" = '1' where "+COLUMN_DATE+" = '"+p2+"';");
//        ourDatabase.rawQuery("insert into "+DATABASE_TABLE_NAME+" ("+COLUMN_ID+","+COLUMN_DATE+") values ("+"'','"+p1+"');",null);
    }

    public void Insert_Absent(String p1,String p2){

        ourDatabase.execSQL("update "+DATABASE_TABLE_NAME+" set "+p1+" = 0 where "+COLUMN_DATE+" = '"+p2+"';");

//        ourDatabase.rawQuery("insert into "+DATABASE_TABLE_NAME+" ("+COLUMN_ID+","+COLUMN_DATE+") values ("+"'','"+p1+"');",null);
    }

    public Cursor Get_Info(){
        Cursor a=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME,null);
        return a;
    }

    public Cursor Get_Info(String date){
        Cursor a=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME+" where "+COLUMN_DATE+" = '"+date+"';",null);
        return a;
    }

    public void Add_Column(String p1){
        ourDatabase.execSQL("ALTER TABLE "+DATABASE_TABLE_NAME+" ADD COLUMN "+p1+" TEXT not null default '2';");
    }

   /* public Cursor get_course(){
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

    }*/
}
