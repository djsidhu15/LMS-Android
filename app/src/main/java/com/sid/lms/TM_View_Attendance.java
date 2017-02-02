package com.sid.lms;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TM_View_Attendance extends AppCompatActivity {

    List<String> list=new ArrayList<String>();
    ListView listView;
    String date="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm__view__attendance);
        listView=(ListView)findViewById(R.id.lv);

        DB_Attendance obj=new DB_Attendance(this);
        obj.open();
        Cursor a=obj.Get_Info();
        for (a.moveToFirst();!a.isAfterLast();a.moveToNext()){

            list.add(a.getString(1));
        }
        obj.close();

        final ArrayAdapter adapter=new ArrayAdapter(TM_View_Attendance.this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                date=adapterView.getItemAtPosition(i).toString();
                Intent intent=new Intent(TM_View_Attendance.this,TM_View_Attendance_2.class);
                intent.putExtra("Date",date);
                startActivity(intent);
            }
        });

    }
}
