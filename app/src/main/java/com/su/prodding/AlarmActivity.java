package com.su.prodding;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {

    TextView textView, textView2;
    AlarmManager alarmManager;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        getSupportActionBar().setTitle("Alarm Activity");
        Intent intent = getIntent();
        String text = intent.getExtras().get("text").toString();
        textView.setText(text);
        textView2.setText(intent.getExtras().get("text2").toString());

        if (((MainActivity) MainActivity.mcontext).vibration == 1) {
            Log.e("진동실행", "정상");
            vibrator.vibrate(
                    new long[]{100, 1000, 100, 500, 100, 500, 100, 1000}, 0

            );
        }


            if (((MainActivity) MainActivity.mcontext).alarmonoff == 1) { //00:00마다 알림체크일때
                Log.e("00:00마다 체크", "실행2");
                int reHour, reMin;
                int reHM;
                reHour = ((MainActivity) MainActivity.mcontext).reHour;
                reMin = ((MainActivity) MainActivity.mcontext).reMin;

                reHM = (reHour * 3600000) + (reMin * 60000);


                PendingIntent pendingIntent = PendingIntent.getActivity(AlarmActivity.this, 30, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + reHM, pendingIntent);
                } else {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + reHM, pendingIntent);
                }

            } else { //각자알림 체크일때
                Log.e("각자알림 체크", "실행2");
                PendingIntent pendingIntent = PendingIntent.getActivity(AlarmActivity.this, 30, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (3600000 * 24), pendingIntent);
                } else {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (3600000 * 24), pendingIntent);
                }


            }
        }




    public void close(View view) {
        this.finish();
        vibrator.cancel();
        Log.e("진동실행", "끝");
    }
}
