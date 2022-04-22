package com.example.honeybee.quest;

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
import com.example.honeybee.quest.questRequest;
import com.example.honeybee.quest.questsActivity;
import com.example.honeybee.R;

import org.json.JSONException;
import org.json.JSONObject;

public class questActivity extends AppCompatActivity {
    private ImageView bt_before2;
    EditText et_user_quest;
    Button bt_quest_choice;
    private TextView tv_questemail,tv_familyCode,tv_exp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_list);
        et_user_quest = findViewById(R.id.et_user_quest);
        bt_quest_choice = findViewById(R.id.bt_quest_choice);
        tv_questemail = findViewById(R.id.tv_questemail);
        tv_familyCode = findViewById(R.id.tv_familyCode);
        bt_before2= findViewById(R.id.bt_before2);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String userName = bundle.getString("userName");
        String userEmail = bundle.getString("userEmail");
        String moneyNumber = bundle.getString("moneyNumber");
        String familyCode = bundle.getString("familyCode");
        String exp = bundle.getString("exp");
        tv_familyCode.setText(familyCode);
        tv_questemail.setText(userEmail);

        bt_before2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(questActivity.this,questsActivity.class);
                intent.putExtra("userName",userName);
                intent.putExtra("userEmail",userEmail);
                intent.putExtra("moneyNumber",moneyNumber);
                intent.putExtra("familyCode",familyCode);
                intent.putExtra("exp",exp);
                startActivity(intent);
            }
        });
        bt_quest_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quest = et_user_quest.getText().toString();
                String userEmail = tv_questemail.getText().toString();
                String familyCode = tv_familyCode.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(),"게시글 성공",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(questActivity.this, questsActivity.class);
                                intent.putExtra("userName",userName);
                                intent.putExtra("userEmail",userEmail);
                                intent.putExtra("moneyNumber",moneyNumber);
                                intent.putExtra("familyCode",familyCode);
                                intent.putExtra("exp",exp);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(),"게시글 실패",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                questRequest questRequest = new questRequest(userEmail, quest, familyCode ,responseListener);
                RequestQueue queue = Volley.newRequestQueue(questActivity.this);
                queue.add(questRequest);

            }

        });



    }

}


