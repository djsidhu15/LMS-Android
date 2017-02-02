package com.sid.lms;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Admin_Approved_Table extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__approved__table);
        tv=(TextView)findViewById(R.id.tvlist);

        DB_Approve obj=new DB_Approve(this);
        obj.open();
        Cursor res=obj.Get_Info();
        String data="";
        for (res.moveToFirst();!res.isAfterLast();res.moveToNext()){
            data+=res.getString(1)+" "+res.getString(2)+" "+res.getString(3);
            data+="\n";
        }
        obj.close();
        tv.setText(data);
    }
}
