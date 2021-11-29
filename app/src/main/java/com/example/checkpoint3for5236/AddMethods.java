package com.example.checkpoint3for5236;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddMethods {

    public Class addClass(String classname){

        if (classname.isEmpty()) {
            return null;
        }

        Class newClass = new Class(classname);

        return newClass;
    }

    public Discussion addDiscussion(String title, String mainText, String userID){
        if (title.isEmpty()) {
            return null;
        }

        Discussion newDiscussion = new Discussion(title, mainText, userID);
        return  newDiscussion;
    }
}
