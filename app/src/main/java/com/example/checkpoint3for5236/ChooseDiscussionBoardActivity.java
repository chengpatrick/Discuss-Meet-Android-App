package com.example.checkpoint3for5236;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ChooseDiscussionBoardActivity extends AppCompatActivity {

    private static final  String TAG="ChooseDiscussionBoardActivity";

    private TextView userNameText;
    private Button deleteButton, UpdateButton;
    private Button addButton;
    private DatabaseReference mDatabase;
    private EditText ChangeNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_choose_discussion_board);

//        Button course = (Button) findViewById(R.id.button);
        RecyclerView view = (RecyclerView) findViewById((R.id.recyclerView));

//        course.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ChooseDiscussionActivity.this, DiscussionBoardActivity.class));
//            }
//        });

        List<BoardItem> items = new ArrayList<BoardItem>();
        items.add(new BoardItem("CSE 5236"));
        items.add(new BoardItem("CSE 3541"));
        items.add(new BoardItem("CSE 3341"));

        ChangeNameEditText = (EditText) findViewById(R.id.ChangeNameEditText);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BoardAdaptor(getApplicationContext(), items));

        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        userNameText = (TextView) findViewById(R.id.textViewUserName);

        if(user != null){
            String userid = user.getUid();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
            reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = snapshot.getValue(User.class).getName();
                    userNameText.setText(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        deleteButton = (Button) findViewById(R.id.deleteUserButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User account deleted.");
                                    Toast.makeText(ChooseDiscussionBoardActivity.this, "User account deleted", Toast.LENGTH_LONG).show();
                                    String userid = user.getUid();
                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                                    reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            snapshot.getRef().removeValue();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                            }
                        });
            }
        });

        UpdateButton = (Button) findViewById(R.id.UpdateNameBt);

        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String changeName = ChangeNameEditText.getText().toString().trim();
                if (changeName.isEmpty()) {
                    ChangeNameEditText.setError("User name is required");
                    ChangeNameEditText.requestFocus();
                    return;
                }
                mDatabase.child("Users").child(user.getUid()).child("name").setValue(changeName);
                String userid = user.getUid();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = snapshot.getValue(User.class).getName();
                        userNameText.setText(name);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        addButton = (Button) findViewById(R.id.addClassButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseDiscussionBoardActivity.this, AddClassActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();

        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();

        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        Log.i(TAG, "onDestroy");
    }
}