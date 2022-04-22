package com.example.honeybee.quest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.honeybee.R;
import com.example.honeybee.mainActivity;
import com.example.honeybee.money.moneyActivity;
import com.example.honeybee.story.storyAdapter;
import com.example.honeybee.story.storyItem;
import com.example.honeybee.story.storyMainActivity;
import com.example.honeybee.quest.questActivity;
import com.example.honeybee.quest.questItem;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class
questsActivity extends AppCompatActivity {
    private ImageView iv_questplus;
    TextView tv_questuserEmail;
    ListView questlistview;
    ImageView bt_before;
    com.example.honeybee.quest.questAdapter questAdapter;

    ArrayList<questItem> questItems = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);
        iv_questplus=findViewById(R.id.iv_questplus);
        questlistview = findViewById(R.id.questlistview);
        tv_questuserEmail =findViewById(R.id.tv_questuserEmail);
        bt_before = findViewById(R.id.bt_before);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String userName = bundle.getString("userName");
        String userEmail = bundle.getString("userEmail");
        String moneyNumber = bundle.getString("moneyNumber");
        String familyCode = bundle.getString("familyCode");
        String exp = bundle.getString("exp");
        tv_questuserEmail.setText(userEmail);
        loadDB(familyCode);

        questAdapter = new questAdapter(getLayoutInflater(), questItems, exp);
        questlistview.setAdapter(questAdapter);
        bt_before.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(questsActivity.this, mainActivity.class);
                intent.putExtra("userName",userName);
                intent.putExtra("userEmail",userEmail);
                intent.putExtra("moneyNumber",moneyNumber);
                intent.putExtra("familyCode",familyCode);
                intent.putExtra("exp",exp);
                startActivity(intent);
            }
        });
        iv_questplus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(questsActivity.this, questActivity.class);
                intent.putExtra("userName",userName);
                intent.putExtra("userEmail",userEmail);
                intent.putExtra("moneyNumber",moneyNumber);
                intent.putExtra("familyCode",familyCode);
                intent.putExtra("exp",exp);
                startActivity(intent);
            }
        });
    }

    void loadDB(String familyCode){
        new Thread() {
            public void run() {
                String serverUri = "http://honeybee54953.dothome.co.kr/questloadDB.php?familyCode="+familyCode;
                try {
                    URL url = new URL(serverUri);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    final StringBuffer buffer = new StringBuffer();
                    String line = reader.readLine();

                    while (line!=null){
                        buffer.append(line+"\n");
                        line=reader.readLine();
                    }

                    String[] rows = buffer.toString().split(";");

                    questItems.clear();


                    for(String row : rows){
                        String[] datas = row.split("&");
                        if(datas.length!=3) continue;

                        String quest = datas[0];
                        String userEmail = datas[1];
                        String familyCode = datas[2];
                        questItems.add(new questItem(quest,userEmail,familyCode));


                    }

                }
                catch (MalformedURLException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }

            }

        }.start();
    }
}
