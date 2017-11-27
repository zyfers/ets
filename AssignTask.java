package com.example.lenovo.employeetrackingsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AssignTask extends AppCompatActivity {
    private EditText Taskname, Taskdescription, Tasklocation;
    private Button addBtn;
    private DatabaseReference mDatabase;
    //UserSessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_task);
        Taskname = (EditText) findViewById(R.id.etname);
        Taskdescription = (EditText) findViewById(R.id.etdescription);
        Tasklocation = (EditText) findViewById(R.id.etlocation);
        addBtn = (Button) findViewById(R.id.addtask);
      //  session = new UserSessionManager(getApplicationContext());
        //if(session.checkLogin())
          //  finish();
        //HashMap<String, String> user = session.getUserDetails();

        // get name
        //String name = user.get(UserSessionManager.KEY_NAME);

        // get email
//        String email = user.get(UserSessionManager.KEY_EMAIL);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference UnAss_tasks = database.getReference("Tasks");
        mDatabase = FirebaseDatabase.getInstance().getReference();



        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tasks tasks = new Tasks(Taskname.getText().toString(), Taskdescription.getText().toString(), Tasklocation.getText().toString());

                mDatabase.child("Task_Details").push().setValue(tasks);
                Taskname.setText("");
                Taskdescription.setText("");
                Tasklocation.setText("");




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
//                session.logoutUser();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(),"About Us",Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
