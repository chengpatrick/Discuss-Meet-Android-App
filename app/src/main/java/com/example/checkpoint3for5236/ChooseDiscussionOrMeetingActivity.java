package com.example.checkpoint3for5236;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChooseDiscussionOrMeetingActivity extends AppCompatActivity {

    TextView boardName;
    private Button backButton, joinDisButton, joinMeetButton;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String className;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_discussion_or_meeting);

        mAuth = FirebaseAuth.getInstance();

        boardName = findViewById(R.id.discussionBoardName);

        Bundle bundle = getIntent().getExtras();

        className = bundle.getString("classname");

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

        // join discussion
        joinDisButton=findViewById(R.id.joinDiscussion);
        joinDisButton.setOnClickListener(
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

        // join meeting
        joinMeetButton=findViewById(R.id.joinMeeting);
        joinMeetButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(ChooseDiscussionOrMeetingActivity.this, ChooseMeetActivity.class);
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("classname", className);
                        i.putExtras(bundle2);
                        startActivity(i);
                    }
                }
        );

    }
}