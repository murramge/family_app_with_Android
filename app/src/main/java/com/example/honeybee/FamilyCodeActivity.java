package com.example.honeybee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.Random;

public class FamilyCodeActivity extends AppCompatActivity {


    private int familyCharLength = 8;
    private final char[] characterTable = {'A','B','C','D','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T'
            ,'U','Y','Z','1','2','3','4','5','6','7','8','9','0'};
    private TextView family_code;
    private Button btn_familycode;
    private Button btn_familynext;
    private String realfamilyCode;
    private TextView tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familycode);

        family_code = findViewById(R.id.family_code);
        btn_familycode = findViewById(R.id.btn_familycode);
        btn_familynext = findViewById(R.id.btn_familynext);
        tv_name = findViewById(R.id.tv_name);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        tv_name.setText(userName);


        btn_familycode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                realfamilyCode = executeGenerate();
                family_code.setText(realfamilyCode);
            }

        });

        btn_familynext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }
    public String executeGenerate(){
        Random random = new Random(System.currentTimeMillis());
        int tableLength = characterTable.length;

        StringBuffer buf = new StringBuffer();

        for(int i=0;i<familyCharLength;i++){
            buf.append(characterTable[random.nextInt(tableLength)]);
        }
        return buf.toString();
    }


}