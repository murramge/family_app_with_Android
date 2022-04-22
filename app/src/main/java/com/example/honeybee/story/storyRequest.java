package com.example.honeybee.story;


import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.request.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class storyRequest extends StringRequest {

    //서버 url 설정
    final static private String URL = "http://honeybee54953.dothome.co.kr/Story.php";
    private Map<String, String> map;

    public storyRequest( String storyPostContent ,String userEmail, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("storyPostContent" , storyPostContent);
        map.put("userEmail",userEmail);
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}