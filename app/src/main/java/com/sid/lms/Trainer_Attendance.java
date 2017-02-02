package com.sid.lms;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Trainer_Attendance extends AppCompatActivity {

    CheckBox[] checkbox=new CheckBox[1000];
    int total=0,checkboxid=1;
    Button submit;
    String course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer__attendance);
        final Context context = Trainer_Attendance.this;
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        submit=(Button)findViewById(R.id.submit);
        Bundle extras = getIntent().getExtras();
        String Trainerusername = extras.getString("Trainer");
        String Trainername="";


        DB_Trainer obj3=new DB_Trainer(context);
        obj3.open();
        Trainername=obj3.Get_Trainer_name(Trainerusername);
        obj3.close();



        final String date = extras.getString("Date");


        try {
            DB_Allocate_Trainer obj = new DB_Allocate_Trainer(context);
            obj.open();
            course= obj.Get_Course(Trainername);
            obj.close();


            DB_Approve obj2 = new DB_Approve(context);
            obj2.open();
            Cursor a = obj2.Get_Trainee(course);
            int i = 1;
            for (a.moveToFirst(); !a.isAfterLast(); i++, a.moveToNext()) {
                String username = a.getString(1);
                checkbox[i] = new CheckBox(context);
                checkbox[i].setId(checkboxid++);
                checkbox[i].setText(username);
                checkbox[i].setChecked(false);
                ll.addView(checkbox[i]);
                total++;
            }
        }catch(Exception e){
            TextView tv=new TextView(context);
            Dialog d=new Dialog(context);
            tv.setText(e.toString());
            d.setContentView(tv);
            d.show();
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB_Attendance obj=new DB_Attendance(context);
                obj.open();
                for (int i=1;i<=total;i++){
                    String username=checkbox[i].getText().toString();
                    if (checkbox[i].isChecked())
                        obj.Insert_Present(username+"_"+course,date);
                    else
                        obj.Insert_Absent(username+"_"+course,date);
                }
            }
        });

    }
}
