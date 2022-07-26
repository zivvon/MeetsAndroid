package com.example.meets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MoimActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private View layout_cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moim);

        Spinner spinner = findViewById(R.id.spn_TeamList);
        Spinner spinner2 = findViewById(R.id.spn_MemberList);
        Spinner spinner3 = findViewById(R.id.spn_Schedule);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.teamList, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.memberList, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.schedule, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);

        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);

        this.InitializeView();

        Button btn_makemoin = (Button) findViewById(R.id.btn_makemoim);
        btn_makemoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MakeMoimActivity.class);
                startActivity(intent);
            }
        });
    }

    public void InitializeView() {
        layout_cal = (LinearLayout)findViewById(R.id.cal);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        layout_cal.setVisibility(View.INVISIBLE);

        switch (view.getId()) {
            case R.id.btn_cal:
                layout_cal.setVisibility(View.VISIBLE);
                break;
        }
    }
}
