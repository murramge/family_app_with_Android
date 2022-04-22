package com.example.honeybee.quest;

import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.request.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class questDeleteRequest extends StringRequest {

    final static private String URL = "http://honeybee54953.dothome.co.kr/deleteQuest.php";
    private Map<String, String> map;

    public questDeleteRequest( String quest, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();

        map.put("quest",quest);
    }
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

