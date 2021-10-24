package com.example.checkpoint3for5236;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final  String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG,"onCreate");

        TextView username=(TextView) findViewById(R.id.editTextTextPersonName3);
        TextView password=(TextView) findViewById(R.id.editTextTextPassword3);
        TextView signUp = (TextView) findViewById(R.id.textView3);

        Button loginbtn=(Button)  findViewById(R.id.signinButton);
        Button fragmenttest=(Button) findViewById(R.id.button);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin")&& password.getText().toString().equals("admin")){
                    //correct password
                    Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, ChooseDiscussionBoardActivity.class));
                }
                else{
                    //incorrect password
                    Toast.makeText(MainActivity.this,"Login Fail, please check username or password",Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        fragmenttest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, FragmentTest.class));
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