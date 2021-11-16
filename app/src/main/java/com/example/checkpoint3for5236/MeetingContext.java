package com.example.checkpoint3for5236;

import java.util.Date;

public class MeetingContext {

    // user name
    String user;

    // context
    String context;

    // time of context sent
    Date time;

    public MeetingContext(){

    }

    public MeetingContext(String user, String context,Date time) {
        this.user=user;
        this.context=context;
        this.time=time;
    }
    public String getUser() {
        return user;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getTime(){
        return time;
    }

    public void setUser(String user){ this.user = user; }

    public void setTime(Date time){ this.time = time; }

}
