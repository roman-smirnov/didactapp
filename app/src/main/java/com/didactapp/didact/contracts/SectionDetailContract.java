package com.didactapp.didact.contracts;

import com.didactapp.didact.entities.Section;

import java.util.List;


public interface SectionDetailContract {

    interface View extends BaseView<Presenter> {

        void showExplanation(String explanation);

        void showIllustration(String imageUrl);

        void showMultChoice(String question,
                            String answer1,
                            String answer2,
                            String answer3,
                            String answer4);


    }

    interface Presenter extends BasePresenter<View> {

        void answerChosen(int choice);

        void loadContent();

    }

}
