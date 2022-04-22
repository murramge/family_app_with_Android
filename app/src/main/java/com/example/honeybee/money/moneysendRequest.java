package com.example.honeybee.money;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.request.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class moneysendRequest extends StringRequest {
        final static private String URL = "http://honeybee54953.dothome.co.kr/moneyupdate.php";
        private Map<String, String> map;
        public  moneysendRequest(String money, String moneyNumber, Response.Listener<String> listener) {
              super(Method.POST, URL, listener, null);

              map = new HashMap<>();
              map.put("money",money);
                map.put("moneyNumber",moneyNumber);
        }
        protected Map<String, String> getParams() throws AuthFailureError {
                return map;
        }
}
