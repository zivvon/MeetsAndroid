package com.example.meets.Adapter;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meets.CalendarActivity;
import com.example.meets.R;
import com.example.meets.WeekCalendarActivity;
import com.example.meets.util.CalendarUtil;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    ArrayList<LocalDate> dayList;

    public CalendarAdapter(ArrayList<LocalDate> dayList){
        this.dayList = dayList;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.calendar_cell, parent, false);

        return new CalendarViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        //날짜 적용
        LocalDate day = dayList.get(position);

        if(day == null){
            holder.dayText.setText("");
        }else{
            holder.dayText.setText(String.valueOf((day.getDayOfMonth())));
            
            //현재 날짜 색상 칠하기
            if(day.equals(CalendarUtil.selectedDate)){
                holder.dayText.setBackgroundResource(R.drawable.selected_date);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), WeekCalendarActivity.class);
                view.getContext().startActivity(intent);

                //누른 데이터 값을 weekcalendaractivity로 넘겨줘야함.. 어떻게?
            }
        });
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    class CalendarViewHolder extends RecyclerView.ViewHolder{

        //초기화
        TextView dayText;
        
        View parentView;

        public CalendarViewHolder(@NonNull View itemView){
            super(itemView);

            dayText = itemView.findViewById(R.id.dayText);
            
            parentView = itemView.findViewById(R.id.parentView);
        }
    }
}
