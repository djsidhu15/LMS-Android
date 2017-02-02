package com.sid.lms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TM_Main_Activity extends AppCompatActivity {

    Button ballocatecources,bviewfeedback,viewattendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm__main_);

        Bundle extras=getIntent().getExtras();
        final String Actor=extras.getString("Actor");

        ballocatecources=(Button)findViewById(R.id.ballocatecources);
        bviewfeedback=(Button)findViewById(R.id.bviewfeedback);
        viewattendance=(Button)findViewById(R.id.viewattendance);

        ballocatecources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),TM_Allocate_Trainers.class);
                intent.putExtra("Actor",Actor);
                startActivity(intent);
            }
        });

        bviewfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),TM_Trainee_List_Activity.class);
                intent.putExtra("Actor",Actor);
                startActivity(intent);
            }
        });

        viewattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TM_Main_Activity.this,TM_View_Attendance.class);
                startActivity(intent);
            }
        });
    }
}
