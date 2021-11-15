package com.example.checkpoint3for5236;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OnlineMeeting extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth mAuth;

    String className, title;

    private TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_meeting);

        mAuth=FirebaseAuth.getInstance();

        Bundle bundle = getIntent().getExtras();

        className = bundle.getString("classname");
        // set title as class name
        titleText=findViewById(R.id.editTextTextPersonName);
        titleText.setText(className);
    }
}