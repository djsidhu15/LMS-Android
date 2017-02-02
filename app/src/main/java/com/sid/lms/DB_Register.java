package com.sid.lms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sidharth on 27-Jun-16.
 */
public class DB_Register {

    private static final String DATABASE_NAME="lms_db_trainee";
    private static final String DATABASE_TABLE_NAME="trainee_table";
    private static final int DATABASE_VERSION=1;

    public static final String COLUMN_ID="id";
    public static final String COLUMN_FNAME="fname";
    public static final String COLUMN_LNAME="lname";
    public static final String COLUMN_MAILID="mailid";
    public static final String COLUMN_USERNAME="username";
    public static final String COLUMN_PASSWORD="password";
    public static final String COLUMN_PHONE="phone";
    public static final String COLUMN_DEPARTMENT="department";
    public static final String COLUMN_SUBGROUP="subgroup";
    public static final String COLUMN_LOCATION="location";

//    String fname,lname,mailid,phone,department,subgroup,location;

    private final Context ourContext;
    private SQLiteDatabase ourDatabase;
    private DbHelper ourHelper;

    private class DbHelper extends SQLiteOpenHelper{

        public DbHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL("create table "+DATABASE_TABLE_NAME+
                    "("+COLUMN_ID+" integer primary key autoincrement,"+
                    COLUMN_FNAME+" text not null,"+
                    COLUMN_LNAME+" text not null,"+
                    COLUMN_MAILID+" text not null,"+
                    COLUMN_USERNAME+" text not null,"+
                    COLUMN_PASSWORD+" text not null,"+
                    COLUMN_PHONE+" text not null,"+
                    COLUMN_DEPARTMENT+" text not null,"+
                    COLUMN_SUBGROUP+" text not null,"+
                    COLUMN_LOCATION+" text not null"+
                    ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL("drop table if exists "+DATABASE_TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }

    public DB_Register(Context c){
        ourContext=c;
    }

    public DB_Register open() throws SQLException{
        ourHelper=new DbHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public long register(String fname2,String lname2,String mailid2,String username2,
                         String password22,String phone2,String department2,String subgroup2,String location2){
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_FNAME,fname2);
        cv.put(COLUMN_LNAME,lname2);
        cv.put(COLUMN_MAILID,mailid2);
        cv.put(COLUMN_USERNAME,username2);
        cv.put(COLUMN_PASSWORD,password22);
        cv.put(COLUMN_PHONE,phone2);
        cv.put(COLUMN_DEPARTMENT,department2);
        cv.put(COLUMN_SUBGROUP,subgroup2);
        cv.put(COLUMN_LOCATION,location2);
       return ourDatabase.insert(DATABASE_TABLE_NAME,null,cv);

//        ourDatabase.execSQL("insert into "+DATABASE_TABLE_NAME+ " values('',"+ "'"+fname2+"',"+ "'"+lname2+"',"+ "'"+mailid2+"',"+ "'"+username2+"',"+ "'"+password22+"',"+ "'"+phone2+"',"+ "'"+department2+"',"+ "'"+subgroup2+"',"+ "'"+location2+"'"+ ");");
    }

    public Cursor Get_Info() {
        Cursor res = ourDatabase.rawQuery("select * from " + DATABASE_TABLE_NAME, null);

        return res;
    }

    public Cursor Get_Info(int clickid){
        Cursor res=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME+ " where "+COLUMN_ID+" = "+clickid+";",null);
        return res;
    }

    public String Get_Info(String username){
        Cursor res=ourDatabase.rawQuery("select * from "+DATABASE_TABLE_NAME+ " where "+COLUMN_USERNAME+" = '"+username+"';",null);
        res.moveToFirst();
        String Trainee=res.getString(1)+" "+res.getString(2);
        return Trainee;
    }
}
