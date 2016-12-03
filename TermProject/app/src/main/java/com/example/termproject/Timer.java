package com.example.termproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;

/**
 * Created by 박도희 on 2016-11-29.
 */

public class Timer {
    private int category; //분류
    private String name; //타이머 이름
    private int id; //db에 저장될 id. delete되어도 숫자는 그대로 늘어남. idx는 숫자가 줄어듦!

    public Timer(int category, int id, String name) {
        this.category = category;
        this.id = id;
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    Chronometer mChrono;

}







