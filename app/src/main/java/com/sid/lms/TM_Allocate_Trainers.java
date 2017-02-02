package com.sid.lms;

import android.app.Dialog;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class TM_Allocate_Trainers extends AppCompatActivity {

    List<String> list=new ArrayList<String>();
    List<String> list2=new ArrayList<String>();
    int clickid,clickid2;
    String Trainer="",TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm__allocate__trainers);

        final ListView listView=(ListView)findViewById(R.id.lvcourselist);

        DB_Course_Details obj1=new DB_Course_Details(TM_Allocate_Trainers.this);

        try {
            obj1.open();
            Cursor a = obj1.get_course();

            list.clear();
            for (a.moveToFirst(); !a.isAfterLast(); a.moveToNext()) {
                list.add(a.getString(2));
            }


            obj1.close();
        }catch (Exception e){
            String error=e.toString();
            Dialog d=new Dialog(TM_Allocate_Trainers.this);
            TextView tv=new TextView(TM_Allocate_Trainers.this);
            tv.setText(error);
            d.setTitle("Table is not created");
            d.setContentView(tv);
            d.show();
        }

        listView.setAdapter(new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,list));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                clickid=i;
                DB_Trainer obj2=new DB_Trainer(TM_Allocate_Trainers.this);
                obj2.open();
                Cursor b=obj2.Get_Info();

                list2.clear();
                for (b.moveToFirst();!b.isAfterLast();b.moveToNext()){

                    list2.add(b.getString(3));
                }
               final AlertDialog.Builder d=new AlertDialog.Builder(TM_Allocate_Trainers.this);
                ListView listView1=new ListView(TM_Allocate_Trainers.this);
                listView1.setAdapter(new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,list2));
                final String course_name=(String)adapterView.getItemAtPosition(i);
                d.setTitle("Allocate for "+course_name);
                d.setView(listView1);
                listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String trainername=(String)adapterView.getItemAtPosition(i);
                        DB_Allocate_Trainer obj=new DB_Allocate_Trainer(TM_Allocate_Trainers.this);
                        obj.open();
                        obj.Insert(course_name,trainername);
                        Toast.makeText(getBaseContext(),"Allocated",Toast.LENGTH_LONG).show();
                        obj.close();
                    }
                });
                d.setPositiveButton("Done",null);
                d.show();
                obj2.close();
            }
        });
    }
}
