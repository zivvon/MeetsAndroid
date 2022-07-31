package com.example.meets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MoimActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private View layout_before;
    private View layout_after;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moim);

        Spinner spinner = findViewById(R.id.spn_TeamList);
        Spinner spinner2 = findViewById(R.id.spn_MemberList);
        Spinner spinner3 = findViewById(R.id.spn_Schedule);
        Spinner spinner4 = findViewById(R.id.spn_Make);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.teamList, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.memberList, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.schedule, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.make, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
        spinner4.setAdapter(adapter4);

        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        spinner4.setOnItemSelectedListener(this);

        this.InitializeView();
        final EditText edittext=(EditText)findViewById(R.id.edittext);
        Button btn_makenotice=(Button)findViewById(R.id.btn_makenotice);
        final TextView textView=(TextView)findViewById(R.id.textview);

        btn_makenotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(edittext.getText());
            }
        });

        Button btn_plusmoin = findViewById(R.id.btn_plusmoim);

        btn_plusmoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlusMoimActivity.class);
                startActivity(intent);
            }
        });
    }

    public void InitializeView() {
        layout_before = (LinearLayout)findViewById(R.id.before);
        layout_after = (LinearLayout)findViewById(R.id.after);
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
        layout_before.setVisibility(View.VISIBLE);
        layout_after.setVisibility(View.INVISIBLE);

        if (view.getId() == R.id.btn_makeafter)
            layout_after.setVisibility(View.VISIBLE);
    }
}
