package com.example.lenovo.employeetrackingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Employee extends AppCompatActivity {
    private Button viewtasks,ongoingtask;
    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        Bundle extras = getIntent().getExtras();
        String hname = extras.getString("Name");
        viewtasks = (Button) findViewById(R.id.viewtask);
        ongoingtask = (Button) findViewById(R.id.ontask);
        name = (TextView) findViewById(R.id.name);
        name.setText("Hi " +hname);
    viewtasks.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent viewtask = new Intent(Employee.this, ViewTask.class);
            startActivity(viewtask);
        }
    });
        ongoingtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ontask = new Intent(Employee.this, TaskDetails.class);
                startActivity(ontask);
            }
        });
    }

}
