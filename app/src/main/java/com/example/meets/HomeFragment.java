package com.example.meets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment  {

    Context ct;

    LinearLayout meetsView;
    Button createMeets;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        meetsView = view.findViewById(R.id.meetsView);
        createMeets = view.findViewById(R.id.createMeets);

        ct = container.getContext();

        createMeets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMeets();
            }
        });
        return view;
    }

    private void createMeets(){
        TextView textViewNm = new TextView(ct);
        textViewNm.setText("새로운 모임 추가");
        textViewNm.setTextSize(12);
        textViewNm.setTypeface(null, Typeface.BOLD);
        textViewNm.setId(0);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        textViewNm.setLayoutParams(param);
        textViewNm.setBackgroundColor(Color.rgb(255, 255, 255));
        meetsView.addView(textViewNm);
    }
}