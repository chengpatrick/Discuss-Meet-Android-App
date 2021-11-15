package com.example.checkpoint3for5236;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChooseMeetActivity extends AppCompatActivity {

    private static final  String TAG="ChooseMeetingActivity";
    private Button backButton,newMeetButton;
    private TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_meet);

        Log.i(TAG, "onCreate");

        Bundle bundle = getIntent().getExtras();

        String className = bundle.getString("classname");

        titleText=findViewById(R.id.textView13);
        titleText.setText(className.toUpperCase(Locale.ROOT)+" Meetings");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<Item> items = new ArrayList<Item>();

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
}