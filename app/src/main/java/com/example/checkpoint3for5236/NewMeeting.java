package com.example.checkpoint3for5236;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.util.Locale;
import java.util.Random;

public class NewMeeting extends AppCompatActivity {

    private TextView titleText, meetTitle;
    private Button backButton,createButton;
    private RadioGroup rGroup;
    private RadioButton rButton;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String className, title;

    private FirebaseAuth mAuth;

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

        int radioId= rGroup.getCheckedRadioButtonId();
        rButton=findViewById((radioId));
        String type=rButton.getText().toString();

        Meeting newMeeting= new Meeting(title,host,type);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.hasChild("M: " + className + " meeting, " + title)){
                    meetTitle.setError("Title already existed");
                    meetTitle.requestFocus();
                    return;
                }else {
                    reference.child("M: " + className + " meeting, " + title).child("type").setValue(type);
                    reference.child("M: " + className + " meeting, " + title).child("userID").setValue(host);
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