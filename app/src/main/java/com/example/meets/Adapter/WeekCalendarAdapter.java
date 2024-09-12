package com.example.meets.Adapter;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meets.R;
import com.example.meets.util.CalendarUtil;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeekCalendarAdapter extends RecyclerView.Adapter<WeekCalendarAdapter.CalendarViewHolder> {
    ArrayList<LocalDate> dayList;

    public WeekCalendarAdapter(ArrayList<LocalDate> dayList){
        this.dayList = dayList;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.week_calendar_cell, parent, false);

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
