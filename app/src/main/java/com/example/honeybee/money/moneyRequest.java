package com.example.honeybee.money;

import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.request.StringRequest;


import java.util.HashMap;
import java.util.Map;

public class moneyRequest extends StringRequest {

    //서버 url 설정
    final static private String URL = "http://honeybee54953.dothome.co.kr/money.php";
    private Map<String, String> map;

    public moneyRequest( String userEmail, int money, int moneyNumber, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userEmail", userEmail);
        map.put("money" , money+"");
        map.put("moneyNumber", moneyNumber+"");

    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}