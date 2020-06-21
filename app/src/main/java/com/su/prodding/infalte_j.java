package com.su.prodding;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class infalte_j extends AppCompatActivity {

    TextView t,t2;
    String text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inflate);


        t=findViewById(R.id.textView6);
        t2=findViewById(R.id.textView7);

        t.setText(((MainActivity)MainActivity.mcontext).editText.getText().toString()); //제목
        t2.setText(((MainActivity)MainActivity.mcontext).editText2.getText().toString()); //날짜

    }
}
