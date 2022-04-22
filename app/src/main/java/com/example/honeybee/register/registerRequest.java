package com.example.honeybee.register;

import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.request.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class registerRequest extends StringRequest {

    //서버 url 설정
    final static private String URL = "http://honeybee54953.dothome.co.kr/Register.php";
    private Map<String, String> map;


    public registerRequest(String userEmail, String userPassword, String userName, int userBirthday, int userPhone, int moneyNumber,String familyCode, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userEmail",userEmail);
        map.put("userPassword", userPassword);
        map.put("userName", userName);
        map.put("userBirthday", userBirthday + "");
        map.put("userPhone", userPhone + "");
        map.put("moneyNumber", moneyNumber + "");
        map.put("familyCode",familyCode);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}