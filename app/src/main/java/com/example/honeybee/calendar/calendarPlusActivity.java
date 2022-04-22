package com.example.honeybee.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.honeybee.calendar.calendarMainActivity;
import com.example.honeybee.calendar.calendarRequest;
import com.example.honeybee.mainActivity;
import com.example.honeybee.R;
import org.json.JSONException;
import org.json.JSONObject;

public class calendarPlusActivity extends AppCompatActivity {
    private TextView tv_userEmail;
    private EditText et_Year, et_Month, et_Day, et_Hour, et_Minute, et_Plan;
    private Button btn_PlanPlus;
    private ImageView iv_before_plus_calendar;
    calendarMainActivity calendarMainActivity = new calendarMainActivity();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_calendar);
        et_Year = findViewById(R.id.et_Year);
        et_Month = findViewById(R.id.et_Month);
        et_Day = findViewById(R.id.et_Day);
        tv_userEmail = findViewById(R.id.tv_userEmail);
        et_Hour = findViewById(R.id.et_Hour);
        et_Minute = findViewById(R.id.et_Minute);
        et_Plan = findViewById(R.id. et_Plan);
        iv_before_plus_calendar = findViewById(R.id.iv_before_plus_calendar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String userName = bundle.getString("userName");
        String userEmail = bundle.getString("userEmail");
        String moneyNumber = bundle.getString("moneyNumber");
        String familyCode = bundle.getString("familyCode");
        String exp = bundle.getString("exp");
        tv_userEmail.setText(userEmail);
        calendarMainActivity calendarMainActivity = new calendarMainActivity();

        btn_PlanPlus = findViewById(R.id.btn_PlanPlus);
        iv_before_plus_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(calendarPlusActivity.this,calendarMainActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });
        btn_PlanPlus.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view){
                String calYear = et_Year.getText().toString();
                String calMonth = et_Month.getText().toString();
                String calDay =  et_Day.getText().toString();
                String calHour = et_Hour.getText().toString();
                String calMinute = et_Minute.getText().toString();
                String calPlan = et_Plan.getText().toString();
                String userEmail = tv_userEmail.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(),"일정이 추가 되었습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(calendarPlusActivity.this, calendarMainActivity.class);
                                intent.putExtra("calHour",calHour);
                                intent.putExtra("calMinute",calMinute);
                                intent.putExtra("calMonth",calMonth);
                                intent.putExtra("calDay",calDay);
                                intent.putExtra("calPlan",calPlan);
                                intent.putExtra("userEmail",userEmail);
                                intent.putExtra("userName", userName);
                                intent.putExtra("moneyNumber", moneyNumber);
                                intent.putExtra("familyCode", familyCode);
                                intent.putExtra("exp", exp);
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

                calendarRequest calenderRequest = new calendarRequest(calYear,calMonth,calDay,calHour,calMinute,calPlan,userEmail, responseListener);
                RequestQueue queue = Volley.newRequestQueue(calendarPlusActivity.this);
                queue.add(calenderRequest);

            }
        });
    }
}