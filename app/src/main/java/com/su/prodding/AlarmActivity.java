package com.su.prodding;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity{

    TextView textView,textView2;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        textView = findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);



        getSupportActionBar().setTitle("Alarm Activity");
        Intent intent = getIntent();
        String text = intent.getExtras().get("text").toString();
        textView.setText(text);
        textView2.setText(intent.getExtras().get("text2").toString());

        /*int Year, Month, Day;
        int Hour, Min;
        Year = intent.getExtras().getInt("Year");
        Toast.makeText(AlarmActivity.this, Year, Toast.LENGTH_SHORT);
        Month = intent.getExtras().getInt("Month");
        Day = intent.getExtras().getInt("Day");
        Hour = intent.getExtras().getInt("Hour");
        Min= intent.getExtras().getInt("Min");*/

        int reHour,reMin;
        int reHM;
        reHour = intent.getExtras().getInt("reHour");
        reMin = intent.getExtras().getInt("reMin");
        reHM=(reHour*3600000)+(reMin*60000);

        /*GregorianCalendar calendar = (GregorianCalendar) intent.getExtras().get("calender");
        GregorianCalendar c= new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR)+reHour,
                calendar.get(Calendar.MINUTE)+reMin);*/

        PendingIntent pendingIntent = PendingIntent.getActivity(AlarmActivity.this, 30, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+reHM, pendingIntent);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+reHM, pendingIntent);
        }


    }



    public void close(View view) {
        this.finish();
    }
}
