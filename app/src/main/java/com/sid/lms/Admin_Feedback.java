package com.sid.lms;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TextView;

public class Admin_Feedback extends AppCompatActivity {

    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__feedback);
        TextView tvlist=(TextView)findViewById(R.id.tvlist);
        DB_Feedback obj=new DB_Feedback(this);
        obj.open();
        Cursor res=obj.Get_Info();
        for (res.moveToFirst();!res.isAfterLast();res.moveToNext()){
            data+=res.getString(1)+" ";
            data+=res.getString(2)+" ";
            data+=res.getString(3)+" ";
            data+=res.getString(4)+" ";
            data+=res.getString(5)+"\n\n";
        }
        tvlist.setText(data);
    }
}
