package com.example.honeybee.story;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;


import com.example.honeybee.R;


public class Fragment_story extends Fragment {
        private ImageView iv_mainphotoadd;


        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


            inflater.inflate(R.layout.fragment_main_story, container, false);

            ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_main_story,container,false);
            ImageView addphoto_button = (ImageView)rootview.findViewById(R.id.iv_mainphotoadd);




            addphoto_button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),Fragment_post_story.class);
                    startActivity(intent);


                }
            });


            return rootview;
        }
    }


