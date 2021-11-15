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

    // location of meeting
    String location;

    public Meeting(){

    }

    public Meeting(String title, String host, String type,Date time, String location) {
        this.title=title;
        this.host=host;
        this.type =type;
        this.time=time;
        this.location = location;
    }
    public String getTitle() {
        return title;
    }

    public String getHost() {
        return host;
    }

    public String getType() {
        return type;
    }

    public Date getTime() {
        return time;
    }

    public void setTitle(String title){ this.title = title; }

    public void setHost(String host){ this.host = host; }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
