package com.su.prodding;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    String text;

    private static final String TAG = "시간값";

    @Override
    public void onReceive(Context context, Intent i) {

        text=((MainActivity)MainActivity.mcontext).toast.toString();

            Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
            //토스트 메시지 알람




        //반복알람을 위해 알람이 울리면 다시 새로운 알람을 설정
        AlarmManager alarmManager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent p=PendingIntent.getBroadcast(context,20,i,PendingIntent.FLAG_UPDATE_CURRENT);

        int reHour,reMin,reHM;
        reHour=((MainActivity) MainActivity.mcontext).reHour;
        reMin=((MainActivity) MainActivity.mcontext).reMin;
        reHM=(reHour*3600000)+(reMin*60000);


        Log.e(TAG, String.valueOf(reHM));

        //알람설정(20초후)
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+reHM,p);
        }else{
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+reHM,p);
        }
    }
}

