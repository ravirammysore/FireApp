package com.gsss.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebase;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebase = FirebaseDatabase.getInstance();
    }

    public void btnWriteClicked(View v){
        db = firebase.getReference("message");
        db.setValue("Hello");
        Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
    }
    public void btnReadClicked(View v){
        db = firebase.getReference("message");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String msg = dataSnapshot.getValue(String.class);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                String err = databaseError.getMessage();
                Toast.makeText(MainActivity.this, err, Toast.LENGTH_SHORT).show();
            }
        });
    }
}