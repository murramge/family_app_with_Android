package com.example.honeybee.myinfo;


import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.request.StringRequest;


import java.util.HashMap;
import java.util.Map;

public class myinfoRequest extends StringRequest {

    //서버 url 설정
    final static private String URL = "http://honeybee54953.dothome.co.kr/UserInfo.php";
    private Map<String, String> map;

    public myinfoRequest( String userMessage,String Name, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userMessage" , userMessage);
        map.put("Name",Name);

    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}