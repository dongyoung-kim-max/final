package com.example.f_gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Hashtable;

public class SignUpActivity extends Activity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("");

    Button signin;
    EditText signid;
    EditText signpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signin = (Button)findViewById(R.id.Btsignin);
        signid = (EditText)findViewById(R.id.Etsignid);
        signpw = (EditText)findViewById(R.id.Etsignpw);



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Read from the database
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        if(dataSnapshot.child("user").hasChild(signid.getText().toString()))
                            {
                                Toast.makeText(getApplicationContext(),"중복된 아이디임", Toast.LENGTH_LONG).show();
                        } else {
                            Hashtable<String,String> members
                                    = new Hashtable<>();
                            members.put("UserId", signid.getText().toString());
                            members.put("UserPw", signpw.getText().toString());

                            myRef.child("user").child(signid.getText().toString()).setValue(members);

                            Toast.makeText(getApplicationContext(),"가입완료", Toast.LENGTH_LONG).show();

                            finish();

                        }




                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                    }
                });











            }
        });


    }
}