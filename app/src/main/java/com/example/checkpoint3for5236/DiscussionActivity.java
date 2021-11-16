package com.example.checkpoint3for5236;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class DiscussionActivity extends AppCompatActivity {

    private static final  String TAG="DiscussionActivity";
    private TextView currentClass, title, mainText;
    private EditText replyText;
    private String context, titleStr, userName, currentClassStr;
    Button replyBtn, refreshBtn;
    private DatabaseReference mDatabase;
    RecyclerView rView;

    private ArrayList<MeetingContext> items;
    private RecyclerView.LayoutManager mLayoutManager;
    private MeetingContextAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        refreshBtn = (Button)findViewById(R.id.buttonRefresh);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createList();
            }
        });
        rView = findViewById(R.id.recyclerView3);
        Bundle bundle = getIntent().getExtras();
        currentClassStr = bundle.getString("classname");
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
               mDatabase.child("Replies").child("At " + meetCon.time.getTime() + ", Replied by " + userName).setValue(meetCon);
               replyText.setText("");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void createList() {
        DatabaseReference MeetConRef = mDatabase.child("Replies");

        items = new ArrayList<MeetingContext>();

        MeetConRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dsp: snapshot.getChildren()){
                    if(dsp.hasChildren() && !dsp.getKey().equals("time")){
                        MeetingContext currentContext = dsp.getValue(MeetingContext.class);
                        items.add(currentContext);
                    }
                }
                Collections.sort(items, new CustomComparator());
                buildRecyclerView(items);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void buildRecyclerView(ArrayList<MeetingContext> items) {
        rView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MeetingContextAdapter(items);

        rView.setLayoutManager(mLayoutManager);
        rView.setAdapter(mAdapter);
    }


    public class CustomComparator implements Comparator<MeetingContext> {
        @Override
        public int compare(MeetingContext o1, MeetingContext o2) {
            return o1.getTime().compareTo(o2.getTime());
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        createList();
        Log.i(TAG, "onResume");
    }

}