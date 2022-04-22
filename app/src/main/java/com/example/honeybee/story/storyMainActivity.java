package com.example.honeybee.story;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.honeybee.R;
import com.example.honeybee.R;
import com.example.honeybee.mainActivity;
import com.example.honeybee.story.storyAdapter;
import com.example.honeybee.story.storyItem;
import com.example.honeybee.story.storyPostActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;


public class storyMainActivity extends AppCompatActivity {
    ImageView iv_mainphotoadd,iv_story_back_bt;
    ListView listView;
    TextView story_username;
    com.example.honeybee.story.storyAdapter storyAdapter;

    ArrayList<storyItem> storyItems = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_story);
        iv_mainphotoadd= findViewById(R.id.iv_mainphotoadd);
        story_username = findViewById(R.id.story_username);
        iv_story_back_bt = findViewById(R.id.iv_story_back_bt);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String userName = bundle.getString("userName");
        String userEmail = bundle.getString("userEmail");
        String moneyNumber = bundle.getString("moneyNumber");
        String familyCode = bundle.getString("familyCode");
        String exp = bundle.getString("exp");
        story_username.setText(userName);
        loadDB();

        listView = findViewById(R.id.listView);
        storyAdapter = new storyAdapter(getLayoutInflater(),storyItems);
        listView.setAdapter(storyAdapter);

        iv_story_back_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(storyMainActivity.this, mainActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });
        iv_mainphotoadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(storyMainActivity.this,storyPostActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("moneyNumber", moneyNumber);
                intent.putExtra("familyCode", familyCode);
                intent.putExtra("exp", exp);
                startActivity(intent);
            }
        });

    }


    void loadDB(){
        new Thread() {
            public void run() {
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                String family = bundle.getString("familyCode");
                String serverUri = "http://honeybee54953.dothome.co.kr/loadDB.php?familyCode="+family;
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

                    storyItems.clear();
                

                    for(String row : rows){
                        String[] datas = row.split("&");
                        if(datas.length!=6) continue;
                        int no = Integer.parseInt(datas[0]);
                        String name = datas[1];
                        String msg = datas[2];
                        String imgPath = "http://honeybee54953.dothome.co.kr/"+datas[3];
                        String date = datas[4];
                        String familyCode = datas[5];

                        storyItems.add(new storyItem(no,name,msg,imgPath,date,familyCode));

                        runOnUiThread(new Runnable(){
                            public void run(){
                                storyAdapter.notifyDataSetChanged();
                            }
                        });
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