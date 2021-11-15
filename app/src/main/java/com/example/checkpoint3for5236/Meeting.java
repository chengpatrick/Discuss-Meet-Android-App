package com.example.checkpoint3for5236;

public class Meeting {

    // title of meeting
    String title;

    // host of meeting
    String host;

    // meeting type, inperson and online
    String type;

    public Meeting(){

    }

    public Meeting(String title, String host, String type) {
        this.title=title;
        this.host=host;
        this.type =type;
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

    public void setTitle(String title){ this.title = title; }

    public void setHost(String host){ this.host = host; }

    public void setType(String type) {
        this.type = type;
    }
}
