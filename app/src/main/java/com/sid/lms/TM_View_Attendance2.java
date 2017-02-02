package com.sid.lms;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TM_View_Attendance2 extends AppCompatActivity {

    String date="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm__view__attendance2);
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        Bundle extras=getIntent().getExtras();
        date=extras.getString("Date");

        DB_Attendance obj=new DB_Attendance(TM_View_Attendance2.this);
        obj.open();
        Cursor a=obj.Get_Info(date);

        a.moveToFirst();
            for (int i=2;i<a.getColumnCount();i++){
                if (a.getString(i)=="1"){
                    CheckBox cb=new CheckBox(TM_View_Attendance2.this);
                    cb.setText(a.getColumnName(i));
                    cb.setChecked(true);
                    ll.addView(cb);
                    Toast.makeText(TM_View_Attendance2.this,"Added",Toast.LENGTH_SHORT).show();
                }
                else if (a.getString(i)=="0"){
                    CheckBox cb=new CheckBox(TM_View_Attendance2.this);
                    cb.setText(a.getColumnName(i));
                    cb.setChecked(false);
                    ll.addView(cb);
                }
            }
        obj.close();
    }
}
