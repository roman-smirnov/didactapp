package com.didactapp.didact.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.didactapp.didact.R;

public class ContentActivity extends BaseActivity {


    /* ui refs */
    private TextView explanation;
    private ImageView illustration;

    private LinearLayout multiple_choice_container;
    private TextView question;
    private RadioGroup answer_container;
    private RadioButton answer1;
    private RadioButton answer2;
    private RadioButton answer3;
    private RadioButton answer4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_detail);

        /* get view component references */
        explanation = findViewById(R.id.explanation_text);
        illustration = findViewById(R.id.illustration_image);
        multiple_choice_container = findViewById(R.id.multiple_choice_container);
        question = findViewById(R.id.multiple_choice_question);
        answer_container = findViewById(R.id.multiple_choice_answer_container);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}