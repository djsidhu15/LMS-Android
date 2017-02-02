package com.sid.lms;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Admin_Trainer_Courses extends AppCompatActivity {

    TextView tvlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__trainer__courses);
        tvlist=(TextView)findViewById(R.id.tvlist);

        DB_Allocate_Trainer obj=new DB_Allocate_Trainer(Admin_Trainer_Courses.this);
        obj.open();
        Cursor res=obj.Get_Info();
        String data="";
        for (res.moveToFirst();!res.isAfterLast();res.moveToNext()){
            data=data+res.getString(0)+" ";
            data=data+res.getString(1)+" ";
            data=data+res.getString(2)+"\n";
        }
        tvlist.setText(data);
        obj.close();
    }
}
