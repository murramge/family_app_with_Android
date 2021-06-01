package com.example.honeybee.calendar;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.honeybee.Fragment_info;
import com.example.honeybee.Fragment_main;
import com.example.honeybee.LoginActivity;
import com.example.honeybee.MainActivity;
import com.example.honeybee.R;
import com.example.honeybee.RegisterActivity;
import com.example.honeybee.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class CalenderActivity extends AppCompatActivity {
    private EditText et_Year, et_Month, et_Day, et_Hour, et_Minute, et_Plan;
    private Button btn_PlanPlus;
    Fragment_calender fragment_calender;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        et_Year = findViewById(R.id.et_Year);
        et_Month = findViewById(R.id.et_Month);
        et_Day = findViewById(R.id.et_Day);
        et_Hour = findViewById(R.id.et_Hour);
        et_Minute = findViewById(R.id.et_Minute);
        et_Plan = findViewById(R.id. et_Plan);
        fragment_calender = new Fragment_calender();

        btn_PlanPlus = findViewById(R.id.btn_PlanPlus);
        btn_PlanPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                int calYear = Integer.parseInt(et_Year.getText().toString());
                int calMonth = Integer.parseInt(et_Month.getText().toString());
                int calDay =  Integer.parseInt(et_Day.getText().toString());
                int calHour = Integer.parseInt(et_Hour.getText().toString());
                int calMinute = Integer.parseInt(et_Minute.getText().toString());
                String calPlan = et_Plan.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(),"일정이 추가 되었습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CalenderActivity.this, MainActivity.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getApplicationContext(),"일정 추가에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                CalenderRequest calenderRequest = new CalenderRequest(calYear,calMonth,calDay,calHour,calMinute,calPlan, responseListener);
                RequestQueue queue = Volley.newRequestQueue(CalenderActivity.this);
                queue.add(calenderRequest);
            }
        });
    }

    }


