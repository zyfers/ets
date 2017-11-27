package com.example.lenovo.employeetrackingsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WorkDetails extends AppCompatActivity {
    private Button submit, calculatepay;
    TextView workinghours, time, displaypay;
    private DatabaseReference mDatabase;
    private ChildEventListener mChildEventListener;
  //  UserSessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_details);
        submit = (Button) findViewById(R.id.submit);
        calculatepay = (Button) findViewById(R.id.calculatepay);
        workinghours = (TextView) findViewById(R.id.workinghours);
        time = (TextView) findViewById(R.id.time);
        displaypay = (TextView) findViewById(R.id.displaypay);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference Duration = database.getReference("Emp_details");



        //    session = new UserSessionManager(getApplicationContext());
      //  if(session.checkLogin())
        //    finish();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workinghours.setVisibility(View.VISIBLE);
                time.setVisibility(View.VISIBLE);
                calculatepay.setVisibility(View.VISIBLE);
                displaypay.setVisibility(View.VISIBLE);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference Duration = database.getReference("Emp_details");

                mChildEventListener = new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        time gt = dataSnapshot.getValue(time.class);
                        workinghours.setText(gt.getDuration());
                    }
                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };
                Duration.addChildEventListener(mChildEventListener);



            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
          //      session.logoutUser();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(),"About Us",Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
