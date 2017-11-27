package com.example.lenovo.employeetrackingsystem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class TaskDetails extends AppCompatActivity {
    private TextView tname, tdesp, actualdesp, tloc, actualloc;
    private Button viewdir, sk, ek, accatask,earn;
    long startTime,difference;
    double time;
    private DatabaseReference mDatabase;
    private ChildEventListener mChildEventListener;
   // UserSessionManager session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        tname = (TextView) findViewById(R.id.taskname);
        tdesp = (TextView) findViewById(R.id.description);
        actualdesp = (TextView) findViewById(R.id.actualdescription);
        tloc = (TextView) findViewById(R.id.location);
        actualloc = (TextView) findViewById(R.id.locationtext);
        viewdir = (Button) findViewById(R.id.viewdirection);
        sk = (Button) findViewById(R.id.starttask);
        ek = (Button) findViewById(R.id.endtask);
        earn =(Button) findViewById(R.id.earn);

        accatask = (Button) findViewById(R.id.anothertask);
     //   session = new UserSessionManager(getApplicationContext());
       // if(session.checkLogin())
         //   finish();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference UnAss_tasks = database.getReference("Task_Details");
        final DatabaseReference Emp_reg = database.getReference("Emp_Registration");
        final DatabaseReference Duration = database.getReference("Emp_details");
        earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double pay = (time*0.0017512);
                String spay = Double.toString(pay);
                Toast.makeText(TaskDetails.this, "You got $"+spay+ " paid", Toast.LENGTH_LONG).show();
            }
        });
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getTask gt = dataSnapshot.getValue(getTask.class);
                tname.setText(gt.getTaskname());
                actualdesp.setText(gt.getTaskdescription());
                actualloc.setText(gt.getTasklocation());}

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getTask gt = dataSnapshot.getValue(getTask.class);
                tname.setText(gt.getTaskname());
                actualdesp.setText(gt.getTaskdescription());
                actualloc.setText(gt.getTasklocation());
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
        UnAss_tasks.addChildEventListener(mChildEventListener);



        viewdir.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String url = "http://maps.google.com/maps?daddr="+actualloc.getText().toString();
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
        startActivity(intent);
    }
});
        sk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = System.currentTimeMillis();
                Intent i1 = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(i1);
            }
        });
        ek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difference = System.currentTimeMillis() - startTime;
                time = (difference/1000);
                Intent i2 = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(i2);
                String duration = Double.toString(time);
                //mDatabase.child("Emp_details").child("task").push().setValue(duration);
                time etime = new time();
                etime.setDuration(duration);

                Duration.child("task").child("duration").setValue(etime).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                tname.setText("Task Name");
                actualloc.setText("select another task");
                 actualdesp.setText("select another task");
                    }
                });
                //Toast.makeText(getApplicationContext(),"Duration: " +time+"hours",Toast.LENGTH_LONG ).show();
            }
        });
        accatask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(TaskDetails.this, ViewTask.class);
                startActivity(i3);
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
     //           session.logoutUser();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(),"About Us",Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
