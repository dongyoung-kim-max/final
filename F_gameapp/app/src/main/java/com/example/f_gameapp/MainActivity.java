package com.example.f_gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
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

public class MainActivity extends Activity {
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("");

    Button sign,login;
    EditText loginid,loginpw;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);

        loginid = (EditText)findViewById(R.id.Etid);
        loginpw = (EditText)findViewById(R.id.Etpw);

        sign = (Button)findViewById(R.id.Btsign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent isign = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(isign);
            }
        });

        login = (Button)findViewById(R.id.Btlogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        if(dataSnapshot.child("user").hasChild(loginid.getText().toString()))
                        {

                            if(dataSnapshot.child("user").child(loginid.getText().toString()).child("UserPw").getValue().equals(loginpw.getText().toString()))
                            {
                                Toast.makeText(getApplicationContext(),"두두등장", Toast.LENGTH_LONG).show();
                                Intent mgame = new Intent(getApplicationContext(),MainSensor.class);
                                startActivity(mgame);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"비밀번호가 틀립니다.", Toast.LENGTH_LONG).show();
                            }

                        } else
                        {
                            Toast.makeText(getApplicationContext(),"아이디가 틀림.", Toast.LENGTH_LONG).show();
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