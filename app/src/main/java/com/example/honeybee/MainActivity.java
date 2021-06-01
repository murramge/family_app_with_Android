

package com.example.honeybee;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.honeybee.calendar.Fragment_calender;
import com.example.honeybee.story.Fragment_story;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
{ BottomNavigationView bottomNavigationView;
Fragment_main fragmentMain;
Fragment_chat fragmentChat;
Fragment_story fragmentStory;
Fragment_calender fragmentCalender;
Fragment_info fragmentInfo;
@Override protected void onCreate(Bundle savedInstanceState)
{ super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
bottomNavigationView = findViewById(R.id.bottomNavigationView);
//프래그먼트 생성
fragmentMain = new Fragment_main();
fragmentChat = new Fragment_chat();
fragmentStory = new Fragment_story();
fragmentCalender = new Fragment_calender();
fragmentInfo = new Fragment_info();

//제일 처음 띄워줄 뷰를 세팅해줍니다. commit();까지 해줘야 합니다.
getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragmentMain).commitAllowingStateLoss();
//bottomnavigationview의 아이콘을 선택 했을때 원하는 프래그먼트가 띄워질 수 있도록 리스너를 추가합니다.

    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
    { @Override public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        switch (menuItem.getItemId()){
            //menu_bottom.xml에서 지정해줬던 아이디 값을 받아와서 각 아이디값마다 다른 이벤트를 발생시킵니다.
            case R.id.tab1:{
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_layout, fragmentMain).commitAllowingStateLoss();
                return true; }
                case R.id.tab2:{
                    Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                    startActivity(intent);
                    return true; }
                    case R.id.tab3:{ getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_layout, fragmentStory).commitAllowingStateLoss();
                    return true; }
            case R.id.tab4:{ getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_layout, fragmentCalender).commitAllowingStateLoss();
                return true; }
            case R.id.tab5:{ getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_layout, fragmentInfo).commitAllowingStateLoss();
                return true; }
            default: return false;
        }
    }
    });
}
}


