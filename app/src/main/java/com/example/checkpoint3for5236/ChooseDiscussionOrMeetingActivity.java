package com.example.checkpoint3for5236;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooseDiscussionOrMeetingActivity extends AppCompatActivity {

    TextView boardName;
    private Button backButton;
    private Button joinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_discussion_or_meeting);

        boardName = findViewById(R.id.discussionBoardName);

        Bundle bundle = getIntent().getExtras();

        String className = bundle.getString("classname");

        boardName.setText(className + " Discussion Board");

        // button to return to choose discussion board
        backButton=(Button)findViewById(R.id.BackToDisBdBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //destroy
                finish();
            }
        }
        );

        joinButton=findViewById(R.id.joinDiscussion);
        joinButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(ChooseDiscussionOrMeetingActivity.this, ChooseDiscussionActivity.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("classname", className);
                        i.putExtras(bundle1);
                        startActivity(i);
                    }
                }
        );

    }
}