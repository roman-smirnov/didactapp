package com.didactapp.didact.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.didactapp.didact.R;
import com.didactapp.didact.contracts.SectionDetailContract;
import com.didactapp.didact.entities.Section;
import com.didactapp.didact.utils.Constants;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Random;


/* this activity displays the contents of a single section */
public class SectionDetailActivity extends BaseActivity implements SectionDetailContract.View, View.OnClickListener {

    /* view refs */
    private TextView explanation;
    private ImageView illustration;
    private TextView question;
    private RadioGroup multChoice;
    private RadioButton answer1;
    private RadioButton answer2;
    private RadioButton answer3;
    private RadioButton answer4;
    private Button submit;
    private ScrollView scrollView;

    /* used for multiple choice question */
    private int correctAnswerId;
    private int currentAnswerId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_detail);

        /* get layout view references */
        explanation = findViewById(R.id.section_detail_explanation);
        illustration = findViewById(R.id.section_detail_illustration);
        question = findViewById(R.id.section_detail_question);
        multChoice = findViewById(R.id.section_detail_multiple_choice);
        scrollView = findViewById(R.id.section_detail_scrollview);

        answer1 = findViewById(R.id.section_detail_answer1);
        answer2 = findViewById(R.id.section_detail_answer2);
        answer3 = findViewById(R.id.section_detail_answer3);
        answer4 = findViewById(R.id.section_detail_answer4);
        submit = findViewById(R.id.question_button);

        /* set listeners */
        submit.setOnClickListener(this);

        /* retrieve section from intent */
        String sectioJsonString = getIntent().getStringExtra(Constants.SECTION_ID_INTENT_KEY);
        Section section = null;
        if (sectioJsonString != null) {
            Gson gson = new Gson();
            section = gson.fromJson(sectioJsonString, Section.class);
        } else {
//            TODO: handle this
        }

        if (section == null) {
            return;
        }

        /* set explanation text */
        if (section.getExplanation() != null) {
            this.explanation.setText(section.getExplanation());
        }

        /* set the image */
        Picasso.with(this)
                .load(Constants.BASE_URL + section.getImageUrl())
                .placeholder(R.drawable.ic_book) // show this image if not loaded yet
                .error(R.drawable.ic_book)      // show this if error or image not exist
                .into(illustration);

        /* set question text */
        if (section.getQuestion() != null
                && section.getWrongAnswer1() != null
                && section.getWrongAnswer2() != null
                && section.getWrongAnswer3() != null
                && section.getCorrectAnswer() != null) {
            setAnswersOrder(section);
            question.setText(section.getQuestion());
        }

        /* initial answer state */
        currentAnswerId = -1;

        /* set radiogroup click listener */
        multChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                currentAnswerId = checkedId;
            }
        });
    }

    /**
     * submit button clicked
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        /* show correct and bad answers */
        if (currentAnswerId == correctAnswerId) {
            System.out.print("good");
            Snackbar.make(scrollView, R.string.section_detail_wrong_answer, Snackbar.LENGTH_SHORT).show();

        } else {
            System.out.print("bad");
            Snackbar.make(scrollView, R.string.section_detail_correct_answer, Snackbar.LENGTH_SHORT).show();

        }
    }

    /* multiple choice question logic */
    private void setAnswersOrder(Section section) {
        Random rand = new Random();
        int correctAnswer = rand.nextInt(3) + 1;
        switch (correctAnswer) {
            case 1: {
                answer1.setText(section.getCorrectAnswer());
                answer2.setText(section.getWrongAnswer2());
                answer3.setText(section.getWrongAnswer3());
                answer4.setText(section.getWrongAnswer1());
                correctAnswerId = R.id.section_detail_answer1;
                break;
            }
            case 2: {
                answer1.setText(section.getWrongAnswer1());
                answer2.setText(section.getCorrectAnswer());
                answer3.setText(section.getWrongAnswer3());
                answer4.setText(section.getWrongAnswer2());
                correctAnswerId = R.id.section_detail_answer2;

                break;
            }
            case 3: {
                answer1.setText(section.getWrongAnswer1());
                answer2.setText(section.getWrongAnswer2());
                answer3.setText(section.getCorrectAnswer());
                answer4.setText(section.getWrongAnswer3());
                correctAnswerId = R.id.section_detail_answer3;

                break;
            }
            case 4: {
                answer1.setText(section.getWrongAnswer1());
                answer2.setText(section.getWrongAnswer2());
                answer3.setText(section.getWrongAnswer3());
                answer4.setText(section.getCorrectAnswer());
                correctAnswerId = R.id.section_detail_answer4;
                break;
            }
        }

    }

    @Override
    public void showExplanation(String explanation) {

    }

    @Override
    public void showIllustration(String imageUrl) {

    }

    @Override
    public void showMultChoice(String question, String answer1, String answer2, String answer3, String answer4) {

    }

    @Override
    public void showSpinner() {

    }

    @Override
    public void hideSpinner() {

    }

    @Override
    public void showLoadError() {

    }

    @Override
    public void hideLoadError() {

    }

    @Override
    public void showNoNetwork() {

    }

    @Override
    public void hideNoNetwork() {

    }

    @Override
    public void showNoContent() {

    }

    @Override
    public void hideNoContent() {

    }

    @Override
    public void checkNetworkState() {

    }
}
