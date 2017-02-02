package com.sid.lms;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_Activity extends AppCompatActivity {

    String strLoginID,strPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        final EditText etloginid=(EditText)findViewById(R.id.etlogin);
        final EditText etpassword=(EditText)findViewById(R.id.etpassword);
        TextView tvregister=(TextView)findViewById(R.id.tvregister);
        Button blogin=(Button)findViewById(R.id.blogin);

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strLoginID=etloginid.getText().toString();
                strPassword=etpassword.getText().toString();

                if (strLoginID.equals("Admin")&&strPassword.equals("")){
                    Intent intent=new Intent(Login_Activity.this,Admin.class);
                    intent.putExtra("Actor","Admin");
                    startActivity(intent);
                }
                if (strLoginID.equals("Trainee")&&strPassword.equals("")){
                    Intent intent=new Intent(Login_Activity.this,Trainee_Main_Activity.class);
                    intent.putExtra("Actor","Trainee");
                    startActivity(intent);
                }
                if (strLoginID.equals("Trainer")&&strPassword.equals("")){
                    Intent intent=new Intent(Login_Activity.this,Trainer_Main_Activity.class);
                    intent.putExtra("Actor","Trainer");
                    startActivity(intent);
                }
                if (strLoginID.equals("TM")&&strPassword.equals("")){
                    Intent intent=new Intent(Login_Activity.this,TM_Main_Activity.class);
                    intent.putExtra("Actor","TM");
                    startActivity(intent);
                }
                if (strLoginID.equals("PM")&&strPassword.equals("")){
                    Intent intent=new Intent(Login_Activity.this,PM_Main_Activity.class);
                    intent.putExtra("Actor","PM");
                    startActivity(intent);
                }
                //Trainee Login Table
                DB_Register traineeobj=new DB_Register(Login_Activity.this);
                traineeobj.open();
                Cursor traineecursor=traineeobj.Get_Info();
                for (traineecursor.moveToFirst();!traineecursor.isAfterLast();traineecursor.moveToNext()){
                    if (strLoginID.equals(traineecursor.getString(4))){
                        if (strPassword.equals(traineecursor.getString(5))){
                            Intent intent=new Intent(Login_Activity.this,Trainee_Main_Activity.class);
                            intent.putExtra("Trainee",traineecursor.getString(4));
                            startActivity(intent);
                        }
                    }
                }
                traineeobj.close();

                //Trainer Login Table
                DB_Trainer trainerobj=new DB_Trainer(Login_Activity.this);
                trainerobj.open();
                Cursor trainercursor=trainerobj.Get_Info();
                for (trainercursor.moveToFirst();!trainercursor.isAfterLast();trainercursor.moveToNext()){
                    if (strLoginID.equals(trainercursor.getString(1))){
                        if (strPassword.equals(trainercursor.getString(2))){
                            Intent intent=new Intent(Login_Activity.this,Trainer_Main_Activity.class);
                            intent.putExtra("Trainer",trainercursor.getString(1));
                            startActivity(intent);
                        }
                    }
                }
                trainerobj.close();

                //TM Login Table
                DB_TM tmobj=new DB_TM(Login_Activity.this);
                tmobj.open();
                Cursor tmcursor=tmobj.Get_Info();
                for (tmcursor.moveToFirst();!tmcursor.isAfterLast();tmcursor.moveToNext()){
                    if (strLoginID.equals(tmcursor.getString(1))){
                        if (strPassword.equals(tmcursor.getString(2))){
                            Intent intent=new Intent(Login_Activity.this,TM_Main_Activity.class);
                            intent.putExtra("TM",tmcursor.getString(1));
                            startActivity(intent);
                        }
                    }
                }
                tmobj.close();

                //PM Login Table
                DB_PM pmobj=new DB_PM(Login_Activity.this);
                pmobj.open();
                Cursor pmcursor=pmobj.Get_Info();
                for (pmcursor.moveToFirst();!pmcursor.isAfterLast();pmcursor.moveToNext()){
                    if (strLoginID.equals(pmcursor.getString(1))){
                        if (strPassword.equals(pmcursor.getString(2))){
                            Intent intent=new Intent(Login_Activity.this,TM_Main_Activity.class);
                            intent.putExtra("PM",tmcursor.getString(1));
                            startActivity(intent);
                        }
                    }
                }
                pmobj.close();
            }


        });

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Activity.this,Register_Activity.class));
            }
        });


    }
}
