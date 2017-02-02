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

public class PM_Trainers_List extends AppCompatActivity {

    String TAG;
    int clickid=0;
    ListView listView;
    List<String> list=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pm__trainers__list);

        listView=(ListView)findViewById(R.id.lvtrainerlist);

        final DB_Trainer obj1=new DB_Trainer(PM_Trainers_List.this);

        try {
            obj1.open();
            Cursor a = obj1.Get_Info();

            for (a.moveToFirst(); !a.isAfterLast(); a.moveToNext()) {

                list.add(a.getString(3));
            }


            obj1.close();
        }catch (Exception e){
            String error=e.toString();
            Dialog d=new Dialog(PM_Trainers_List.this);
            TextView tv=new TextView(PM_Trainers_List.this);
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
                Intent intent=new Intent(PM_Trainers_List.this,PM_Trainer_Info.class);
                intent.putExtra("clickid",clickid);
                startActivity(intent);
            }
        });
    }
    }

