package com.example.honeybee.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.honeybee.R;
import com.example.honeybee.login.loginActivity;
import com.example.honeybee.register.registerRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class registerActivity extends AppCompatActivity {
    EditText et_Email,et_Password,et_userName,et_userBirthday,et_userPhone,et_moneyNumber,et_code;
    Button btn_register,bt_createcode;
    ImageView bt_before;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bt_before = findViewById(R.id.bt_before);
        et_code = findViewById(R.id.et_code);
        et_Email = findViewById(R.id.et_Email);
        et_Password = findViewById(R.id.et_Password);
        et_userName = findViewById(R.id.et_userName);
        et_userBirthday = findViewById(R.id.et_userBirthday);
        et_userPhone = findViewById(R.id.et_userPhone);
        et_moneyNumber = findViewById(R.id.et_moneyNumber);
        bt_createcode = findViewById(R.id.bt_createcode);
        //회원가입 버튼 클릭하면 액션활동되게함
        btn_register = findViewById(R.id.btn_register);
        bt_createcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer key = new StringBuffer();
                Random rnd = new Random();
                for(int i=0;i<6 ;i++)
                {
                    int index = rnd.nextInt(3);
                    switch (index) {
                        case 0:
                            key.append(((int) (rnd.nextInt(26)) + 97));
                            break;
                        case 1:
                            key.append(((int) (rnd.nextInt(26)) + 65));
                            break;
                        case 2:
                            key.append((rnd.nextInt(10)));
                            break;
                    }
                }
                et_code.setText(key);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                String familyCode = et_code.getText().toString();
                String userEmail = et_Email.getText().toString();
                String userPassword = et_Password.getText().toString();
                String userName = et_userName.getText().toString();
                int userBirthday = Integer.parseInt(et_userBirthday.getText().toString());
                int userPhone = Integer.parseInt(et_userPhone.getText().toString());
                int moneyNumber = Integer.parseInt(et_moneyNumber.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 회원등록에 성공한 경우
                                Toast.makeText(getApplicationContext(),"회원 등록에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(registerActivity.this, loginActivity.class);
                                intent.putExtra("userName",userName);
                                intent.putExtra("moneyNumber",moneyNumber);
                                intent.putExtra("familyCode",familyCode);
                                startActivity(intent);
                            } else { // 회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(),"회원 등록에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                registerRequest registerRequest = new registerRequest(userEmail,userPassword,userName,userBirthday,userPhone,moneyNumber,familyCode, responseListener);
                RequestQueue queue = Volley.newRequestQueue(registerActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}