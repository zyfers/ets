package com.example.lenovo.employeetrackingsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity implements
        AdapterView.OnItemSelectedListener {
    private Spinner spin;
    String[] user = {"Select User" ,"Admin", "Employee", "Hr"  };
    Button cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spin = (Spinner) findViewById(R.id.spinner);

        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,user);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

    }


    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        Intent intent = new Intent();
        if (spin.getSelectedItem().toString().equals("Admin")) {
            startActivity(new Intent(MainActivity.this, LoginAdmin.class));
        } else if(spin.getSelectedItem().toString().equals("Employee")){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        else if(spin.getSelectedItem().toString().equals("Hr")){
            startActivity(new Intent(MainActivity.this, LoginHR.class));
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    } */
}