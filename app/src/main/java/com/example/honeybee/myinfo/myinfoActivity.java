package com.example.honeybee.myinfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.honeybee.mainActivity;
import com.example.honeybee.money.moneyActivity;
import com.example.honeybee.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class myinfoActivity extends AppCompatActivity {
    private TextView tv_infouser,tv_usermessage;
    private Button btn_messagechange,btn_money;
    private ImageView iv_money_back_bt;



    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String userName = bundle.getString("userName");
        String userEmail = bundle.getString("userEmail");
        String moneyNumber = bundle.getString("moneyNumber");
        String familyCode = bundle.getString("familyCode");
        String exp = bundle.getString("exp");

        iv_money_back_bt = findViewById(R.id.iv_money_back_bt);
        tv_usermessage = findViewById(R.id.tv_usermessage);

        tv_infouser = findViewById(R.id.tv_infouser);
        tv_infouser.setText(userName);

        btn_messagechange = findViewById(R.id.btn_messagechange);
        btn_money = findViewById(R.id.btn_money);

        iv_money_back_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myinfoActivity.this, mainActivity.class);
                intent.putExtra("userName",userName);
                intent.putExtra("userEmail",userEmail);
                intent.putExtra("moneyNumber",moneyNumber);
                intent.putExtra("familyCode",familyCode);
                intent.putExtra("exp",exp);
                startActivity(intent);
            }
        });
        btn_money.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myinfoActivity.this, moneyActivity.class);
                intent.putExtra("userName",userName);
                intent.putExtra("userEmail",userEmail);
                intent.putExtra("moneyNumber",moneyNumber);
                intent.putExtra("familyCode",familyCode);
                intent.putExtra("exp",exp);
                startActivity(intent);
            }
        });

        new BackgroundTask().execute(userName);
        btn_messagechange.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myinfoActivity.this, statemessageActivity.class);
                intent.putExtra("userName",userName);
                intent.putExtra("userEmail",userEmail);
                intent.putExtra("moneyNumber",moneyNumber);
                intent.putExtra("familyCode",familyCode);
                intent.putExtra("exp",exp);
                startActivity(intent);
            }
        });

    }

    class BackgroundTask extends AsyncTask<String,Void,String>
    {

        String target;
        @Override
        protected void onPreExecute() {

        }
        protected String doInBackground(String...args0){
            try{
                String userName = args0[0];
                target = "http://honeybee54953.dothome.co.kr/selectUserInfo.php?Name="+ userName;
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate();
        }

        @Override
        public void onPostExecute(String result){
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");

                String userMessage;
                userMessage = jsonArray.getString(0);
                System.out.println("gggg" + userMessage);
                userMessage=userMessage.replace("[","");
                userMessage=userMessage.replace("]","");
                userMessage=userMessage.replace("\"","");

                tv_usermessage.setText(userMessage);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}