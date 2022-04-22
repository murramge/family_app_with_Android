package com.example.honeybee.story;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.Manifest;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;
import com.example.honeybee.R;

public class storyPostActivity extends AppCompatActivity {
    EditText text_post;
    TextView story_email,tv_userfamilycode;
    ImageView iv_iv;


    String imgPath;
    String familyCode;
    String userEmail;
    String userName;
    String moneyNumber,exp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_story);

        text_post=findViewById(R.id.text_post);
        iv_iv=findViewById(R.id.iv_iv);
        story_email=findViewById(R.id.story_email);
        tv_userfamilycode=findViewById(R.id.tv_userfamilycode);
        story_email.setText(userEmail);

        //외부 저장소에 권한 필요, 동적 커미션
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
       userName = bundle.getString("userName");
       userEmail = bundle.getString("userEmail");
        moneyNumber = bundle.getString("moneyNumber");
        familyCode = bundle.getString("familyCode");
        exp = bundle.getString("exp");
        tv_userfamilycode.setText(familyCode);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            int permissionResult = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(permissionResult== PackageManager.PERMISSION_DENIED){
                String[] permissions= new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions,10);
            }
        }

    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions,grantResults);
        switch (requestCode){
            case 10:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "외부 메모리 읽기/쓰기 사용가능",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this,"외부 메모리 읽기/쓰기 제한",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public void clickBtn(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,10);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 10:
                if(resultCode==RESULT_OK){
                    Toast.makeText(this,"RESULT_OK",Toast.LENGTH_SHORT).show();
                    Uri uri = data.getData();
                    if(uri!=null){
                        iv_iv.setImageURI(uri);
                        imgPath= getRealPathFromUri(uri);
                        new AlertDialog.Builder(this).setMessage(uri.toString()+"\n"+imgPath).create().show();
                    }
                }
                else {
                    Toast.makeText(this, "이미지 선택 안함",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    String getRealPathFromUri(Uri uri){
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, uri, proj, null,null,null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
     }

    public void clickUpload(View view) {
        String name = story_email.getText().toString();
        String familyCode = tv_userfamilycode.getText().toString();
        String msg = text_post.getText().toString();

        String serverUrl="http://honeybee54953.dothome.co.kr/insertDB.php";

    SimpleMultiPartRequest smpr = new SimpleMultiPartRequest(Request.Method.POST,serverUrl,new Response.Listener<String>() {
        public void onResponse(String response) {
            new AlertDialog.Builder(storyPostActivity.this).setMessage("응답" + response).create().show();
        }
    }, new Response.ErrorListener(){
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(storyPostActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

        }

    });
    smpr.addStringParam("name", name);
    smpr.addStringParam("msg",msg);
    smpr.addStringParam("familyCode",familyCode);
    smpr.addFile("img",imgPath);

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(smpr);
}
    public void clickLoad(View view) {

        Intent intent = new Intent(storyPostActivity.this, storyMainActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("userEmail", userEmail);
        intent.putExtra("moneyNumber", moneyNumber);
        intent.putExtra("familyCode", familyCode);
        intent.putExtra("exp", exp);
        startActivity(intent);
    }
}