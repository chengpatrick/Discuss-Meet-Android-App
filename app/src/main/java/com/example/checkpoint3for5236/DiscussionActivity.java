package com.example.checkpoint3for5236;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class DiscussionActivity extends AppCompatActivity {

    private static final  String TAG="DiscussionActivity";
    private TextView currentClass, title, mainText;
    private EditText replyText;
    private String context, titleStr, userName;
    Button replyBtn;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        Bundle bundle = getIntent().getExtras();
        String currentClassStr = bundle.getString("classname");
        titleStr = bundle.getString("title");
/*        String mainTextStr = bundle.getString("mainText");*/
        mDatabase = FirebaseDatabase.getInstance().getReference("Classes").child(currentClassStr).child("Discussions").child("D: " + titleStr);
        currentClass = findViewById(R.id.textViewCurrentClass);
        title = findViewById(R.id.textViewDisTitle);
        mainText = findViewById(R.id.textViewDisMainText);
        replyBtn = (Button)findViewById(R.id.buttonReply);
        replyText = findViewById(R.id.editTextDisReply);
        currentClass.setText(currentClassStr);
        title.setText(titleStr);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Discussion thisDis = dataSnapshot.getValue(Discussion.class);

                mainText.setText(thisDis.getMainText());
                Log.i(TAG, "mainText: " + thisDis.getMainText());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.i(TAG, "loadMainText:onCancelled");
            }
        };
        mDatabase.addValueEventListener(postListener);
        replyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = replyText.getText().toString();
                addReply();
            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            String userid = user.getUid();
            Log.i(TAG, "user is: " + userid);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
            reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    userName = snapshot.getValue(User.class).getName();
                    Log.i(TAG, "userName is: " + userName);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void addReply() {

        Date time = Calendar.getInstance().getTime();

        MeetingContext meetCon = new MeetingContext(userName, context, time);

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               mDatabase.child("Replies").child("Reply by " + userName).setValue(meetCon);
               replyText.setText("");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}