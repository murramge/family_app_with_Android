package com.example.honeybee;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.honeybee.R;
import com.example.honeybee.calendar.calendarMainActivity;
import com.example.honeybee.chat.chatMainActivity;
import com.example.honeybee.money.moneyActivity;
import com.example.honeybee.myinfo.myinfoActivity;
import com.example.honeybee.quest.questActivity;
import com.example.honeybee.quest.questItem;
import com.example.honeybee.quest.questsActivity;
import com.example.honeybee.story.storyMainActivity;
import com.example.honeybee.locationMainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class mainActivity extends AppCompatActivity {
    private ImageView btn_chat;
    private ImageView btn_story;
    private ImageView btn_calendar;
    private ImageView btn_info;
    private long backKeyPressedTime = 0;
    private Toast toast;
    private ImageView iv_location;
    private TextView qs_1, qs_2, qs_3, tv_ex;
    private ImageView iv_questlist, iv_bee;

    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_info = findViewById(R.id.btn_info);
        btn_chat = findViewById(R.id.btn_chat);
        btn_story = findViewById(R.id.btn_story);
        tv_ex = findViewById(R.id.tv_ex);
        iv_bee = findViewById(R.id.iv_bee);
        btn_calendar = findViewById(R.id.btn_calendar);
        iv_location = findViewById(R.id.iv_location);
        qs_1 = findViewById(R.id.qs_1);

        iv_questlist = findViewById(R.id.iv_questlist);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String userName = bundle.getString("userName");
        String userEmail = bundle.getString("userEmail");
        String moneyNumber = bundle.getString("moneyNumber");
        String familyCode = bundle.getString("familyCode");
        String exp = bundle.getString("exp");

        tv_ex.setText(exp);
        System.out.println(exp);

        new BackgroundTask().execute(familyCode);
        if (Integer.parseInt(exp) <= 2) {
            iv_bee.setImageResource(R.drawable.bee01);
        } else if (Integer.parseInt(exp) <= 4) {
            iv_bee.setImageResource(R.drawable.bee02);
        } else if (Integer.parseInt(exp) >= 6) {
            iv_bee.setImageResource(R.drawable.bee03);
        }

        btn_chat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity.this, chatMainActivity.class);
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
                Intent intent = new Intent(mainActivity.this, storyMainActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });

        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity.this, myinfoActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });


        btn_calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity.this, calendarMainActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });
        iv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity.this, locationMainActivity.class);
                startActivity(intent);
            }
        });

        iv_questlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity.this, questsActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finishAffinity();
            toast.cancel();
        }
    }

    class BackgroundTask extends AsyncTask<String, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {

        }

        protected String doInBackground(String... args0) {
            try {
                String familyCode = args0[0];
                target = "http://honeybee54953.dothome.co.kr/MainInFo.php?familyCode=" + familyCode;
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        public void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                String quest;
                quest = jsonArray.getString(0);

                System.out.println("gggg" + quest);
                quest = quest.replace("[", "");
                quest = quest.replace("]", "");
                quest = quest.replace("\"", "");
                qs_1.setText(quest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


