package com.su.prodding;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    //00: 00마다 알림을 체크하면 하위 알람중 하나만 선택가능(토스트/알람)
    //각자 지정을 선택하면 다수 선택가능
    Boolean alarmonoff;
    CheckBox checkBox3,checkBox4;

    AlarmManager alarmManager;

    public EditText editText; //제목 및 내용
    public EditText editText2;//현재 시간 출력
    CheckBox checkBox1,checkBox2;


    EditText editText3,editText4,editText5,editText6,editText7;
    TextView textView3;
    // 분~시간 간격 마다 알림
    Handler handler;
    Runnable runnable;


    PendingIntent pendingIntent;
    public static Context mcontext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mcontext=this;

        alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        editText=findViewById(R.id.editText);//제목 및 내용

        editText2=findViewById(R.id.editText2);//현재 날짜,시간
        editText2.setText(d);

        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        editText3=findViewById(R.id.editText3);
        textView3=findViewById(R.id.textView3);
        editText4=findViewById(R.id.editText4);
        editText5=findViewById(R.id.editText5);
        editText6=findViewById(R.id.editText6);
        editText7=findViewById(R.id.editText7);

        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);


        editText6.setEnabled(false);
        editText7.setEnabled(false);
    }

    //체크박스 체크했을 때
    public void Check(View view) {

        if(checkBox1.isChecked()){
            textView3.setVisibility(View.INVISIBLE);
            editText3.setVisibility(View.VISIBLE);
            editText4.setVisibility(View.INVISIBLE);
            editText5.setVisibility(View.INVISIBLE);
            checkBox2.setChecked(false);

            //각자지정- 시간 view 비활성화
            editText4.setEnabled(false);
            editText5.setEnabled(false);
            checkBox3.setEnabled(true);
            checkBox4.setEnabled(true);
            alarmonoff=true;




        }else{
            textView3.setVisibility(View.VISIBLE);
            editText3.setVisibility(View.INVISIBLE);
            editText4.setVisibility(View.VISIBLE);
            editText5.setVisibility(View.VISIBLE);
            checkBox2.setChecked(true);

            //각자지정- 시간 view 활성화
            editText4.setEnabled(true);
            editText5.setEnabled(true);
            checkBox3.setEnabled(false);
            checkBox4.setEnabled(false);
            alarmonoff=false;
        }

    }
    public void Check2(View view) {

      if(checkBox2.isChecked()){
          editText3.setVisibility(View.INVISIBLE);
          editText4.setVisibility(View.VISIBLE);
          editText5.setVisibility(View.VISIBLE);
          textView3.setVisibility(View.VISIBLE);
          checkBox1.setChecked(false);

          //각자지정- 시간 view 활성화
          editText4.setEnabled(true);
          editText5.setEnabled(true);
          checkBox3.setEnabled(false);
          checkBox4.setEnabled(false);
          alarmonoff=false;

      }else{
          editText3.setVisibility(View.VISIBLE);
          editText4.setVisibility(View.INVISIBLE);
          editText5.setVisibility(View.INVISIBLE);
          textView3.setVisibility(View.INVISIBLE);
          checkBox1.setChecked(true);

          //각자지정- 시간 view 비활성화
          editText4.setEnabled(false);
          editText5.setEnabled(false);
          checkBox3.setEnabled(true);
          checkBox4.setEnabled(true);
          alarmonoff=true;
      }

    }





    //현재시간 출력
    long now = System.currentTimeMillis();
    Date date=new Date(now);
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd \n\n HH:mm");
    String d=simpleDateFormat.format(date);



      /*  public void clickBtn(View view) {
        Intent intent=new Intent(this,AlarmActivity.class);

        intent.putExtra("text",editText.getText()); //에디트텍스트값

        PendingIntent pendingIntent=PendingIntent.getActivity(this,10,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        //마시멜로우 버전부터 Doz(낮잠)모드가 생김
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+10000,pendingIntent);
        }else{
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+10000,pendingIntent);
        } //RTC랑 ELAPSED는 동작안함
    }

    public void clickBtn2(View view) {
        //반복알람:10초 후에 처음 알람, 20초마다 반복알람
        Intent intent= new Intent(this,AlarmReceiver.class);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(this,20,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+5000,pendingIntent);
        }else{
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+5000,pendingIntent);
        }
    }
*/
    public void clickBtn3(View view) {
        //반복알람 종료
        //알람매니저에 보류되어있는 PendingIntent를 cancel
        Intent intent=new Intent(this,AlarmActivity.class);
        pendingIntent=PendingIntent.getActivity(this,30,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();
    }






    int Year,Month,Day;
    int Hour,Min;

    public void clickBtn4(View view) {

        //날짜선택 다이얼로그 보이기
        GregorianCalendar calendar=new GregorianCalendar(Locale.KOREA);
        DatePickerDialog dialog= new DatePickerDialog(this,onDateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    //날짜선택 리스너
    DatePickerDialog.OnDateSetListener onDateSetListener= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Toast.makeText(MainActivity.this, year+","+(month+1)+","+day+"", Toast.LENGTH_SHORT).show();


            //선택한 날짜 저장
            Year= year;
            Month= month;
            Day= day;

            //시간 선택 다이얼로그 보이기
            GregorianCalendar calendar= new GregorianCalendar(Locale.KOREA);
            TimePickerDialog dialog= new TimePickerDialog(MainActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY)
                    ,calendar.get(Calendar.MINUTE),false);
            dialog.show();
        }
    };

    //시간 선택 리스너
    TimePickerDialog.OnTimeSetListener timeSetListener= new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            Toast.makeText(MainActivity.this, hour+":"+minute, Toast.LENGTH_SHORT).show();
            Hour=hour;
            Min=minute;

        }
    };

    public int reHour;
    public int reMin;

    //시간,분 간격으로 반복
    public void repeat(View view) {
        final CharSequence[] reMinl={"00","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18"
                , "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40"
                , "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60"};
        final CharSequence[] reHourl={"0","1","2","3","4","5","6","7","8","9","10","11","12"};

        AlertDialog.Builder b2= new AlertDialog.Builder(this);

        b2.setTitle("분을 선택하세요");
        b2.setItems(reMinl, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),reMinl[which]+"분",Toast.LENGTH_SHORT).show();
                reMin=which;
                editText3.setText(reHour+"시간"+"\n"+reMin+"분"+"마다 알림");
            }
        });

        AlertDialog dialog2 = b2.create();
        dialog2.show();

        AlertDialog.Builder b = new AlertDialog.Builder(this);

        b.setTitle("시간을 선택하세요");
                b.setItems(reHourl, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int index){
                        Toast.makeText(getApplicationContext(), reHourl[index]+"시간", Toast.LENGTH_SHORT).show();
                        reHour=index;
                    }
                });
        AlertDialog dialog=b.create();
        dialog.show();



    }



    public String toast; //알람 토스트메시지



    public void AlarmMake(View view) { //확인을 눌렀을 때 알람 생성
        //선택한 날짜와 시간으로 알람 설정
        GregorianCalendar calendar= new GregorianCalendar(Year, Month, Day,Hour,Min);

        //알람시간에 AlarmActivity 실행되도록.
        Intent intent= new Intent(MainActivity.this, AlarmActivity.class);
        intent.putExtra("text",editText.getText()); //제목및 내용
        intent.putExtra("text2",editText6.getText());//메시지 내용

        if(editText7.isEnabled()){ //활성화되있을때
            toast = editText7.getText().toString();//AlarmReceiver 토스트메시지
        }



        //토스트알림
        Intent i= new Intent(this,AlarmReceiver.class);
        pendingIntent= PendingIntent.getBroadcast(this,20,i,PendingIntent.FLAG_UPDATE_CURRENT);



        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        }else{
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        }

     /*   intent.putExtra("Year",calendar.YEAR);
        intent.putExtra("Month",calendar.MONTH);
        intent.putExtra("Month",calendar.DAY_OF_MONTH);
        intent.putExtra("Day",calendar.HOUR_OF_DAY);
        intent.putExtra("Min",calendar.MINUTE);*/
        intent.putExtra("reHour",reHour);
        intent.putExtra("reMin",reMin);
        intent.putExtra("calender",calendar);


        pendingIntent= PendingIntent.getActivity(MainActivity.this,30,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);
        }else{
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        }

        Toast.makeText(this,Year+"년"+Month+"월"+Day+"일"+Hour+"시"+Min+"분", Toast.LENGTH_LONG).show();





        //Main2Activity에 뷰 추가
        Intent AlarmCreate = new Intent(this,Main2Activity.class);
        AC=true;
        AlarmCreate.putExtra("AC",AC);

        startActivity(AlarmCreate);
        Log.e("액티비티 넘어가기","잘돼니");
        AC=false;

    }

    public boolean AC; // true일때 알람 생성하고 다시 false


    //알람시간 각자지정
    public void AlarmTime(View view) {

        GregorianCalendar calendar1= new GregorianCalendar(Locale.KOREA);
        TimePickerDialog dialog= new TimePickerDialog(MainActivity.this,timeSetListener,calendar1.get(Calendar.HOUR_OF_DAY)
                ,calendar1.get(Calendar.MINUTE),false);
        dialog.show();
       editText6.setEnabled(true);

    }

    //시간 선택 리스너
    TimePickerDialog.OnTimeSetListener timeSetListener1= new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            Toast.makeText(MainActivity.this, hour+":"+minute, Toast.LENGTH_SHORT).show();
            Hour=hour;
            Min=minute;

        }
    };

    //토스트메시지 시간 각자지정
    public void ToastTime(View view) {


        editText7.setEnabled(true);
    }

    public void onoff(View view) {
        if (checkBox3.isChecked()){
            editText6.setEnabled(true);
        }else{
            editText6.setEnabled(false);
        }
    }

    public void onoffT(View view) {
        if(checkBox4.isChecked()){
            editText7.setEnabled(true);
        }else{
            editText7.setEnabled(false);
        }
    }
}
