package com.example.checkpoint3for5236;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class NewMeeting extends AppCompatActivity {

    private TextView titleText, meetTitle;
    private Button backButton,createButton;
    private RadioGroup rGroup;
    private RadioButton rButton, rb2, rb3;
    private EditText locationText;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String className, title;

    private FirebaseAuth mAuth;
    private Date time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meeting);

        mAuth=FirebaseAuth.getInstance();

        Bundle bundle = getIntent().getExtras();

        className = bundle.getString("classname");

        meetTitle=findViewById(R.id.editTextTextMeetTitle);

        titleText=findViewById(R.id.textView10);
        titleText.setText("New Meeting for "+className.toUpperCase(Locale.ROOT));

        // button to return to choose discussion board
        backButton=(Button)findViewById(R.id.backBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //destroy
                finish();
            }
        }
        );

        //radio group and button
        rGroup=findViewById(R.id.radiogroup);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        locationText = findViewById(R.id.editLocation);
        locationText.setVisibility(View.INVISIBLE);
        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    locationText.setVisibility(View.VISIBLE);
                }
            }
        });
        rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    locationText.setVisibility(View.INVISIBLE);
                    locationText.setText("");
                }
            }
        });

        createButton=findViewById(R.id.button6);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId= rGroup.getCheckedRadioButtonId();

                rButton=findViewById((radioId));
                addMeeting();
                Log.i("NewMeetingActivity", "Launched "+rButton.getText()+" meeting");
            }
        }
        );
    }

    public void checkButton(View v){
        int radioId= rGroup.getCheckedRadioButtonId();

        rButton=findViewById((radioId));

        Toast.makeText(this,"Selected: "+rButton.getText(),Toast.LENGTH_SHORT).show();
    }

    private void addMeeting(){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Classes").child(className).child("Meetings");

        // count number of meetings in certain class

        String host= mAuth.getCurrentUser().getUid();

        title=meetTitle.getText().toString();
        time= Calendar.getInstance().getTime();

        int radioId= rGroup.getCheckedRadioButtonId();
        rButton=findViewById((radioId));
        String type=rButton.getText().toString();

        String location = locationText.getText().toString();
        if(location.equals("") && type.equals("In-Person")){
            Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT);
        }

        Meeting newMeeting= new Meeting(title,host,type,time, location);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.hasChild("M: " + className + " meeting, " + title)){
                    meetTitle.setError("Title already existed");
                    meetTitle.requestFocus();
                    return;
                }else {
//                    reference.child("M: " + className + " meeting, " + title).child("type").setValue(type);
//                    reference.child("M: " + className + " meeting, " + title).child("title").setValue(title);
//                    reference.child("M: " + className + " meeting, " + title).child("userID").setValue(host);
//                    reference.child("M: " + className + " meeting, " + title).child("time").setValue(time);
                    reference.child("M: " + className + " meeting, " + title).setValue(newMeeting);
                    finish();
                    startActivity(new Intent(NewMeeting.this, ChooseMeetActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}