package com.su.prodding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {


    Boolean AC;
    LinearLayout list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        list=findViewById(R.id.list);










        Intent AlarmCreate=getIntent();
        AC= (Boolean) AlarmCreate.getExtras().get("AC");
        Log.e("AC값 잘 넘어왔니",AC.toString());

        if(AC){
            View newview=inflater.inflate(R.layout.inflate,null);
            list.addView(newview);


        }

    }



}
