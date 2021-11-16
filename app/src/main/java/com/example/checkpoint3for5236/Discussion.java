package com.example.checkpoint3for5236;

public class Discussion {
    public String title, mainText, userID;

    public Discussion(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Discussion(String title, String mainText, String userID) {
        this.title = title;
        this.mainText = mainText;
        this.userID = userID;
    }

}
