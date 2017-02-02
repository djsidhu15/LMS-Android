package com.sid.lms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class Admin extends AppCompatActivity {


    public String TAG;
    String Actor="";
    Button badmintrainee,bcourses,btrainercourses,bappliedcourses,bapproved,bfeedback;
    Button btrainer,btrainingmanager,bprojectmanager,attendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        badmintrainee=(Button)findViewById(R.id.badmintrainee);
        bcourses=(Button)findViewById(R.id.bcourses);
        btrainer=(Button)findViewById(R.id.btrainer);
        btrainingmanager=(Button)findViewById(R.id.btrainingmanager);
        bprojectmanager=(Button)findViewById(R.id.bprojectmanager);
        btrainercourses=(Button)findViewById(R.id.trainercourses);
        bappliedcourses=(Button)findViewById(R.id.bappliedcourses);
        bapproved=(Button)findViewById(R.id.approved);
        bfeedback=(Button)findViewById(R.id.feedback);
        attendance=(Button)findViewById(R.id.attendance);



        //Buttom Trainee
        badmintrainee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this,PM_Trainees_List_Activity.class));
            }
        });

        //Button Courses
        bcourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin.this,Admin_Insert_Course.class);
                startActivity(intent);
            }
        });

        //Button Trainer
        btrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actor="Trainer";
                Intent intent=new Intent(Admin.this,Admin_Actor_Insert.class);
                intent.putExtra("Actor",Actor);
                startActivity(intent);
            }
        });

        btrainingmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actor="Training Manager";
                Intent intent=new Intent(Admin.this,Admin_Actor_Insert.class);
                intent.putExtra("Actor",Actor);
                startActivity(intent);
            }
        });

        bprojectmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actor="Project Manager";
                Intent intent=new Intent(Admin.this,Admin_Actor_Insert.class);
                intent.putExtra("Actor",Actor);
                startActivity(intent);
            }
        });


        btrainercourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this,Admin_Trainer_Courses.class));
            }
        });

        bappliedcourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin.this,Admin_Applied_Courses_List.class);
                startActivity(intent);
            }
        });

        bapproved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin.this,Admin_Approved_Table.class);
                startActivity(intent);
            }
        });

        bfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin.this,Admin_Feedback.class);
                startActivity(intent);
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin.this,Admin_Attendance.class);
                startActivity(intent);
            }
        });
    }
}
