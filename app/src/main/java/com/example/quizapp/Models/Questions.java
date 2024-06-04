package com.example.quizapp.Models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Questions implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("que")
    @Expose
    private String que;
    @SerializedName("optionA")
    @Expose
    private String optionA;
    @SerializedName("optionB")
    @Expose
    private String optionB;
    @SerializedName("optionC")
    @Expose
    private String optionC;
    @SerializedName("optionD")
    @Expose
    private String optionD;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}