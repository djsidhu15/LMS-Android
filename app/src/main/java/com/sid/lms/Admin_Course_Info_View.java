package com.sid.lms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Admin_Course_Info_View extends AppCompatActivity {

    TextView courseno,title,duration,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__course__info__view);

        courseno=(TextView)findViewById(R.id.etcourseno);
        title=(TextView)findViewById(R.id.tvdescription2);
        duration=(TextView)findViewById(R.id.tvduration2);
        description=(TextView)findViewById(R.id.tvmaterialsavailable2);

        Bundle extras=getIntent().getExtras();
        courseno.setText(extras.getString("cno"));
        title.setText(extras.getString("ctitle"));
        duration.setText(extras.getString("cduration"));
        description.setText(extras.getString("cdescription"));



    }
}
