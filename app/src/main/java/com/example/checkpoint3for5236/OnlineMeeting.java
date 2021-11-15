package com.example.checkpoint3for5236;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineMeeting extends AppCompatActivity {

    private static final  String TAG="OnlineMeeting";

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth mAuth;

    String className, title;
    String context;

    private TextView titleText,sendContext,timeView;
    private Button sendBtn,quitBtn;
    private int count=0;

    Handler mHandler;

    RecyclerView rView;

    private ArrayList<MeetingContext> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_meeting);


        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable,5000);

        mAuth=FirebaseAuth.getInstance();

        Bundle bundle = getIntent().getExtras();

        rView=findViewById(R.id.recyclerView2);

        className = bundle.getString("classname");
        title=bundle.getString("title");
        // set title as class name
        titleText=findViewById(R.id.editTextTextPersonName);
        titleText.setText(className+" "+title);

        // timer for meeting
//        timeView=findViewById(R.id.editTextTime);
//        Timer T=new Timer();
//        T.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                timeView.setText(count);
//                count++;
//            }
//        }, 1000, 1000);

        sendBtn=findViewById(R.id.button7);
        sendContext=findViewById(R.id.editTextTextPersonName2);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context=sendContext.getText().toString();
                sendContext();
                Log.i(TAG, context);
            }
        }
        );

        // button to quit meeting
        quitBtn=(Button)findViewById(R.id.button3);
        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //destroy
                finish();
            }
        });

    }

    // send context to database
    private void sendContext(){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Classes").child(className).child("Meetings").child("M: "+className+" meeting, "+title);

        String userName= mAuth.getCurrentUser().getUid();
        Date time= Calendar.getInstance().getTime();

        MeetingContext meetCon=new MeetingContext(userName,context,time);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                    reference.child("Reply by"+userName).setValue(meetCon);
                    // empty the context box once sent
                    sendContext.setText("");
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            // Toast.makeText(OnlineMeeting.this,"in runnable",Toast.LENGTH_SHORT).show();
            OnlineMeeting.this.mHandler.postDelayed(m_Runnable, 5000);
        }

    };//runnable

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(m_Runnable);
        finish();
    }

}