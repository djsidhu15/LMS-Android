package com.sid.lms;

import android.app.Dialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Trainee_Send_Feedback extends AppCompatActivity {

    List<String> list=new ArrayList<String>();
    String feedback,rating="",course,trainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee__send__feedback);
        Button bsendfeedback2=(Button)findViewById(R.id.send);
        final RatingBar ratingBar=(RatingBar)findViewById(R.id.ratingbar);
        final EditText etfeedback=(EditText)findViewById(R.id.etfeedback);
        final Spinner spinner=(Spinner)findViewById(R.id.spinner);
        Bundle extras=getIntent().getExtras();
        final String Traineeusername=extras.getString("Trainee");
//        Toast.makeText(getApplicationContext(),Traineeusername,Toast.LENGTH_LONG).show();


        DB_Approve obj=new DB_Approve(this);
        obj.open();
        Cursor res=obj.Get_Info(Traineeusername);
        for (res.moveToFirst();!res.isAfterLast();res.moveToNext()){
            list.add(res.getString(2));
        }
        obj.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        spinner.setAdapter(adapter);

        bsendfeedback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Insert feedback
                rating=""+ratingBar.getRating();
                course=""+spinner.getSelectedItem();
                feedback=etfeedback.getText().toString();

               try {
                   DB_Allocate_Trainer obj2 = new DB_Allocate_Trainer(Trainee_Send_Feedback.this);
                   obj2.open();
                   trainer = obj2.Get_Info(course);
                   obj2.close();

                   //Insert
                   DB_Feedback obj3 = new DB_Feedback(Trainee_Send_Feedback.this);
                   obj3.open();
//                   Toast.makeText(getApplicationContext(),Traineeusername,Toast.LENGTH_LONG).show();
                   obj3.Insert(Traineeusername, course, rating, feedback, trainer);
                   obj3.close();
                   Toast.makeText(getApplicationContext(), "Feedback sent for " + course, Toast.LENGTH_LONG).show();
               }catch (Exception e){
                   TextView tv=new TextView(getApplicationContext());
                   Dialog d=new Dialog(getApplicationContext());
                   tv.setText(e.toString());
                   d.setContentView(tv);
                   d.show();
               }
            }
        });
    }
}
