package com.example.honeybee.quest;

import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.request.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class questRequest extends StringRequest {
    final static private String URL ="http://honeybee54953.dothome.co.kr/quest.php";
    private Map<String,String> map;
    public questRequest(String userEmail,String quest,String familyCode, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userEmail",userEmail);
        map.put("quest",quest);
        map.put("familyCode",familyCode);

    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}