package com.sid.lms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class TM_View_Feedback extends AppCompatActivity {

    String Traineeusername,course,rating,feedback,trainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm__view__feedback);
        TextView tvtitle=(TextView)findViewById(R.id.traineeusername);
        TextView tvtrainer=(TextView)findViewById(R.id.trainer);
        RatingBar ratingBar=(RatingBar)findViewById(R.id.ratingbar);
        TextView tvfeedback=(TextView)findViewById(R.id.tvfeedback);

        Bundle extras=getIntent().getExtras();
        Traineeusername=extras.getString("traineeusername");
        course=extras.getString("course");
        rating=extras.getString("rating");
        feedback=extras.getString("feedback");
        trainer=extras.getString("trainer");

        tvtitle.setText(Traineeusername);
        tvtrainer.setText("Trainer: "+trainer);
        ratingBar.setRating(Float.parseFloat(rating));
        ratingBar.setIsIndicator(true);
        tvfeedback.setText(feedback);

    }
}
