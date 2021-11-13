package com.example.checkpoint3for5236;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddClassActivity extends AppCompatActivity {

    Button button;
    EditText classEditText;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        button = findViewById(R.id.addButton);
        classEditText = findViewById((R.id.editTextTextClassName));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClass();
            }
        });
    }

    private void addClass(){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Classes");

        String classname = classEditText.getText().toString();

        if (classname.isEmpty()) {
            classEditText.setError("Class name is required");
            classEditText.requestFocus();
            return;
        }

        Class newClass = new Class(classname);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(classname)) {
                    // run some code
                    classEditText.setError("Class name already exists");
                    classEditText.requestFocus();
                    return;
                }else{
                    reference.child(classname).setValue(newClass);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}