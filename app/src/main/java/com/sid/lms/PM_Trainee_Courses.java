package com.sid.lms;

import android.app.Dialog;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PM_Trainee_Courses extends AppCompatActivity {

    TextView tvtitle;
    ListView listView;
    List<String> list=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pm__trainee__courses);
        tvtitle=(TextView)findViewById(R.id.title);
        listView=(ListView)findViewById(R.id.lvcources);

        Bundle extras=getIntent().getExtras();
        String Traineeusername=extras.getString("Trainee");

        DB_Approve obj=new DB_Approve(this);
        obj.open();
        Cursor a=obj.Get_Info(Traineeusername);
        for (a.moveToFirst();!a.isAfterLast();a.moveToNext()){
            list.add(a.getString(2));
        }
        obj.close();

        ArrayAdapter adapter=new ArrayAdapter(PM_Trainee_Courses.this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String course=adapterView.getItemAtPosition(i).toString();
                DB_Course_Details obj2=new DB_Course_Details(getApplicationContext());
                obj2.open();
                Cursor b=obj2.get_course(course);
                b.moveToFirst();
                String courseno=b.getString(1);
                String duration=b.getString(3);
                String desciption=b.getString(4);
                obj2.close();

                LinearLayout ll=new LinearLayout(PM_Trainee_Courses.this);
                ll.setOrientation(LinearLayout.VERTICAL);
                TextView tv=new TextView(PM_Trainee_Courses.this);
                tv.setText("Course no: "+courseno);
                ll.addView(tv);
                TextView tv1=new TextView(PM_Trainee_Courses.this);
                tv1.setText("Title: "+course);
                ll.addView(tv1);
                TextView tv2=new TextView(PM_Trainee_Courses.this);
                tv2.setText("Duration: "+duration);
                ll.addView(tv2);
                TextView tv3=new TextView(PM_Trainee_Courses.this);
                tv3.setText("Description: "+desciption);
                ll.addView(tv3);
                Dialog d=new Dialog(PM_Trainee_Courses.this);
                d.setContentView(ll);
                d.show();

            }
        });
    }
}
