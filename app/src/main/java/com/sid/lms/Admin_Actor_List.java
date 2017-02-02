package com.sid.lms;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Admin_Actor_List extends AppCompatActivity {

    TextView tvtitle;
    ListView lv;
    String Actor="";
    List<String> list=new ArrayList<String>();
    Cursor res;
    int clickid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__actor__list);
        tvtitle=(TextView)findViewById(R.id.tvactorlist);
        lv=(ListView)findViewById(R.id.lvactor);

        Bundle extras=getIntent().getExtras();
        Actor=extras.getString("Actor");

        tvtitle.setText(Actor+" List:");
        switch (Actor){
            case "Trainer":
                DB_Trainer obj1=new DB_Trainer(Admin_Actor_List.this);
                try{obj1.open();
                res=obj1.Get_Info();
                for (res.moveToFirst();!res.isAfterLast();res.moveToNext()){
                    list.add(res.getString(3));
                }
                obj1.close();
                }catch (Exception e){
                    String error=e.toString();
                    Dialog d=new Dialog(Admin_Actor_List.this);
                    TextView tv=new TextView(Admin_Actor_List.this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.setTitle("Table is not created");
                    d.show();
                }
                break;
            case "Training Manager":
                DB_TM obj2=new DB_TM(Admin_Actor_List.this);
                try {
                    obj2.open();
                    res = obj2.Get_Info();
                    for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
                        list.add(res.getString(3));
                    }
                    obj2.close();
                }catch (Exception e){
                    String error=e.toString();
                    Dialog d=new Dialog(Admin_Actor_List.this);
                    TextView tv=new TextView(Admin_Actor_List.this);
                    tv.setText(error);
                    d.setTitle("Table is not created");
                    d.setContentView(tv);
                    d.show();
                }
                break;
            case "Project Manager":
                DB_PM obj3=new DB_PM(Admin_Actor_List.this);
                try {
                    obj3.open();
                    res = obj3.Get_Info();
                    for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
                        list.add(res.getString(3));
                    }
                    obj3.close();
                }catch (Exception e){
                    String error=e.toString();
                    Dialog d=new Dialog(Admin_Actor_List.this);
                    TextView tv=new TextView(Admin_Actor_List.this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.setTitle("Table is not created");
                    d.show();
                }
                break;
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clickid=i;
                String loginid,password,name;
                switch (Actor){
                    case "Trainer":
                        DB_Trainer obj1=new DB_Trainer(Admin_Actor_List.this);
                        obj1.open();
                        res=obj1.Get_Info(clickid+1);
                        res.moveToFirst();
                        loginid=res.getString(1);
                        password=res.getString(2);
                        name=res.getString(3);
                        Intent intent=new Intent(Admin_Actor_List.this,Admin_Actor_Info.class);
                        intent.putExtra("loginid",loginid);
                        intent.putExtra("password",password);
                        intent.putExtra("name",name);
                        intent.putExtra("Actor",Actor);
                        startActivity(intent);
                        obj1.close();
                        break;
                    case "Training Manager":
                        DB_TM obj2=new DB_TM(Admin_Actor_List.this);
                        obj2.open();
                        res=obj2.Get_Info(clickid+1);
                        res.moveToFirst();
                        loginid=res.getString(1);
                        password=res.getString(2);
                        name=res.getString(3);
                        Intent intent2=new Intent(Admin_Actor_List.this,Admin_Actor_Info.class);
                        intent2.putExtra("loginid",loginid);
                        intent2.putExtra("password",password);
                        intent2.putExtra("name",name);
                        intent2.putExtra("Actor",Actor);
                        startActivity(intent2);
                        obj2.close();
                        break;
                    case "Project Manager":
                        DB_PM obj3=new DB_PM(Admin_Actor_List.this);
                        obj3.open();
                        res.moveToFirst();
                        loginid=res.getString(1);
                        password=res.getString(2);
                        name=res.getString(3);
                        Intent intent3=new Intent(Admin_Actor_List.this,Admin_Actor_Info.class);
                        intent3.putExtra("loginid",loginid);
                        intent3.putExtra("password",password);
                        intent3.putExtra("name",name);
                        intent3.putExtra("Actor",Actor);
                        startActivity(intent3);
                        obj3.close();
                        break;

                }
            }


        });
    }
}
