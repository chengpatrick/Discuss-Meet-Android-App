package com.example.checkpoint3for5236;

import java.util.Date;

public class Meeting {

    // title of meeting
    String title;

    // host of meeting
    String host;

    // meeting type, inperson and online
    String type;

    // time of meeting
    Date time;

    public Meeting(){

    }

    public Meeting(String title, String host, String type,Date time) {
        this.title=title;
        this.host=host;
        this.type =type;
        this.time=time;
    }
    public String getMeetingTitle() {
        return title;
    }

    public String getMeetingHost() {
        return host;
    }

    public String getMeetingType() {
        return type;
    }

    public Date getMeetingTime() {
        return time;
    }

    public void setTitle(String title){ this.title = title; }

    public void setHost(String host){ this.host = host; }

    public void setType(String type) {
        this.type = type;
    }
}
