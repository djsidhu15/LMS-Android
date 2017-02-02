package com.sid.lms;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sidharth on 26-Jun-16.
 */
public class DB_Admin_Login {
    private static final String DATABASE_NAME="LMS_db";
    private static final String DATABASE_TABLE_NAME="admin_table";
    private static final int DATABASE_VERSION=1;

    public static final String COLUMN_ID="id";
    public static final String COLUMN_LOGIN_ID="login_id";
    public static final String COLUMN_PASSWORD="password";
    public static final String COLUMN_ADMIN_NO="admin_no";

    private final Context ourContext;
    private SQLiteDatabase ourDatabase;
    private DbHelper ourHelper;

private static class DbHelper extends SQLiteOpenHelper{

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+DATABASE_TABLE_NAME+
        "("+COLUMN_ID+" integer primary key autoincrement,"+
        COLUMN_LOGIN_ID+" text not null,"+
        COLUMN_PASSWORD+" text not null,"+
        COLUMN_ADMIN_NO+"text not null "+
        ");");

        sqLiteDatabase.execSQL("insert into "+DATABASE_TABLE_NAME+" values('','Admin','Admin','Admin-00');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists "+DATABASE_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
    //Get Class Context
    public DB_Admin_Login(Context c){
        ourContext=c;
    }

    //Open Database
    public DB_Admin_Login open() throws SQLException{
        ourHelper=new DbHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }

    //Close Database
    public void close(){
        ourHelper.close();
    }




}
