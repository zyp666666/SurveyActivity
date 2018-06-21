package com.zyp.surveyactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by zyp on 2018/6/20.
 */
public class SurveyActivityafter extends AppCompatActivity{

    private ColorProgressBar bar;
    private int mScore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveyafter);


        mScore = getIntent().getIntExtra("score",0);

        TextView textView = (TextView) findViewById(R.id.survey_tv);
        bar = (ColorProgressBar) findViewById(R.id.bar);

        textView.setText(String.valueOf(mScore));
        setType(mScore);
        bar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bar.setMaxProgress(100);
                bar.setCurrentProgress(mScore);
            }
        });
    }

    public void setType(int num) {
        if (num >= 30 && num <=47){
            findViewById(R.id.type_1).setVisibility(View.VISIBLE);
            findViewById(R.id.type_introduce_1).setVisibility(View.VISIBLE);
        }else if (num >= 48 && num <=66) {
            findViewById(R.id.type_2).setVisibility(View.VISIBLE);
            findViewById(R.id.type_introduce_2).setVisibility(View.VISIBLE);
        } else if (num >= 67 && num <= 85) {
            findViewById(R.id.type_3).setVisibility(View.VISIBLE);
            findViewById(R.id.type_introduce_3).setVisibility(View.VISIBLE);
        } else if (num >= 86 && num <= 100) {
            findViewById(R.id.type_4).setVisibility(View.VISIBLE);
            findViewById(R.id.type_introduce_4).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.type_0).setVisibility(View.VISIBLE);
            findViewById(R.id.type_introduce_0).setVisibility(View.VISIBLE);
        }
    }
}
