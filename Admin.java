package com.example.lenovo.employeetrackingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Admin extends AppCompatActivity {
    private Button assign,track;
    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Bundle extras = getIntent().getExtras();
        String hname = extras.getString("Name");
        assign = (Button) findViewById(R.id.assign);
        track = (Button) findViewById(R.id.track);
        name = (TextView) findViewById(R.id.name);
        name.setText("Hi " +hname);
        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent assign = new Intent(Admin.this, AssignTask.class);
                startActivity(assign);
            }
        });
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent track = new Intent(Admin.this, MapWebView.class);
                startActivity(track);
            }
        });
    }

}
