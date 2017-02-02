package com.sid.lms;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Admin_Applied_Courses_List extends AppCompatActivity {

    String TAG;
    TextView tvlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__applied__courses__list);
        tvlist=(TextView)findViewById(R.id.tvlist);

        DB_Apply obj=new DB_Apply(Admin_Applied_Courses_List.this);
        obj.open();
        Cursor res=obj.Get_Info();
        String data="";
        for (res.moveToFirst();!res.isAfterLast();res.moveToNext()){
            data=data+res.getString(0)+" ";
            data=data+res.getString(1)+" ";
            data=data+res.getString(2)+" ";
            data=data+res.getString(3)+"\n";
            Log.d(TAG,"data="+data);
        }
        tvlist.setText(data);
        int count=res.getCount();
        Log.d(TAG,"data="+data);
        Log.d(TAG,"Count="+Integer.toString(count));
        obj.close();
    }
}
