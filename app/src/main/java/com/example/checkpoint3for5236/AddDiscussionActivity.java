package com.example.checkpoint3for5236;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddDiscussionActivity extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String currentClassName;
    EditText titleText, mainText;
    Button postButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discussion);
        mAuth = FirebaseAuth.getInstance();

        Bundle bundle = getIntent().getExtras();

        currentClassName = bundle.getString("classname");
        titleText = findViewById((R.id.editTextTitle));
        mainText = findViewById((R.id.editTextText));
        postButton = findViewById((R.id.buttonAddDiscussion));
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDiscussion();
            }
        });
    }

    private void addDiscussion(){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Classes").child(currentClassName).child("Discussions");

        String title = titleText.getText().toString();
        String mainTexts = mainText.getText().toString();
        String userID = mAuth.getCurrentUser().getUid();

        if (title.isEmpty()) {
            titleText.setError("Title of the post should not be empty!");
            titleText.requestFocus();
            return;
        }

        Discussion newDiscussion = new Discussion(title, mainTexts, userID);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(title)) {
                    // run some code
                    titleText.setError("Title already existed");
                    titleText.requestFocus();
                    return;
                }else{
                    reference.child("D: " + title).setValue(newDiscussion);
                    finish();
                    startActivity(new Intent(AddDiscussionActivity.this, ChooseDiscussionActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}