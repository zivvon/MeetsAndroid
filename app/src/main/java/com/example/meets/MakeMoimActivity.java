package com.example.meets;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MakeMoimActivity extends AppCompatActivity {

    private static final String TAG = "activity_makemoim";

    private TextView tvGood1;
    private TextView tvGood2;
    private TextView tvGood3;

    private Button btnGood1;
    private Button btnGood2;
    private Button btnGood3;

    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makemoim);

        Spinner spinner = findViewById(R.id.spn_Make);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.make, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tvGood1 = findViewById(R.id.tv_good1);
        tvGood2 = findViewById(R.id.tv_good2);
        tvGood3 = findViewById(R.id.tv_good3);
        tvGood1.setText(count1 + "");
        tvGood2.setText(count2 + "");
        tvGood3.setText(count3 + "");
        btnGood1 = findViewById(R.id.btn_good1);
        btnGood2 = findViewById(R.id.btn_good2);
        btnGood3 = findViewById(R.id.btn_good3);

        btnGood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btnGood1 : " + view.getClass().getName());
                count1++;
                tvGood1.setText(count1 + "");
            }
        });

        btnGood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btnGood1 : " + view.getClass().getName());
                count2++;
                tvGood2.setText(count2 + "");
            }
        });

        btnGood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btnGood1 : " + view.getClass().getName());
                count3++;
                tvGood3.setText(count3 + "");
            }
        });

    }
}