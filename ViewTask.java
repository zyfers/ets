package com.example.lenovo.employeetrackingsystem;

import android.content.Intent;
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

public class ViewTask extends AppCompatActivity {
    private Button btn;
    private TextView name, description, location;
    private DatabaseReference mDatabase;
    private ChildEventListener mChildEventListener;
  //  UserSessionManager session;

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        btn = (Button) findViewById(R.id.status);
        name = (TextView) findViewById(R.id.name);
        description = (TextView) findViewById(R.id.description);
        location = (TextView) findViewById(R.id.location);
//        session = new UserSessionManager(getApplicationContext());
  //      if(session.checkLogin())
    //        finish();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference UnAss_tasks = database.getReference("Task_Details");
        final DatabaseReference Emp_reg = database.getReference("Emp_Registration");
        final DatabaseReference Duration = database.getReference("Emp_details");

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getTask gt = dataSnapshot.getValue(getTask.class);
                name.setText(gt.getTaskname());
                description.setText(gt.getTaskdescription());
                location.setText(gt.getTasklocation());}

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getTask gt = dataSnapshot.getValue(getTask.class);
                name.setText(gt.getTaskname());
                description.setText(gt.getTaskdescription());
                location.setText(gt.getTasklocation());
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





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btn.setText("ASSIGNED");
                Intent mtask = new Intent(ViewTask.this, TaskDetails.class);
                startActivity(mtask);
                btn.setEnabled(false);



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
      //          session.logoutUser();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(),"About Us",Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}




