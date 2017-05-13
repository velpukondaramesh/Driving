package com.ram.drivingtest.Model;

/**
 * Created by Ramesh on 11-05-2017.
 */

public class QuestionModel {

    String question, url;

    public QuestionModel() {
    }

    public QuestionModel(String question, String url) {
        this.question = question;
        this.url = url;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
