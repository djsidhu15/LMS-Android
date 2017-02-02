package com.sid.lms;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Trainee_Enrolled_Courses extends AppCompatActivity {

    String TAG;
    ListView listView;
    List<String> list2=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee__enrolled__courses);
        Button bsendbutton=(Button)findViewById(R.id.bsendfeedback);
        listView=(ListView)findViewById(R.id.lv);
        Bundle extras=getIntent().getExtras();
        final String Traineeusername=extras.getString("Trainee");
//        Toast.makeText(getApplicationContext(),Traineeusername,Toast.LENGTH_LONG).show();


        Log.d(TAG,"Trainee= "+ Traineeusername);
        bsendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Trainee_Enrolled_Courses.this,Trainee_Send_Feedback.class);
                intent.putExtra("Trainee",Traineeusername);
                startActivity(intent);
            }
        });



       try {
           DB_Approve obj = new DB_Approve(this);

           obj.open();
           Cursor cursor = obj.Get_Info(Traineeusername);
//           Toast.makeText(this, cursor.getCount()+"o", Toast.LENGTH_LONG).show();
           for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
               list2.add(cursor.getString(2));
           }
           obj.close();
       }catch (Exception e){
           Dialog dialog=new Dialog(this);
           TextView tv=new TextView(this);
           tv.setText(e.toString());
           dialog.setContentView(tv);
           dialog.show();
       }

        listView.setAdapter(new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,list2));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String cno,ctitle,cduration,cdescription,itemname;
                itemname=(String)adapterView.getItemAtPosition(i);
                DB_Course_Details obj2=new DB_Course_Details(Trainee_Enrolled_Courses.this);
                obj2.open();
                Cursor res=obj2.get_course(itemname);
                res.moveToFirst();
                cno=res.getString(1);
                ctitle=res.getString(2);
                cduration=res.getString(3);
                cdescription=res.getString(4);

                obj2.close();
                Intent intent=new Intent(Trainee_Enrolled_Courses.this,Trainee_Course_Info.class);
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
