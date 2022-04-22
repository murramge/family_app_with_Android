
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
import com.example.honeybee.money.moneyActivity;
import com.example.honeybee.money.moneyRequest;
import com.example.honeybee.R;

import org.json.JSONException;
import org.json.JSONObject;

public class moneychargeActivity extends AppCompatActivity {
    private EditText et_moneychargename,et_moneysendnumber,et_moneysendmoney;
    private Button bt_moneysend;
    private TextView money_email;
    private ImageView iv_money_back_bt;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneycharge);
        et_moneysendmoney = findViewById(R.id.et_moneysendmoney);
        et_moneysendnumber = findViewById(R.id.et_moneysendnumber);
        bt_moneysend = findViewById(R.id.bt_moneysend);
        money_email = findViewById(R.id.money_email);
        iv_money_back_bt = findViewById(R.id.iv_money_back_bt);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String userName = bundle.getString("userName");
        String userEmail = bundle.getString("userEmail");
        String moneyNumber = bundle.getString("moneyNumber");
        String familyCode = bundle.getString("familyCode");
        String exp = bundle.getString("exp");
        et_moneysendnumber.setText(moneyNumber);
        money_email.setText(userEmail);
        iv_money_back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(moneychargeActivity.this,moneyActivity.class);
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

                String userEmail = money_email.getText().toString();
                int money = Integer.parseInt(et_moneysendmoney.getText().toString());
                int moneyNumber = Integer.parseInt(et_moneysendnumber.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(),"게시글 성공",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(moneychargeActivity.this, moneyActivity.class);
                                intent.putExtra("userName", userName);
                                intent.putExtra("userEmail", userEmail);
                                intent.putExtra("moneyNumber", moneyNumber+"");
                                intent.putExtra("familyCode", familyCode);
                                intent.putExtra("exp", exp);
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
                moneyRequest moneyRequest = new moneyRequest( userEmail ,money ,moneyNumber, responseListener);
                RequestQueue queue = Volley.newRequestQueue(moneychargeActivity.this);
                queue.add(moneyRequest);
            }

        });

    }

}