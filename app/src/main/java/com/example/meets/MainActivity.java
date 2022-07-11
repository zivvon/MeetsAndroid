package com.example.meets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    FrameLayout home_ly;
    BottomNavigationView bottom_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init(); //객체 정의
        SettingListener(); //리스너 등록

        //맨 처음 시작할 탭 설정
        bottom_tab.setSelectedItemId(R.id.home);
    }

    private void init() {
        home_ly = findViewById(R.id.home_ly);
        bottom_tab = findViewById(R.id.bottom_tab);
    }

    private void SettingListener() {
        //선택 리스너 등록
        bottom_tab.setOnNavigationItemSelectedListener(new TabSelectedListener());
    }

    class TabSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.tab_home: {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.home_ly, new HomeFragment())
                            .commit();
                    return true;
                }
//                case R.id.tab_loading: {
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.home_ly, new LoadingFragment())
//                            .commit();
//                    return true;
//                }
                case R.id.tab_calendar: {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.home_ly, new CalendarFragment())
                            .commit();
                    return true;
                }
            }

            return false;
        }
    }
}