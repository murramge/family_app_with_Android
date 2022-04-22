package com.example.honeybee.calendar;


import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.request.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class calendarRequest extends StringRequest {

    //서버 url 설정
    final static private String URL = "http://honeybee54953.dothome.co.kr/Date.php";
    private Map<String, String> map;


    public calendarRequest(String calYear, String calMonth, String calDay, String calHour, String calMinute, String calPlan,String userEmail, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("calYear", calYear);
        map.put("calMonth", calMonth);
        map.put("calDay", calDay);
        map.put("calHour", calHour);
        map.put("calMinute", calMinute);
        map.put("calPlan", calPlan);
        map.put("userEmail",userEmail);


    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}