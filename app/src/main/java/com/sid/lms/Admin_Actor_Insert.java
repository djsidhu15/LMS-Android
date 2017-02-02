package com.sid.lms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Admin_Actor_Insert extends AppCompatActivity {

    TextView tvactortitle,tvactorname;
    Button bactorinsert,bactorview;
    EditText etactorloginid,etactorpassword,etactorname;
    String Actor="";
    String loginid,password,actorname;
    int clickid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__actor__insert);

        tvactortitle=(TextView)findViewById(R.id.tvactortitle);
        tvactorname=(TextView)findViewById(R.id.tvactorname);
        bactorinsert=(Button)findViewById(R.id.bactorinsert);
        bactorview=(Button)findViewById(R.id.bactorview);
        etactorloginid=(EditText)findViewById(R.id.etactorloginid);
        etactorpassword=(EditText)findViewById(R.id.etactorpassword);
        etactorname=(EditText)findViewById(R.id.etactorname);

        Bundle extras=getIntent().getExtras();
        Actor=extras.getString("Actor");
        tvactortitle.setText(Actor+" Insert:");
        tvactorname.setText(Actor+" Name:");

        bactorinsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginid=etactorloginid.getText().toString();
                password=etactorpassword.getText().toString();
                actorname=etactorname.getText().toString();

                switch (Actor){
                    case "Trainer":

                        DB_Trainer obj1=new DB_Trainer(Admin_Actor_Insert.this);
                        obj1.open();
                        obj1.Insert(loginid,password,actorname);
                        obj1.close();
                        Toast.makeText(getBaseContext(),"Inserted to "+Actor,Toast.LENGTH_LONG).show();
                        break;
                    case "Training Manager":

                        DB_TM obj2=new DB_TM(Admin_Actor_Insert.this);
                        obj2.open();
                        obj2.Insert(loginid,password,actorname);
                        obj2.close();
                        Toast.makeText(getBaseContext(),"Inserted to "+Actor,Toast.LENGTH_LONG).show();
                        break;
                    case "Project Manager":

                        DB_PM obj3=new DB_PM(Admin_Actor_Insert.this);
                        obj3.open();
                        obj3.Insert(loginid,password,actorname);
                        obj3.close();
                        Toast.makeText(getBaseContext(),"Inserted to "+Actor,Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        bactorview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin_Actor_Insert.this,Admin_Actor_List.class);
                intent.putExtra("Actor",Actor);
                startActivity(intent);
            }
        });


    }
}
