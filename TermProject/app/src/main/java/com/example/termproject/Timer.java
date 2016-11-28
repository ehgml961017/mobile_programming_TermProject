package com.example.termproject;

/**
 * Created by 박도희 on 2016-11-29.
 */

public class Timer {
    private int category; //분류
    private String name; //타이머 이름
    private int startTime; //타이머가 시작된 시간
    private int endTime; //타이머가 끝난 시간

    public Timer(int category, String name)
    {
        this.category = category;
        this.name = name;
        //현재시간도 받아오기!
        int time;
        setStartTime(time);
    }
    public Timer() {}

    public int getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getLeadTime()
    {
        return endTime-startTime;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
