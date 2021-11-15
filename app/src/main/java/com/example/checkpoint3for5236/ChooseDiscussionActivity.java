package com.example.checkpoint3for5236;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChooseDiscussionActivity extends AppCompatActivity {

    private static final  String TAG="ChooseDiscussionActivity";
    private Button backButton;
    private TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_discussion);

        Bundle bundle = getIntent().getExtras();

        String className = bundle.getString("classname");

        titleText=findViewById(R.id.textView4);
        titleText.setText(className.toUpperCase(Locale.ROOT)+" Discussions");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<Item> items = new ArrayList<Item>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this
        ));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));

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