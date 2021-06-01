package com.example.honeybee.story;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class StoryRequest  extends StringRequest {

    //서버 url 설정
    final static private String URL = "http://honeybee54953.dothome.co.kr/story.php";
    private Map<String, String> map;

    public StoryRequest( int postID ,String storyPost, String uEmail, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("postID",postID + "");
        map.put("storyPost", storyPost);
        map.put("uEmail", uEmail);

    }
}
