package com.sid.lms;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.DynamicDrawableSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Admin_Course_List extends AppCompatActivity {

    String TAG;
    int clickid=0;
    ListView listView;
    List<String> list=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__course__list);

        listView=(ListView)findViewById(R.id.lvcourselist);



        final DB_Course_Details obj1=new DB_Course_Details(Admin_Course_List.this);

        try {
            obj1.open();
            Cursor a = obj1.get_course();
            String[] coursetitle = new String[]{"a", "a", "u", "p"};
            int[] id = new int[]{1, 2, 1, 1};
            int i = 0;

            for (a.moveToFirst(); !a.isAfterLast(); i++, a.moveToNext()) {
                //id[i]=a.getInt(0);
                //coursetitle[i]=a.getString(2);
                //if (!a.getString(2).equals(""))
                list.add(a.getString(2));
            }


            obj1.close();
        }catch (Exception e){
            String error=e.toString();
            Dialog d=new Dialog(Admin_Course_List.this);
            TextView tv=new TextView(Admin_Course_List.this);
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
                Intent intent=new Intent(Admin_Course_List.this,Admin_Course_Info_View.class);
                intent.putExtra("cno",cno);
                intent.putExtra("ctitle",ctitle);
                intent.putExtra("cduration",cduration);
                intent.putExtra("cdescription",cdescription);
                startActivity(intent);
            }
        });




    }
}
