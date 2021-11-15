package com.example.checkpoint3for5236;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooseDiscussionOrMeetingActivity extends AppCompatActivity {

    TextView boardName;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_discussion_or_meeting);

        boardName = findViewById(R.id.discussionBoardName);

        Bundle bundle = getIntent().getExtras();

        String className = bundle.getString("classname");

        boardName.setText(className + " Discussion Board");

        // back button onclick
        backButton=(Button)findViewById(R.id.BackToDisBdBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // go back to last screen
                    finish();
                }
            }
        );
    }
}