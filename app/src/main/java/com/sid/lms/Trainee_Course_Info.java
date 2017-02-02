package com.sid.lms;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class Trainee_Course_Info extends AppCompatActivity {

    TextView courseno,title,duration,description;
    Button apply;
    String Actor="",Trainer="",Course="",Traineeusername="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee__course__info);
        courseno=(TextView)findViewById(R.id.etcourseno);
        title=(TextView)findViewById(R.id.tvdescription2);
        duration=(TextView)findViewById(R.id.tvduration2);
        description=(TextView)findViewById(R.id.tvmaterialsavailable2);
        apply=(Button)findViewById(R.id.apply);

        Bundle extras=getIntent().getExtras();
        courseno.setText(extras.getString("cno"));
        title.setText(extras.getString("ctitle"));
        duration.setText(extras.getString("cduration"));
        description.setText(extras.getString("cdescription"));
        Actor=extras.getString("Actor");
        Course=extras.getString("ctitle");
        Traineeusername=extras.getString("Trainee");

        try {
            ;
            DB_Allocate_Trainer obj2 = new DB_Allocate_Trainer(Trainee_Course_Info.this);
            obj2.open();
            Trainer = obj2.Get_Info(Course);
            obj2.close();

        }catch (Exception e){
            String error=e.toString();
            Dialog d=new Dialog(Trainee_Course_Info.this);
            TextView tv=new TextView(Trainee_Course_Info.this);
            tv.setText(error);
            d.setContentView(tv);
            d.show();
        }

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB_Apply obj=new DB_Apply(Trainee_Course_Info.this);
               try {
                   obj.open();
                   obj.Insert(Traineeusername, Course, Trainer);
                   Toast.makeText(Trainee_Course_Info.this, "Successfully Applied", Toast.LENGTH_LONG).show();
                   obj.close();
               }catch (Exception e){
                   String error=e.toString();
                   Dialog d=new Dialog(Trainee_Course_Info.this);
                   TextView tv=new TextView(Trainee_Course_Info.this);
                   tv.setText(error);
                   d.setContentView(tv);
                   d.show();
               }
            }
        });
    }
}
