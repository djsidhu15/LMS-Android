package com.sid.lms;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PM_Trainees_List_Activity extends AppCompatActivity {

    String TAG;
    int clickid=0;
    ListView listView;
    List<String> list=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pm__trainees__list_);

        listView=(ListView)findViewById(R.id.lvtraineelist);

        final DB_Register obj1=new DB_Register(PM_Trainees_List_Activity.this);

        try {
            obj1.open();
            Cursor a = obj1.Get_Info();

            for (a.moveToFirst(); !a.isAfterLast(); a.moveToNext()) {

                list.add(a.getString(1)+" "+a.getString(2));
            }


            obj1.close();
        }catch (Exception e){
            String error=e.toString();
            Dialog d=new Dialog(PM_Trainees_List_Activity.this);
            TextView tv=new TextView(PM_Trainees_List_Activity.this);
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
                Intent intent=new Intent(PM_Trainees_List_Activity.this,PM_Trainee_Info.class);
                intent.putExtra("clickid",clickid);
                startActivity(intent);
            }
        });
    }
}
