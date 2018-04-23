package com.didactapp.didact.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.didactapp.didact.R;
import com.didactapp.didact.contracts.SectionDetailContract;
import com.didactapp.didact.entities.Section;
import com.google.gson.Gson;

import static com.didactapp.didact.utils.Constants.SECTION_ID_INTENT_KEY;

public class SectionDetailActivity extends BaseActivity implements SectionDetailContract.View {

    /* view refs */
    private TextView explanation;
    private ImageView illustration;
    private TextView question;
    private RadioGroup multChoice;
    private RadioButton answer1;
    private RadioButton answer2;
    private RadioButton answer3;
    private RadioButton answer4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_detail);

        /* get layout view references */
        explanation = findViewById(R.id.section_detail_explanation);
        illustration = findViewById(R.id.section_detail_illustration);
        question = findViewById(R.id.section_detail_question);
        multChoice = findViewById(R.id.section_detail_multiple_choice);
        answer1 = findViewById(R.id.section_detail_answer1);
        answer2 = findViewById(R.id.section_detail_answer2);
        answer3 = findViewById(R.id.section_detail_answer3);
        answer4 = findViewById(R.id.section_detail_answer4);

        /* getFromRemote section from intent */
        String sectioJsonString = getIntent().getStringExtra(SECTION_ID_INTENT_KEY);
        Section section = null;
        if (sectioJsonString != null) {
            Gson gson = new Gson();
            section = gson.fromJson(sectioJsonString, Section.class);
        } else {
//            TODO: handle this case
        }

        if (section == null) {
            return;
        }

        if (section.getExplanation() != null) {
            this.explanation.setText(section.getExplanation());
        }

        illustration.setImageDrawable(getDrawable(R.drawable.ic_book_logo));

        if (section.getQuestion() != null
                && section.getWrongAnswer1() != null
                && section.getWrongAnswer2() != null
                && section.getWrongAnswer3() != null
                && section.getCorrectAnswer() != null) {

            question.setText(section.getQuestion());
            answer1.setText(section.getWrongAnswer1());
            answer2.setText(section.getWrongAnswer2());
            answer3.setText(section.getWrongAnswer3());
            answer4.setText(section.getCorrectAnswer());
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
