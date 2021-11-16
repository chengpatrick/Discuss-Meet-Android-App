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
import java.util.List;
import java.util.Locale;

public class ChooseDiscussionActivity extends AppCompatActivity {

    private static final  String TAG="ChooseDiscussionActivity";
    private Button backButton, addDisscussion;
    private TextView titleText;
    private ArrayList<Discussion> items;
    private RecyclerView view;
    private RecyclerView.LayoutManager mLayoutManager;
    private DiscussionAdapter mAdapter;
    private String className;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_discussion);
        Bundle bundle = getIntent().getExtras();

        className = bundle.getString("classname");

        titleText=findViewById(R.id.textView4);
        titleText.setText(className.toUpperCase(Locale.ROOT)+" Discussions");
        addDisscussion = findViewById(R.id.StartNewDisscusBtn);

        addDisscussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChooseDiscussionActivity.this, AddDiscussionActivity.class);
                bundle.putString("classname", className);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.DiscussionRecycler);



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

    }

    private void createList() {
        Bundle bundle = getIntent().getExtras();

        String className = bundle.getString("classname");
        DatabaseReference DiscussionRef = FirebaseDatabase.getInstance().getReference("Classes").child(className).child("Discussions");

        items = new ArrayList<>();

        DiscussionRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dsp: snapshot.getChildren()){
                    Discussion currentDiscussion = dsp.getValue(Discussion.class);
//                    String className = currentClass.getClassname();
                    items.add(currentDiscussion);
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

    private void buildRecyclerView(ArrayList<Discussion> items) {
        view = (RecyclerView) findViewById((R.id.DiscussionRecycler));
        view.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new DiscussionAdapter(items);

        view.setLayoutManager(mLayoutManager);
        view.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new DiscussionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                enterDiscussion(position);
                // Pass a value to next activity
                Intent i = new Intent(ChooseDiscussionActivity.this, DiscussionActivity.class);
                String DiscussionTitle = items.get(position).getTitle();
                i.putExtra("classname", className);
                i.putExtra("title", DiscussionTitle);
                startActivity(i);
            }
        });
    }
    private void enterDiscussion(int position) {

        Log.i(TAG, "Position is "+position+", Discussion title is: "+items.get(position).getTitle());

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