
package com.example.honeybee.money;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class moneysendActivity extends AppCompatActivity {
    private EditText et_moneysendname,et_moneysendnumber,et_moneysendmoney;
    private Button bt_moneysend;
    private ImageView iv_money_back_bt;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneysend);
        et_moneysendname = findViewById(R.id.et_moneysendname);
        et_moneysendnumber = findViewById(R.id.et_moneysendnumber);
        et_moneysendmoney = findViewById(R.id.et_moneysendmoney);
        iv_money_back_bt = findViewById(R.id.iv_money_back_bt);
        bt_moneysend = findViewById(R.id.bt_moneysend);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String userName = bundle.getString("userName");
        String userEmail = bundle.getString("userEmail");
        String moneyNumber = bundle.getString("moneyNumber");
        String familyCode = bundle.getString("familyCode");
        String exp = bundle.getString("exp");
        iv_money_back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(moneysendActivity.this,moneyActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });
        bt_moneysend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String money = et_moneysendmoney.getText().toString();
                String moneyNumber = et_moneysendnumber.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String> () {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(), "성공",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(moneysendActivity.this, moneyActivity.class);
                                intent.putExtra("userName", userName);
                                intent.putExtra("userEmail", userEmail);
                                intent.putExtra("moneyNumber", moneyNumber);
                                intent.putExtra("familyCode", familyCode);
                                intent.putExtra("exp", exp);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                moneysendRequest moneysendRequest = new moneysendRequest(money,moneyNumber, responseListener);
                RequestQueue queue = Volley.newRequestQueue(moneysendActivity.this);
                queue.add(moneysendRequest);

            }
        });

    }

}