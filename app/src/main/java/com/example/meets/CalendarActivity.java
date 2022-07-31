package com.example.meets;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meets.Adapter.CalendarAdapter;
import com.example.meets.util.CalendarUtil;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    //개인 일정추가 다이얼로그
    Dialog todo_dialog;

    TextView monthYearText; //년월 텍스트뷰

    Button moveHome;
    Button moveCal;
    Button moveMypg;

    Button moveWeekCal;

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        recyclerView = findViewById(R.id.recyclerView);

        monthYearText = findViewById(R.id.monthYearText);
        Button prevBtn = findViewById(R.id.pre_btn);
        Button nextBtn = findViewById(R.id.next_btn);

        //하단탭 버튼 설정
        moveHome = findViewById(R.id.homeBtn);
        moveCal = findViewById(R.id.calBtn);
        moveMypg = findViewById(R.id.myBtn);

        //weekCal 버튼 설정
        moveWeekCal = findViewById(R.id.up_page_btn);

        CalendarUtil.selectedDate = LocalDate.now(); //현재날짜

        setMonthView(); //화면설정

        prevBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                //현재 월-1
                CalendarUtil.selectedDate = CalendarUtil.selectedDate.minusMonths(1);
                setMonthView();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                //현재 월+1
                CalendarUtil.selectedDate = CalendarUtil.selectedDate.plusMonths(1);
                setMonthView();
            }
        });

        //개인 일정 다이얼로그
        todo_dialog = new Dialog(CalendarActivity.this);
        todo_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        todo_dialog.setContentView(R.layout.todo_dialog);

        // 버튼: 커스텀 다이얼로그 띄우기
        findViewById(R.id.todo_plus_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                showTodoDialog();
            }
        });
    }

    public void moveHome(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void moveCal(View v){
        Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
        startActivity(intent);
    }

    public void moveMypg(View v){
        Intent intent = new Intent(getApplicationContext(), MypgActivity.class);
        startActivity(intent);
    }

    public void moveWeekCal(View v){
        Intent intent = new Intent(getApplicationContext(), WeekCalendarActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy M월");

        return date.format(formatter);
    }

    //화면 설정
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        //년월 텍스트뷰 setting
        monthYearText.setText(monthYearFromDate(CalendarUtil.selectedDate));

        //해당 월 가져오기
        ArrayList<LocalDate> dayList = daysInMonthArray(CalendarUtil.selectedDate);

        //어댑터 데이터 적용
        CalendarAdapter adapter = new CalendarAdapter(dayList);

        //레이아웃 설정
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 7);

        //레이아웃 적용
        recyclerView.setLayoutManager(manager);

        //어댑터 적용
        recyclerView.setAdapter(adapter);
    }

    //날짜 생성 메소드
    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<LocalDate> daysInMonthArray(LocalDate date){
        ArrayList<LocalDate> dayList = new ArrayList<>();

        YearMonth yearMonth = YearMonth.from(date);

        //해당 월 마지막 날짜(28, 30, 31)
        int lastDay = yearMonth.lengthOfMonth();

        //해당 월의 1일
        LocalDate firstDay = CalendarUtil.selectedDate.withDayOfMonth(1);

        //첫번쨰 날 요일
        int dayOfWeek = firstDay.getDayOfWeek().getValue();

        for (int i = 1; i < 42; i++){
            if(i <= dayOfWeek || i > lastDay + dayOfWeek){
                dayList.add(null);
            } else {
                dayList.add(LocalDate.of(CalendarUtil.selectedDate.getYear(), CalendarUtil.selectedDate.getMonth(),
                        i - dayOfWeek));
            }
        }

        return dayList;
    }

    //개인 일정 추가 다이얼로그
    public void showTodoDialog(){
        todo_dialog.getWindow().setGravity(Gravity.BOTTOM);

        // Dialog 사이즈 조절 하기
        WindowManager.LayoutParams params = todo_dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        todo_dialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        todo_dialog.show();

        Button closeBtn = todo_dialog.findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                todo_dialog.dismiss();
            }
        });
    }
}
