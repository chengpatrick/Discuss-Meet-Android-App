package com.example.checkpoint3for5236;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InPersonMeetingActivity extends AppCompatActivity {

    private TextView meetingText, titleText, hostText, locationText;
    private Button findLocation;
    private String className, childName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_person_meeting);

        meetingText = findViewById(R.id.inPersonMeeting);
        titleText = findViewById(R.id.meetingTitle);
        hostText = findViewById(R.id.meetingHost);
        locationText = findViewById(R.id.meetingLocation);

        findLocation = findViewById(R.id.searchLocation);

//        Bundle bundle = getIntent().getExtras();
//        className = bundle.getString("classname");
//        childName = bundle.getString("childname");


        className = "Test";
        childName = "M: Test meeting, Sleep";
        meetingText.setText(className + " In-Person Meeting");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Classes").child(className).child("Meetings").child(childName);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Meeting meeting = snapshot.getValue(Meeting.class);
                titleText.setText("Title: " + meeting.getTitle());
                hostText.setText("Host: " + meeting.getHost());
                locationText.setText(meeting.getLocation());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        findLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InPersonMeetingActivity.this, FragmentTest.class);
                String locationName = locationText.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("location", locationName);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

    }
}