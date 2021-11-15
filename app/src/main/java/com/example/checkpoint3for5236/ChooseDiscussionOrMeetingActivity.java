package com.example.checkpoint3for5236;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ChooseDiscussionOrMeetingActivity extends AppCompatActivity {

    TextView boardName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_discussion_or_meeting);

        boardName = findViewById(R.id.discussionBoardName);

        Bundle bundle = getIntent().getExtras();

        String className = bundle.getString("classname");

        boardName.setText(className + " Discussion Board");
    }
}