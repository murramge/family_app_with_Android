package com.example.honeybee.story;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.honeybee.R;

import java.util.ArrayList;

public class storyAdapter extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<storyItem> storyItems;

    public storyAdapter(LayoutInflater inflater, ArrayList<storyItem> storyItems){
        this.inflater = inflater;
        this.storyItems = storyItems;
    }

    @Override
    public int getCount() {
        return storyItems.size();
    }

    @Override
    public Object getItem(int position) {
        return storyItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(view==null){
            view= inflater.inflate(R.layout.list_item, viewGroup, false);
        }
        TextView tvName= view.findViewById(R.id.tv_name);
        TextView tvDate= view.findViewById(R.id.tv_date);
        TextView tvMsg= view.findViewById(R.id.tv_msg);
        TextView tvFamilycode = view.findViewById(R.id.tv_familycode);
        ImageView iv= view.findViewById(R.id.iv);

        storyItem storyItem = storyItems.get(position);
        tvName.setText(storyItem.getName());
        tvDate.setText(storyItem.getDate());
        tvMsg.setText(storyItem.getMsg());
        tvFamilycode.setText(storyItem.getFamilyCode());

        Glide.with(view).load(storyItem.getImgPath()).into(iv);

        return view;
    }
}
