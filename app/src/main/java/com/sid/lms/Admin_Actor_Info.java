package com.sid.lms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Admin_Actor_Info extends AppCompatActivity {

    String Actor="";
    TextView tvactorinfo,tvactorname;
    TextView etactorloginid,etactorpassword,etactorname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__actor__info);

        tvactorinfo=(TextView)findViewById(R.id.tvactorinfo);
        tvactorname=(TextView)findViewById(R.id.tvactorname);

        etactorloginid=(TextView) findViewById(R.id.etactorloginid);
        etactorpassword=(TextView) findViewById(R.id.etactorpassword);
        etactorname=(TextView) findViewById(R.id.etactorname);

        Bundle extras=getIntent().getExtras();
        String loginid,password,name;
        loginid=extras.getString("loginid");
        password=extras.getString("password");
        name=extras.getString("name");
        Actor=extras.getString("Actor");

        tvactorinfo.setText(Actor+" Info");
        tvactorname.setText(Actor+" name:");

        etactorloginid.setText(loginid);
        etactorpassword.setText(password);
        etactorname.setText(name);
    }
}
