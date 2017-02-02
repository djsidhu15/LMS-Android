package com.sid.lms;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PM_Trainer_Info extends AppCompatActivity {

    TextView etloginid,etpassword,etname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pm__trainer__info);

        etloginid=(TextView)findViewById(R.id.etactorloginid);
        etpassword=(TextView)findViewById(R.id.etactorpassword);
        etname=(TextView)findViewById(R.id.etactorname);

        Bundle extras=getIntent().getExtras();
        int clickid=extras.getInt("clickid");

        DB_Trainer obj1=new DB_Trainer(PM_Trainer_Info.this);
        obj1.open();
        Cursor a=obj1.Get_Info(clickid+1);
        a.moveToFirst();
        etloginid.setText(a.getString(1));
        etpassword.setText(a.getString(2));
        etname.setText(a.getString(3));
        obj1.close();
    }
}
