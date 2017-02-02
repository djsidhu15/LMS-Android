package com.sid.lms;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PM_Trainee_Info extends AppCompatActivity {

    TextView etfname,etlname,etmailid,etusername,etpassword,etphone,etdept,etsubgroup,etlocation;
    String Traineeusername;
    int clickid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pm__trainee__info);

        etfname=(TextView)findViewById(R.id.etfname);
        etlname=(TextView)findViewById(R.id.etlname);
        etmailid=(TextView)findViewById(R.id.etmailid);
        etusername=(TextView)findViewById(R.id.etusername);
        etpassword=(TextView)findViewById(R.id.etpassword2);
        etphone=(TextView)findViewById(R.id.etphone);
        etdept=(TextView)findViewById(R.id.etdepartment);
        etsubgroup=(TextView)findViewById(R.id.etsubgroup);
        etlocation=(TextView)findViewById(R.id.etlocation);

        Button enrolledcources=(Button)findViewById(R.id.enrolledcources);


        Bundle extras=getIntent().getExtras();
        clickid=extras.getInt("clickid");
        DB_Register obj2=new DB_Register(PM_Trainee_Info.this);
        obj2.open();
        Cursor res=obj2.Get_Info(clickid+1);
        res.moveToFirst();
        etfname.setText(res.getString(1));
        etlname.setText(res.getString(2));
        etmailid.setText(res.getString(3));
        etusername.setText(res.getString(4));
        etpassword.setText(res.getString(5));
        etphone.setText(res.getString(6));
        etdept.setText(res.getString(7));
        etsubgroup.setText(res.getString(8));
        etlocation.setText(res.getString(9));
        Traineeusername=res.getString(4);
        obj2.close();

        enrolledcources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),PM_Trainee_Courses.class);
                intent.putExtra("Trainee",Traineeusername);
                startActivity(intent);
            }
        });
    }
}
