
package com.example.honeybee.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.honeybee.myinfo.myinfoActivity;
import com.example.honeybee.myinfo.myinfoRequest;
import com.example.honeybee.R;
import com.example.honeybee.login.loginActivity;
import com.example.honeybee.register.registerActivity;
import com.example.honeybee.register.registerRequest;
import com.example.honeybee.story.storyMainActivity;
import com.example.honeybee.story.storyPostActivity;
import com.example.honeybee.story.storyRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class statemessageActivity extends AppCompatActivity {
    private TextView tv_userName;
    private EditText et_message;
    private Button button3,button;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statemessagewindow);
        button3 = findViewById(R.id.button3);
        et_message = findViewById(R.id.et_message);
        tv_userName = findViewById(R.id.tv_userName);
        button = findViewById(R.id.button);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String userName = bundle.getString("userName");
        String userEmail = bundle.getString("userEmail");
        String moneyNumber = bundle.getString("moneyNumber");
        String familyCode = bundle.getString("familyCode");
        String exp = bundle.getString("exp");
        tv_userName.setText(userName);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(statemessageActivity.this,myinfoActivity.class);
                intent.putExtra("userName",userName);
                intent.putExtra("userEmail",userEmail);
                intent.putExtra("moneyNumber",moneyNumber);
                intent.putExtra("familyCode",familyCode);
                intent.putExtra("exp",exp);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String Name = tv_userName.getText().toString();
                String userMessage = et_message.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {

                                Toast.makeText(getApplicationContext(),"게시글 성공",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(statemessageActivity.this, myinfoActivity.class);
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
                myinfoRequest myinfoRequest = new myinfoRequest(userMessage,Name ,responseListener);
                RequestQueue queue = Volley.newRequestQueue(statemessageActivity.this);
                queue.add(myinfoRequest);
            }
        });
    }
}