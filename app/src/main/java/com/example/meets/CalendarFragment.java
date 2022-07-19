package com.example.meets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.meets.databinding.CalendarListBinding;
import com.example.meets.ui.adapter.CalendarAdapter;
import com.example.meets.ui.viewmodel.CalendarListViewModel;

import java.util.ArrayList;

public class CalendarFragment extends Fragment {

    private CalendarListBinding binding;
    private CalendarAdapter calendarAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        binding = DataBindingUtil.getBinding(view); //setContentView...
        binding.setVariable(BR.model, new ViewModelProvider(this).get(CalendarListViewModel.class));
        binding.setLifecycleOwner(this);

        binding.getModel().initCalendarList();

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
        calendarAdapter = new CalendarAdapter();
        binding.calendar.setLayoutManager(manager);
        binding.calendar.setAdapter(calendarAdapter);
        observe();

        return view;
    }

    private void observe() {
        binding.getModel().mCalendarList.observe(getViewLifecycleOwner(), new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(ArrayList<Object> objects) {
                calendarAdapter.submitList(objects);
                if (binding.getModel().mCenterPosition > 0) {
                    binding.calendar.scrollToPosition(binding.getModel().mCenterPosition);
                }
            }
        });
    }
}