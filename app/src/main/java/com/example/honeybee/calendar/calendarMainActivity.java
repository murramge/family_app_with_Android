package com.example.honeybee.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.honeybee.R;
import com.example.honeybee.chat.chatMainActivity;
import com.example.honeybee.mainActivity;
import com.example.honeybee.story.storyMainActivity;


public class calendarMainActivity extends AppCompatActivity {
    private TextView textView,planView,timeView,dayView;
    private CalendarView calendarView;
    private ImageView iv_dateadd;
    private ImageView btn_chat;
    private ImageView btn_story;
    private ImageView btn_calendar;
    private ImageView btn_home;
    private ImageView iv_cal_back_bt;

    final static private String URL = "http://honeybee54953.dothome.co.kr/Date.php";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calendar);

        textView = findViewById(R.id.textView);
        calendarView = findViewById(R.id.calendarView);
        iv_dateadd = findViewById(R.id.iv_dateadd);
        dayView = findViewById(R.id.dayView);
        planView = findViewById(R.id.planView);
        timeView = findViewById(R.id.timeView);
        btn_home = findViewById(R.id.btn_home);
        btn_chat = findViewById(R.id.btn_chat);
        btn_story = findViewById(R.id.btn_story);
        btn_calendar = findViewById(R.id.btn_calendar);
        iv_cal_back_bt = findViewById(R.id.iv_cal_back_bt);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String userName = bundle.getString("userName");
        String userEmail = bundle.getString("userEmail");
        String moneyNumber = bundle.getString("moneyNumber");
        String familyCode = bundle.getString("familyCode");
        String exp = bundle.getString("exp");

        iv_cal_back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(calendarMainActivity.this,mainActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(calendarMainActivity.this, mainActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });

        btn_chat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(calendarMainActivity.this, chatMainActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });
        btn_story.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(calendarMainActivity.this, storyMainActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });


        iv_dateadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(calendarMainActivity.this,calendarPlusActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });

        String calMinute = null;
        String calHour= null;
        String calMonth= null;
        String calDay= null;
        String calPlan= null;

            try {
                calMinute = getIntent().getExtras().getString("calMinute");
                calHour = getIntent().getExtras().getString("calHour");
                calMonth = getIntent().getExtras().getString("calMonth");
                calDay = getIntent().getExtras().getString("calDay");
                calPlan = getIntent().getExtras().getString("calPlan");
            }
            catch (NullPointerException e) {

            }



        String finalCalMonth = calMonth;
        String finalCalDay = calDay;
        String finalCalPlan = calPlan;
        String finalCalHour = calHour;
        String finalCalMinute = calMinute;



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                        month += 1;
                        textView.setText(String.format("%d년%d월%d일", year, month, dayOfMonth));
                        dayView.setText(String.format("%d년%d월%d일", year, month, dayOfMonth));
                        try {
                            if (month == Integer.parseInt(finalCalMonth) && dayOfMonth == Integer.parseInt(finalCalDay)) {
                                timeView.setText(String.format("%s시%s분", finalCalHour, finalCalMinute));
                                planView.setText(String.format("%s", finalCalPlan));
                            } else {
                                timeView.setText(null);
                                planView.setText(null);
                            }
                        }
                        catch (NumberFormatException e){

                        }
                        catch (Exception e){

                        }
                    }

                });




    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(calendarMainActivity.this, mainActivity.class);
        startActivity(intent);
    }
}