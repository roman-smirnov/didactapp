package com.didactapp.didact.models;

/**
 * domain model
 */
public abstract class SectionModel {

    protected final int sectionId;
    protected final int chapterId;
    protected final int sectionNum;
    protected final String name;
    protected final String explanation;
    protected final String imageUrl;
    protected final String question;
    protected final String wrongAnswer1;
    protected final String wrongAnswer2;
    protected final String wrongAnswer3;
    protected final String correctAnswer;


    public SectionModel(int sectionId, int chapterId, int sectionNum, String name, String explanation, String imageUrl, String question, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, String correctAnswer) {
        this.sectionId = sectionId;
        this.chapterId = chapterId;
        this.sectionNum = sectionNum;
        this.name = name;
        this.explanation = explanation;
        this.imageUrl = imageUrl;
        this.question = question;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        this.correctAnswer = correctAnswer;
    }

    public int getSectionId() {
        return sectionId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public int getSectionNum() {
        return sectionNum;
    }


    public String getName() {
        return name;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getQuestion() {
        return question;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
