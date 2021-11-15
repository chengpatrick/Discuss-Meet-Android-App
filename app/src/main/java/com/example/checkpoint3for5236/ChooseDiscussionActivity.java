package com.example.checkpoint3for5236;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ChooseDiscussionActivity extends AppCompatActivity {

    private static final  String TAG="ChooseDiscussionActivity";
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_discussion);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<Item> items = new ArrayList<Item>();
//        items.add(new Item("About HW1","I have a question about the first pro..."));
//        items.add(new Item("About HW2","I have a question about the first pro..."));
//        items.add(new Item("About HW3","I have a question about the first pro..."));
//        items.add(new Item("About HW4","I have a question about the first pro..."));
//        items.add(new Item("About HW5","I have a question about the first pro..."));
//        items.add(new Item("About HW6","I have a question about the first pro..."));
//        items.add(new Item("About HW1","I have a question about the first pro..."));
//        items.add(new Item("About HW2","I have a question about the first pro..."));
//        items.add(new Item("About HW3","I have a question about the first pro..."));
//        items.add(new Item("About HW4","I have a question about the first pro..."));
//        items.add(new Item("About HW5","I have a question about the first pro..."));
//        items.add(new Item("About HW6","I have a question about the first pro..."));


        recyclerView.setLayoutManager(new LinearLayoutManager(this
        ));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));

        // back button onclick
        backButton=(Button)findViewById(R.id.BackToDisBdBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              // go back to last screen
                                              finish();
                                          }
                                      }
        );
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