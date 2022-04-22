package com.example.honeybee.quest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.honeybee.quest.questDeleteRequest;
import com.example.honeybee.quest.questItem;
import com.example.honeybee.R;
import com.example.honeybee.chat.chatMainActivity;
import com.example.honeybee.mainActivity;
import com.example.honeybee.money.moneyActivity;
import com.example.honeybee.money.moneysendActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class questAdapter extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<questItem> questItems;
    String exp;
    private Context context;


    public questAdapter(LayoutInflater inflater, ArrayList<questItem> questItems,String exp){
        this.inflater = inflater;
        this.questItems = questItems;
        this.exp =exp;
    }
    @Override
    public int getCount() {
        return questItems.size();
    }

    @Override
    public Object getItem(int position) {
        return questItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {


        if(view==null){
            view= inflater.inflate(R.layout.quest_list_item, viewGroup,false);

        }
        TextView tv_quest_useremail=view.findViewById(R.id.tv_quest_useremail);
        TextView tv_quest=view.findViewById(R.id.tv_quest);
        TextView tv_familycode = view.findViewById(R.id.tv_userfamilycode);
        Button btn_questfinish=view.findViewById(R.id.btn_questfinish);
        questItem questItem = questItems.get(position);
        tv_quest.setText(questItem.getquest());
        tv_familycode.setText(questItem.getFamilyCode());
        tv_quest_useremail.setText(questItem.getUserEmail());
        btn_questfinish.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String userEmail = tv_quest_useremail.getText().toString();
                String quest = tv_quest.getText().toString();
                String familyCode = tv_familycode.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                int ex = Integer.parseInt(exp);
                                ex +=1;
                                Toast.makeText(view.getContext(), "안녕하세요", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(view.getContext(),mainActivity.class);
                                intent.putExtra("userEmail",userEmail);
                                intent.putExtra("familyCode",familyCode);
                                intent.putExtra("exp",ex+"");
                                view.getContext().startActivity(intent);
                            } else {
                                Toast.makeText(view.getContext(), "실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                };
                questDeleteRequest questDeleteRequest = new questDeleteRequest(quest,responseListener);
                RequestQueue queue = Volley.newRequestQueue(view.getContext());
                queue.add(questDeleteRequest);

            }
        });
        return view;
    }

}