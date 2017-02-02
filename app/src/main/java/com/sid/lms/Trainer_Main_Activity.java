package com.sid.lms;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Trainer_Main_Activity extends AppCompatActivity {

    List<String> list=new ArrayList<String>();
    Button bviewfeedback,add;
    String TAG,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer__main_);
        final ListView listView=(ListView)findViewById(R.id.lv);
        add=(Button)findViewById(R.id.baddd);
        final EditText etdate=(EditText)findViewById(R.id.etdate);


        Bundle extras=getIntent().getExtras();
        String Actor=extras.getString("Actor");
        final String Trainer=extras.getString("Trainer");

        bviewfeedback=(Button)findViewById(R.id.viewfeedback);

        bviewfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Trainer_Main_Activity.this,Trainer_Trainee_List.class));
            }
        });

        DB_Attendance obj=new DB_Attendance(this);
        obj.open();
        Cursor a=obj.Get_Info();
        for (a.moveToFirst();!a.isAfterLast();a.moveToNext()){

            list.add(a.getString(1));
            Log.d(TAG,"Date="+a.getString(1));
        }
        obj.close();

        final ArrayAdapter adapter=new ArrayAdapter(Trainer_Main_Activity.this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date=etdate.getText().toString();
                DB_Attendance obj2=new DB_Attendance(Trainer_Main_Activity.this);
                obj2.open();
                obj2.Insert_Date(date);
                Toast.makeText(Trainer_Main_Activity.this,"Inserted "+date,Toast.LENGTH_LONG).show();
                obj2.close();

                DB_Attendance obj=new DB_Attendance(Trainer_Main_Activity.this);
                obj.open();
                Cursor a=obj.Get_Info();
                list.clear();
                int aa=a.getColumnIndex(obj.COLUMN_DATE);
                for (a.moveToFirst();!a.isAfterLast();a.moveToNext()){
                    list.add(a.getString(aa));
                }
                obj.close();

                ArrayAdapter adapter=new ArrayAdapter(Trainer_Main_Activity.this,android.R.layout.simple_list_item_1,list);
                listView.setAdapter(adapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Trainer_Main_Activity.this,Trainer_Attendance.class);
                intent.putExtra("Trainer",Trainer);
                date=adapterView.getItemAtPosition(i).toString();
                intent.putExtra("Date",date);
                startActivity(intent);
            }
        });
    }
}
