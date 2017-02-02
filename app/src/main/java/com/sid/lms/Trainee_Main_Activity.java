package com.sid.lms;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Trainee_Main_Activity extends AppCompatActivity {

    String TAG;
    int clickid=0;
    ListView listView;
    List<String> list=new ArrayList<String>();
    String Traineeusername="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee__main_);

        Bundle extras=getIntent().getExtras();
        final String Actor=extras.getString("Actor");
        Log.d(TAG,"Actor="+Actor);
        Traineeusername=extras.getString("Trainee");
//        Toast.makeText(this,Traineeusername+" "+Actor,Toast.LENGTH_LONG).show();

        Button bmycourses=(Button)findViewById(R.id.bmycourses);
        bmycourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Trainee_Main_Activity.this,Trainee_Enrolled_Courses.class);
                intent.putExtra("Trainee",Traineeusername);
                startActivity(intent);
            }
        });

        listView=(ListView)findViewById(R.id.lvcourselist);



        final DB_Course_Details obj1=new DB_Course_Details(Trainee_Main_Activity.this);

        try {
            obj1.open();
            Cursor a = obj1.get_course();

            for (a.moveToFirst(); !a.isAfterLast(); a.moveToNext()) {
                list.add(a.getString(2));
            }


            obj1.close();
        }catch (Exception e){
            String error=e.toString();
            Dialog d=new Dialog(Trainee_Main_Activity.this);
            TextView tv=new TextView(Trainee_Main_Activity.this);
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
                obj1.open();
                String cno,ctitle,cduration,cdescription;
                Log.d(TAG,"Reached1");
                Cursor b=obj1.get_info(clickid+1);
                Log.d(TAG,"Reached2");
                b.moveToFirst();
                Log.d(TAG,"Reached3");
                cno=b.getString(1);
                Log.d(TAG,"Reached4");
                Log.d(TAG,"cno="+cno);
                ctitle=b.getString(2);
                cduration=b.getString(3);
                cdescription=b.getString(4);
                obj1.close();
                Intent intent=new Intent(Trainee_Main_Activity.this,Trainee_Course_Info.class);
                intent.putExtra("cno",cno);
                intent.putExtra("ctitle",ctitle);
                intent.putExtra("cduration",cduration);
                intent.putExtra("cdescription",cdescription);
                intent.putExtra("Trainee",Traineeusername);
                startActivity(intent);
            }
        });
    }
}
