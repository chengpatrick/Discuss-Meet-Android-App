package com.example.checkpoint3for5236;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class ChooseMeetActivity extends AppCompatActivity {

    private static final  String TAG="ChooseMeetActivity";
    private Button backButton,newMeetButton;
    private TextView titleText;
    private ArrayList<Meeting> items;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MeetingAdapter mAdapter;
    private String className;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_meet);

        Log.i(TAG, "onCreate");

        Bundle bundle = getIntent().getExtras();

        className = bundle.getString("classname");

        titleText=findViewById(R.id.textView13);
        titleText.setText(className.toUpperCase(Locale.ROOT)+" Meetings");

        recyclerView = findViewById(R.id.recyclerview99);


//        recyclerView.setLayoutManager(new LinearLayoutManager(this
//        ));
//        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));

        // button to return to choose discussion board
        backButton=(Button)findViewById(R.id.backToDisBdBtn2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //destroy
                finish();
            }
        }
        );

        //button to start new meeting
        newMeetButton=findViewById(R.id.startNewDisscusBtn2);
        newMeetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChooseMeetActivity.this, NewMeeting.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("classname", className);
                i.putExtras(bundle1);
                startActivity(i);
            }
        }
        );

    }

    private void createList() {
        Bundle bundle = getIntent().getExtras();

        className = bundle.getString("classname");
        DatabaseReference MeetingRef = FirebaseDatabase.getInstance().getReference("Classes").child(className).child("Meetings");

        items = new ArrayList<>();

        MeetingRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dsp: snapshot.getChildren()){
                    Meeting currentMeeting = dsp.getValue(Meeting.class);
//                    String className = currentClass.getClassname();
                    items.add(currentMeeting);
//                    Log.i(TAG, className);
//                    Log.i(TAG, "Items: " + items.toString());
                }
                buildRecyclerView(items);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void buildRecyclerView(ArrayList<Meeting> items) {
        recyclerView = (RecyclerView) findViewById((R.id.recyclerview99));
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MeetingAdapter(items);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MeetingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                enterMeeting(position);
                // Pass a value to next activity
                if(items.get(position).getType().equals("Online")) {
                    Intent i = new Intent(ChooseMeetActivity.this, OnlineMeeting.class);
                    String MeetingTitle = items.get(position).getTitle();
                    Bundle bundle = new Bundle();
                    bundle.putString("classname", className);
                    bundle.putString("title", MeetingTitle);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            }
        });
    }

    private void enterMeeting(int position) {

        Log.i(TAG, "Position is "+position+", Meeting title is: "+items.get(position).getTitle());

    }
    @Override
    protected void onStart() {
        super.onStart();

        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        createList();
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