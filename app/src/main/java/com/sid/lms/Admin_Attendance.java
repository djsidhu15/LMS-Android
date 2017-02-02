package com.sid.lms;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Admin_Attendance extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__attendance);
        tv=(TextView)findViewById(R.id.tvlist);

        DB_Attendance obj=new DB_Attendance(this);
        obj.open();
        Cursor res=obj.Get_Info();
        String data="";
        for (int i=1;i<res.getColumnCount();i++){
            data+=res.getColumnName(i)+" ";
        }
        data+="\n";
        for (res.moveToFirst();!res.isAfterLast();res.moveToNext()){
            for (int i=1;i<res.getColumnCount();i++){
                data+=res.getString(i)+" ";
            }
            data+="\n";
        }
        int a=res.getColumnCount();
//        Toast.makeText(Admin_Attendance.this,""+a,Toast.LENGTH_LONG).show();
//        Toast.makeText(Admin_Attendance.this,res.getColumnName(3),Toast.LENGTH_LONG).show();
        obj.close();
        tv.setText(data);
    }
}
