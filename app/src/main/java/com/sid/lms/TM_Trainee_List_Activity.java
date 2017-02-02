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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TM_Trainee_List_Activity extends AppCompatActivity {

    ListView listView;
    List<String> list=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm__trainee__list_);
        ListView listView=(ListView)findViewById(R.id.lv);

        try {
            DB_Feedback obj = new DB_Feedback(this);

            obj.open();
            Cursor cursor = obj.Get_Info();
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                list.add(cursor.getString(1)+"-"+cursor.getString(2));
            }
            obj.close();
        }catch (Exception e){
            Dialog dialog=new Dialog(this);
            TextView tv=new TextView(this);
            tv.setText(e.toString());
            dialog.setContentView(tv);
            dialog.show();
        }

        listView.setAdapter(new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,list));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int clickid=i;
                DB_Feedback obj1=new DB_Feedback(getApplicationContext());
                String traineeusername,course,rating,feedback,trainer;
                obj1.open();
                Cursor res=obj1.Get_Info(clickid+1);
                res.moveToFirst();
                traineeusername=(String)adapterView.getItemAtPosition(i);
                course=res.getString(2);
                rating=res.getString(3);
                feedback=res.getString(4);
                trainer=res.getString(5);
                Intent intent=new Intent(getApplicationContext(),TM_View_Feedback.class);
                intent.putExtra("traineeusername",traineeusername);
                intent.putExtra("course",course);
                intent.putExtra("rating",rating);
                intent.putExtra("feedback",feedback);
                intent.putExtra("trainer",trainer);
                startActivity(intent);
                obj1.close();
            }
        });
    }
}
