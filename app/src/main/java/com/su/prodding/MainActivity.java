package com.su.prodding;

import android.app.Activity;
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
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    //00: 00마다 알림을 체크하면 하위 알람중 하나만 선택가능(토스트/알람)
    //각자 지정을 선택하면 다수 선택가능
    public int alarmonoff;
    public int vibration=0;
    CheckBox checkBox3,checkBox4;

    AlarmManager alarmManager;

    public EditText editText; //제목 및 내용
    public EditText editText2;//현재 시간 출력
    CheckBox checkBox1,checkBox2;


    EditText editText3,editText4,editText5,editText6,editText7;
    TextView textView3;
    // 분~시간 간격 마다 알림

    //자동 버튼

    PendingIntent pendingIntent, pendingIntent2;
    public static Context mcontext;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mcontext = this;

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        editText = findViewById(R.id.editText);//제목 및 내용

        editText2 = findViewById(R.id.editText2);//현재 날짜,시간







        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        editText3 = findViewById(R.id.editText3);
        textView3 = findViewById(R.id.textView3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);

        checkBox3 = findViewById(R.id.checkBox5);
        checkBox4 = findViewById(R.id.checkBox4);


        editText6.setEnabled(false);
        editText7.setEnabled(false);

        //그냥 처음 셋팅
        checkBox1.setChecked(true);
        checkBox2.setChecked(false);
        editText3.setEnabled(true);
        editText4.setEnabled(false);
        editText5.setEnabled(false);
        checkBox3.setEnabled(true);
        checkBox4.setEnabled(true);
        alarmonoff=1;

        //진동
        final Vibrator vibrator=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        CheckBox vibrate=findViewById(R.id.checkBox);
        vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vibration==0) {
                    vibration = 1;
                    Toast.makeText(MainActivity.this, "진동 활성화", Toast.LENGTH_SHORT).show();
                }else{
                    vibration=0;
                    Toast.makeText(MainActivity.this,"진동 비활성화",Toast.LENGTH_SHORT).show();
                }

            }
        });
        long now = System.currentTimeMillis();

        Date date=new Date(now);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd \n\n HH:mm");
        String d=simpleDateFormat.format(date);

        editText2.setText(d);

        Button random=findViewById(R.id.random);//자동버튼
        random.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                editText3.setEnabled(false);
                editText4.setEnabled(false);
                editText5.setEnabled(false);
                Random rnd=new Random();
                reHour=rnd.nextInt(24);
                reMin=rnd.nextInt(60);
                Hour2=rnd.nextInt(24);
                Hour3=rnd.nextInt(24);
                Min2=rnd.nextInt(60);
                Min3=rnd.nextInt(60);

                editText3.setText(reHour+"시간 "+reMin+"분 마다 알림");
                editText4.setText(Hour2+" : "+Min2);
                editText5.setText(Hour3+" : "+Min3);
                auto=false;
            }
        });

        Button button= findViewById(R.id.button); //지정버튼
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText3.setText("00: 00 마다 알림");
                editText4.setText("00 : 00");
                editText5.setText("00 : 00");
                reHour=0;
                reMin=0;
                Hour2=0;
                Hour3=0;
                Min2=0;
                Min3=0;
                auto=true;


            }
        });
    }


    boolean auto=false;

    //체크박스 체크했을 때
    public void Check(View view) {


            if (checkBox1.isChecked()) {

                checkBox2.setChecked(false);
                if(auto==true) {
                    //각자지정- 시간 view 비활성화
                    editText3.setEnabled(true);
                    editText4.setEnabled(false);
                    editText5.setEnabled(false);
                }
                    checkBox3.setEnabled(true);
                    checkBox4.setEnabled(true);

                alarmonoff = 1;


            } else {

                checkBox2.setChecked(true);
                if(auto==true) {
                    //각자지정- 시간 view 활성화
                    editText3.setEnabled(false);
                    editText4.setEnabled(true);
                    editText5.setEnabled(true);
                }
                    checkBox3.setEnabled(false);
                    checkBox4.setEnabled(false);

                alarmonoff = 0;
            }


    }
    public void Check2(View view) {


            if (checkBox2.isChecked()) {

                checkBox1.setChecked(false);
                if(auto==true) {

                    //각자지정- 시간 view 활성화
                    editText3.setEnabled(false);
                    editText4.setEnabled(true);
                    editText5.setEnabled(true);
                }
                    checkBox3.setEnabled(false);
                    checkBox4.setEnabled(false);
                alarmonoff = 0;

            } else {

                checkBox1.setChecked(true);
                if(auto==true) {
                    //각자지정- 시간 view 비활성화
                    editText3.setEnabled(true);
                    editText4.setEnabled(false);
                    editText5.setEnabled(false);
                }
                    checkBox3.setEnabled(true);
                    checkBox4.setEnabled(true);
                alarmonoff = 1;
            }

    }









    public void clickBtn3(View view) {
        //반복알람 종료
        //알람매니저에 보류되어있는 PendingIntent를 cancel
        Intent intent=new Intent(this,AlarmActivity.class);
        pendingIntent=PendingIntent.getActivity(this,30,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();

        Intent i = new Intent(this, AlarmReceiver.class);
        pendingIntent2 = PendingIntent.getBroadcast(this, 20, i, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent2);
        pendingIntent2.cancel();
    }



    int Year,Month,Day;
    int Hour,Min;


    public void clickBtn4(View view) {


        //날짜선택 다이얼로그 보이기
        GregorianCalendar calendar=new GregorianCalendar(Locale.KOREA);
        DatePickerDialog dialog= new DatePickerDialog(this,onDateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();

        //저장한 시간 출력
        /*long now = calendar.getTimeInMillis();

        Date date=new Date(now);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd \n\n HH:mm");
        String d=simpleDateFormat.format(date);*/

        editText2.setText(Year+"년 "+ Month+"월 "+ Day+"일 \n\n"+Hour+":"+Min);
        editText2.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow( editText2.getWindowToken(), 0);
                    editText2.setText(Year+"년 "+ Month+"월 "+ Day+"일 \n\n"+Hour+"시"+Min+"분");
                    return true;
                }     return false;
            }
        });





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
    public int reHM=(reHour*3600000)+(reMin*60000);

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
    public GregorianCalendar c=new GregorianCalendar(Year,Month,Day, Hour,Min); //알람시간 공유용


    public void AlarmMake(View view) { //확인을 눌렀을 때 알람 생성

        if(Year==0){ Toast.makeText(this,"날짜를 지정하세요",Toast.LENGTH_SHORT).show();}else {


            //알람시간에 AlarmActivity 실행되도록.
            Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
            intent.putExtra("text", editText.getText()); //제목및 내용
            //토스트알림
            Intent i = new Intent(this, AlarmReceiver.class);


            if (editText7.isEnabled()) { //활성화되있을때
                toast = editText7.getText().toString();//AlarmReceiver 토스트메시지

            }
            if (editText6.isEnabled()) {
                intent.putExtra("text2", editText6.getText());//메시지 내용
            }


            if (alarmonoff == 1) { //00:00마다알림
                Log.e("메인 액티비티", "실행");
                //선택한 날짜와 시간으로 알람 설정
                GregorianCalendar calendar = new GregorianCalendar(Year, Month, Day, Hour + reHour, Min + reMin);

                if (checkBox3.isChecked()) {
                    pendingIntent = PendingIntent.getActivity(MainActivity.this, 30, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    } else {
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    }
                }
                if (checkBox4.isChecked()) {
                    intent.putExtra("reHour", reHour);
                    intent.putExtra("reMin", reMin);
                    intent.putExtra("calender", calendar);


                    pendingIntent2 = PendingIntent.getBroadcast(this, 20, i, PendingIntent.FLAG_UPDATE_CURRENT);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent2);
                    } else {
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent2);
                    }
                } else {
                    Toast.makeText(this, "시간을 설정해주세요", Toast.LENGTH_SHORT);
                }

                Toast.makeText(this, Year + "년" + (Month + 1) + "월" + Day + "일" + (Hour + reHour) + "시" + (Min + reMin) + "분", Toast.LENGTH_LONG).show();
            } else {
                Log.e("메인 액티비티", "실행");
                //각자지정을 체크했을 때

                GregorianCalendar calendar1 = new GregorianCalendar(Year, Month, Day, Hour2, Min2);
                GregorianCalendar calendar2 = new GregorianCalendar(Year, Month, Day, Hour3, Min3);

                //메시지알람
                pendingIntent = PendingIntent.getActivity(MainActivity.this, 30, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                //메시지알람
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), pendingIntent);
                } else {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), pendingIntent);
                }
                //토스트알람
                pendingIntent2 = PendingIntent.getBroadcast(this, 20, i, PendingIntent.FLAG_UPDATE_CURRENT);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), pendingIntent2);
                } else {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), pendingIntent2);
                }


                Toast.makeText(this, Year + "년" + Month + "월" + Day + "일" + "메시지알람: " + Hour2 + "시" + Min2 + "분" + "\n토스트알람: " + Hour3 + "시" + Min3 + "분", Toast.LENGTH_LONG).show();
            }
        }


    }



    //알람시간 각자지정
    public void AlarmTime(View view) {

        GregorianCalendar calendar1= new GregorianCalendar(Locale.KOREA);
        TimePickerDialog dialog= new TimePickerDialog(MainActivity.this,timeSetListener2,calendar1.get(Calendar.HOUR_OF_DAY)
                ,calendar1.get(Calendar.MINUTE),false);
        dialog.show();


    }

   public int Hour2,Hour3;
    public int Min2, Min3;


    //시간 선택 리스너
    TimePickerDialog.OnTimeSetListener timeSetListener2= new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            Toast.makeText(MainActivity.this, hour+":"+minute, Toast.LENGTH_SHORT).show();
            Hour2=hour;
            Min2=minute;
            editText4.setText(Hour2+" : "+Min2);

        }
    };

    TimePickerDialog.OnTimeSetListener timeSetListener3= new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            Toast.makeText(MainActivity.this, hour+":"+minute, Toast.LENGTH_SHORT).show();
            Hour3=hour;
            Min3=minute;
            editText5.setText(Hour3+" : "+Min3);

        }
    };

    //토스트메시지 시간 각자지정
    public void ToastTime(View view) {
        GregorianCalendar calendar2= new GregorianCalendar(Locale.KOREA);
        TimePickerDialog dialog= new TimePickerDialog(MainActivity.this,timeSetListener3,calendar2.get(Calendar.HOUR_OF_DAY)
                ,calendar2.get(Calendar.MINUTE),false);
        dialog.show();

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
