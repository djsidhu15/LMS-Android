package com.sid.lms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sidharth on 29-Jun-16.
 */
public class DB_Trainer {

    private static final String DATABASE_NAME="lms_db_trainer";
    private static final String DATABASE_TABLE_NAME="trainer_table";
    private static final int DATABASE_VERSION=1;

    public static final String COLUMN_ID="id";
    public static final String COLUMN_LOGINID="loginid";
    public static final String COLUMN_PASSWORD="password";
    public static final String COLUMN_TRAINER_NAME="name";

    private final Context ourContext;
    private SQLiteDatabase ourDatabase;
    private DbHelper ourHelper;

    private class DbHelper extends SQLiteOpenHelper{
        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table "+DATABASE_TABLE_NAME
            +"("
                    +COLUMN_ID+" integer primary key autoincrement,"
                    +COLUMN_LOGINID+" text not null,"
                    +COLUMN_PASSWORD+" text not null,"
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
    public DB_Trainer(Context c){
        ourContext=c;
    }

    public DB_Trainer open() throws SQLException{
        ourHelper=new DbHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public void Insert(String p1,String p2,String p3){
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_LOGINID,p1);
        cv.put(COLUMN_PASSWORD,p2);
        cv.put(COLUMN_TRAINER_NAME,p3);
        ourDatabase.insert(DATABASE_TABLE_NAME,null,cv);
    }

    public Cursor Get_Info(){
        Cursor cursor=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME,null);
        return cursor;
    }

    public Cursor Get_Info(int clickid){
        Cursor cursor2=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME+" where "+COLUMN_ID+" = "+clickid+";",null);
        return cursor2;

    }
    public String Get_Trainer_name(String p1){
        Cursor cursor=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME+" where "+COLUMN_LOGINID+" = '"+p1+"';",null);
        String name;
        cursor.moveToFirst();
        name=cursor.getString(3);
        return name;
    }
}
