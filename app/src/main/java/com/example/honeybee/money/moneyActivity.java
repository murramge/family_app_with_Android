
package com.example.honeybee.money;

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
import com.example.honeybee.money.moneychargeActivity;
import com.example.honeybee.money.moneysendActivity;
import com.example.honeybee.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class moneyActivity extends AppCompatActivity {
    private Button btn_moneysend,btn_moneycharge;
    private TextView tv_money,tv_userEmail;
    private ImageView iv_money_back_bt;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        tv_userEmail = findViewById(R.id.tv_userEmail);
        btn_moneycharge = findViewById(R.id.btn_moneycharge);
        btn_moneysend = findViewById(R.id.btn_moneymove);
        iv_money_back_bt = findViewById(R.id.iv_money_back_bt);
        tv_money = findViewById(R.id.tv_money);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String userName = bundle.getString("userName");
        String userEmail = bundle.getString("userEmail");
        String moneyNumber = bundle.getString("moneyNumber");
        String familyCode = bundle.getString("familyCode");
        String exp = bundle.getString("exp");
        tv_userEmail.setText(userEmail);

        new BackgroundTask().execute(userEmail,moneyNumber);
        iv_money_back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(moneyActivity.this, mainActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });
        btn_moneycharge.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(moneyActivity.this, moneychargeActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });

        btn_moneysend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(moneyActivity.this, moneysendActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });

    }
    class BackgroundTask extends AsyncTask<String,Void,String>{
        String target;

        @Override
        protected void onPreExecute() {

        }
        protected String doInBackground(String...args0){
            try{
                String userEmail = args0[0];
                int moneyNumber = Integer.parseInt(args0[1]);

                target = "http://honeybee54953.dothome.co.kr/selectMoney.php?userEmail=" +userEmail+ "&moneyNumber="+moneyNumber;
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp=bufferedReader.readLine())!=null)
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
        public void onProgressUpdate(Void... values){super.onProgressUpdate();}

        public void onPostExecute(String result){
            try{
//
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                    String money;
                     money = jsonArray.getString(0);
                    System.out.println("gggg" + money);
                    money = money.replace("[", "");
                    money = money.replace("]", "");
                    money = money.replace("\"", "");
                    tv_money.setText(money);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}