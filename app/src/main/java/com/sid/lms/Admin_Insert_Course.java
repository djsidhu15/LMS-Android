package com.sid.lms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin_Insert_Course extends AppCompatActivity {

    EditText etno,ettitle,etduration,etdescription;
    Button insert,viewcourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__insert__course);
        etno=(EditText)findViewById(R.id.etcourseno);
        ettitle=(EditText)findViewById(R.id.etcoursetitle);
        etduration=(EditText)findViewById(R.id.etcourseduration);
        etdescription=(EditText)findViewById(R.id.etcoursedescription);
        insert=(Button)findViewById(R.id.binsertcourse);
        viewcourse=(Button)findViewById(R.id.bviewcourse);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String no,title,duration,description;
                no=etno.getText().toString();
                title=ettitle.getText().toString();
                duration=etduration.getText().toString();
                description=etdescription.getText().toString();

                DB_Course_Details obj1=new DB_Course_Details(Admin_Insert_Course.this);
                obj1.open();
                obj1.Insert_course(no,title,duration,description);
                Toast.makeText(getBaseContext(),"Inserted "+title,Toast.LENGTH_LONG).show();
                obj1.close();
            }
        });

        viewcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Insert_Course.this,Admin_Course_List.class));
            }
        });

    }
}
