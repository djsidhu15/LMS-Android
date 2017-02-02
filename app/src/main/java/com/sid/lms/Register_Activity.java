package com.sid.lms;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register_Activity extends AppCompatActivity {
    Boolean worked=true;

    EditText etfname,etlname,etmailid,etusername,etpassword2,etphone,etdepartment,etsubgroup,etlocation;
    String fname="",lname="",mailid="",username="",password2="",phone="",department="",subgroup="",location="";
    Button bregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        etfname=(EditText)findViewById(R.id.etfname);
        etlname=(EditText)findViewById(R.id.etlname);
        etmailid=(EditText)findViewById(R.id.etmailid);
        etusername=(EditText)findViewById(R.id.etusername);
        etpassword2=(EditText)findViewById(R.id.etpassword2);
        etphone=(EditText)findViewById(R.id.etphone);
        etdepartment=(EditText)findViewById(R.id.etdepartment);
        etsubgroup=(EditText)findViewById(R.id.etsubgroup);
        etlocation=(EditText)findViewById(R.id.etlocation);

        bregister=(Button)findViewById(R.id.bregister);

        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname=etfname.getText().toString();
                lname=etlname.getText().toString();
                mailid=etmailid.getText().toString();
                username=etusername.getText().toString();
                password2=etpassword2.getText().toString();
                phone=etphone.getText().toString();
                department=etdepartment.getText().toString();
                subgroup=etsubgroup.getText().toString();
                location=etlocation.getText().toString();


                try {

                    DB_Register reg = new DB_Register(Register_Activity.this);
                    reg.open();
                    reg.register(fname, lname, mailid, username, password2, phone, department, subgroup, location);
                    reg.close();
                    Toast.makeText(getBaseContext(), "Successfully registered.", Toast.LENGTH_LONG).show();

                }catch (Exception e){
                    worked=false;
                    String error=e.toString();
                    Dialog d=new Dialog(Register_Activity.this);
                    d.setTitle("Dang it!");
                    TextView tv=new TextView(Register_Activity.this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }finally {
                    if(worked){
                        Dialog d=new Dialog(Register_Activity.this);
                        d.setTitle("HeckYea!");
                        TextView tv=new TextView(Register_Activity.this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }
                }
            }
        });


    }
}
