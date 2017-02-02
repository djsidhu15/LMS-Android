package com.sid.lms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Admin_Members_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__members__view);
        TextView tvtest=(TextView)findViewById(R.id.tvtest);
        DB_Register get=new DB_Register(Admin_Members_View.this);
        get.open();
        //String a=get.get();
        //tvtest.setText(a);
        get.close();
    }
}
