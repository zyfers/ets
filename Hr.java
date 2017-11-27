package com.example.lenovo.employeetrackingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Hr extends AppCompatActivity {
    private Button register,viewdetails;
    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr);
        Bundle extras = getIntent().getExtras();
        String hname = extras.getString("Name");
        register = (Button) findViewById(R.id.register);
        viewdetails = (Button) findViewById(R.id.viewdetails);
        name = (TextView) findViewById(R.id.name);
        name.setText("Hi " +hname);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Hr.this,SignUp.class);
                 startActivity(i);

            }
        });
        viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(Hr.this, WorkDetails.class);
                startActivity(view);
            }
        });






    }
}
