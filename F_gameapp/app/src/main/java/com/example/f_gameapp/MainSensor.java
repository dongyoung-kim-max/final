package com.example.f_gameapp;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;


public class MainSensor extends Activity {

    ImageView[] img_array = new ImageView[9];

    int[] image = {R.id.image1,R.id.image2,R.id.image3,R.id.image4,R.id.image5,R.id.image6,R.id.image7,
    R.id.image8,R.id.image9};

    int boom = new Random().nextInt(9)+1;

    final String TAG_ON = "on";
    final String TAG_OFF = "off";
    Button restart;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maingame);

        restart = (Button)findViewById(R.id.reset);


        Log.e("haha",Integer.toString(boom));

        for(int i = 0; i<img_array.length; i++)
        {
            img_array[i] = (ImageView)findViewById(image[i]);
            img_array[i].setTag(TAG_OFF);
            if(i == (boom-1))
            {
                img_array[i].setTag(TAG_ON);

            }

            img_array[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((ImageView)v).getTag().toString().equals(TAG_ON)){
                        Toast.makeText(getApplicationContext(),"당첨",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        ((ImageView)v).setImageResource(R.drawable.game2);
                    }
                }
            });

        }

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });



    }


}
