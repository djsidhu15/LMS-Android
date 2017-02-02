package com.sid.lms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PM_Main_Activity extends AppCompatActivity {

    Button bapprove,traineestat,trainerstat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pm__main_);

        Bundle extras=getIntent().getExtras();
        String Actor=extras.getString("Actor");

        bapprove=(Button)findViewById(R.id.bPMapprovecourses);
        traineestat=(Button)findViewById(R.id.btraineestatus);
        trainerstat=(Button)findViewById(R.id.btrainersstat);

        bapprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),PM_Enrolled_Courses_Activity.class));
            }
        });

        traineestat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),PM_Trainees_List_Activity.class));
            }
        });

        trainerstat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),PM_Trainers_List.class));
            }
        });
    }
}
