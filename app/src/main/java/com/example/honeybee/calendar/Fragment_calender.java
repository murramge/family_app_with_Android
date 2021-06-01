package com.example.honeybee.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.honeybee.R;


public class Fragment_calender extends Fragment {
    private TextView textView;
    private CalendarView calendarView;
    private ImageView iv_dateadd;
    final static private String URL = "http://honeybee54953.dothome.co.kr/Date.php";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        inflater.inflate(R.layout.fragment_calender, container, false);


        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_calender, container, false);
        TextView textView = (TextView) rootview.findViewById(R.id.textView);
        TextView dayView = (TextView) rootview.findViewById(R.id.dayView);
        TextView timeView = (TextView) rootview.findViewById(R.id.timeView);
        TextView planView = (TextView) rootview.findViewById(R.id.planView);
        CalendarView calendarView = (CalendarView) rootview.findViewById(R.id.calendarView);
        ImageView iv_dateadd = (ImageView)rootview.findViewById(R.id.iv_dateadd);

        iv_dateadd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CalenderActivity.class);
                startActivity(intent);


            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth){
                month += 1;
                textView.setText(String.format("%d년 %d월 %d일",year,month,dayOfMonth));
                dayView.setText(String.format("%d년 %d월 %d일",year,month,dayOfMonth));
            }

        });

        return rootview;
    }
}