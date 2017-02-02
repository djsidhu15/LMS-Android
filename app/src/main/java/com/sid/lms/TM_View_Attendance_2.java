package com.sid.lms;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TM_View_Attendance_2 extends AppCompatActivity {

    String date="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm__view__attendance_2);
        LinearLayout ll = (LinearLayout) findViewById(R.id.llayout);
        Bundle extras=getIntent().getExtras();
        date=extras.getString("Date");

        CheckBox[] cb=new CheckBox[1000];
        DB_Attendance obj=new DB_Attendance(TM_View_Attendance_2.this);
        obj.open();
        Cursor a=obj.Get_Info(date);

        a.moveToFirst();
        for (int i=1;i<a.getColumnCount();i++){

            if (a.getString(i).equals("1")){
                cb[i] =new CheckBox(TM_View_Attendance_2.this);
                cb[i].setText(a.getColumnName(i));
                cb[i].setChecked(true);
                cb[i].setClickable(false);
                ll.addView(cb[i]);
            }
            if (a.getString(i).equals("0")){
                cb[i]=new CheckBox(TM_View_Attendance_2.this);
                cb[i].setText(a.getColumnName(i));
                cb[i].setChecked(false);
                cb[i].setClickable(false);
                ll.addView(cb[i]);
            }
        }
        obj.close();
    }
    }

