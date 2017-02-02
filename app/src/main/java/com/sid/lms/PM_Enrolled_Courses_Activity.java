package com.sid.lms;

import android.app.Dialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PM_Enrolled_Courses_Activity extends AppCompatActivity {

    String TAG;
    Button approve;
    LinearLayout ll;
    int totalcheckbox=0,checkboxID=1;
    String name="",username="",course="",trainer="";
    CheckBox[] checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pm__enrolled__courses_);
        ll=(LinearLayout)findViewById(R.id.ll);
        approve=(Button)findViewById(R.id.approve);

        DB_Apply apply=new DB_Apply(this);
        apply.open();
        Cursor cursor=apply.Get_Info();
        /*for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            totalcheckbox++;
        }*/
        final DB_Register register=new DB_Register(this);
        register.open();


//           final CheckBox[] checkBox = new CheckBox[totalcheckbox];
        try {
            checkbox = new CheckBox[cursor.getCount()+1];

            int no=1;
           for (cursor.moveToFirst(); !cursor.isAfterLast(); no++,cursor.moveToNext()) {
               username = cursor.getString(1);
               course = cursor.getString(2);
               name = register.Get_Info(username);
               Log.d(TAG, "Name=" + name);
               Log.d(TAG, "Course=" + course);
               Log.d(TAG, "Username=" + username);
               checkbox[no]=new CheckBox(this);
               checkbox[no].setId(checkboxID++);
               checkbox[no].setText(name + " - " + course);
               checkbox[no].setChecked(false);
//               Toast.makeText(PM_Enrolled_Courses_Activity.this,""+no,Toast.LENGTH_SHORT).show();
               ll.addView(checkbox[no]);
               totalcheckbox++;
           }
       }catch (Exception e){
            TextView tv=new TextView(PM_Enrolled_Courses_Activity.this);
            Dialog d=new Dialog(PM_Enrolled_Courses_Activity.this);
            tv.setText(e.toString());
            d.setContentView(tv);
            d.show();
       }
        apply.close();
        register.close();

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DB_Apply apply1=new DB_Apply(PM_Enrolled_Courses_Activity.this);
                apply1.open();

                DB_Register register1=new DB_Register(PM_Enrolled_Courses_Activity.this);
                register1.open();

                DB_Approve approve1=new DB_Approve(PM_Enrolled_Courses_Activity.this);
                approve1.open();
                int i=0;
                for (int no=1;no<=totalcheckbox;no++){
                    try {

                        if (checkbox[no].isChecked()) {

//                            Toast.makeText(PM_Enrolled_Courses_Activity.this, "Checked" + no, Toast.LENGTH_LONG).show();


                            Cursor res = apply1.Get_Info();
                            int j = 1;
                            for (res.moveToFirst(); j <= no; j++, res.moveToNext()) {

                                if (j == no) {
                                    username = res.getString(1);
                                    name = register1.Get_Info(username);
                                    course = res.getString(2);
                                    trainer = res.getString(3);
                                    approve1.Insert(username, course, trainer);
                                    DB_Attendance obj=new DB_Attendance(PM_Enrolled_Courses_Activity.this);
                                    obj.open();
                                    obj.Add_Column(username+"_"+course);
                                    obj.close();
//                                    Toast.makeText(PM_Enrolled_Courses_Activity.this, name +" "+no, Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        }catch(Exception e){
                        TextView tv = new TextView(PM_Enrolled_Courses_Activity.this);
                        Dialog d = new Dialog(PM_Enrolled_Courses_Activity.this);
                        tv.setText(e.toString()+"\n\nPossibly the course is already allocated");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                apply1.close();
                register1.close();
                approve1.close();
                Toast.makeText(PM_Enrolled_Courses_Activity.this,"Successfully Approved",Toast.LENGTH_LONG).show();
            }
        });

    }
}
