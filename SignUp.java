package com.example.lenovo.employeetrackingsystem;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private static EditText fullName, emailId, mobileNumber, location,
            password, confirmPassword;
    private static TextView login;
    private static Button signUpButton;
    private static CheckBox terms_conditions;
    private DatabaseReference mDatabase;
  //  private Firebase Emp_reg;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ProgressDialog progressDialog;
    // Initialize all views
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference Emp_reg = database.getReference("Emp_Registration");

        //Emp_reg = new Firebase("https://employee-tracking-system-7df39.firebaseio.com/Emp_Registration");

        fullName = (EditText) findViewById(R.id.fullName);
        emailId = (EditText) findViewById(R.id.userEmailId);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        location = (EditText) findViewById(R.id.location);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        signUpButton = (Button) findViewById(R.id.signUpBtn);

        terms_conditions = (CheckBox) findViewById(R.id.terms_conditions);
        mDatabase = FirebaseDatabase.getInstance().getReference();




        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkValidation()) {
                    String name = fullName.getText().toString().trim();
                    String email = emailId.getText().toString().trim();
                    String mobileno = mobileNumber.getText().toString().trim();
                    String loc = location.getText().toString().trim();
                    String pass = password.getText().toString().trim();
                    String des = "Employee";
                    String task = "Null";

                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.v("status", "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "Password Short", Toast.LENGTH_SHORT).show();
                                password.setText("");
                                confirmPassword.setText("");
                            } else if (task.isSuccessful()) {
                                               Toast.makeText(SignUp.this, "Successfull", Toast.LENGTH_SHORT).show();
                                fullName.setText("");
                                emailId.setText("");
                                mobileNumber.setText("");
                                location.setText("");
                                password.setText("");
                                confirmPassword.setText("");
                            }

                            // ...
                        }
                    });

                    String key = Emp_reg.push().getKey();
                    Emp_details emp = new Emp_details(key, name, email, mobileno, loc, des, task);
                    Map<String, Object> postValues = emp.toMap();

                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/Emp_details/" + key, postValues);

                    mDatabase.updateChildren(childUpdates);
                }
            }
        });
        // Setting text selector over textviews
        // XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        //try {
        //    ColorStateList csl = ColorStateList.createFromXml(getResources(),
        //            xrp);

        //   login.setTextColor(csl);
        //   terms_conditions.setTextColor(csl);
        // } catch (Exception e) {
        // }
    }

    // Check Validation Method
    private boolean checkValidation() {
        // Get all edittext texts
        String getFullName = fullName.getText().toString();
        String getEmailId = emailId.getText().toString();
        String getMobileNumber = mobileNumber.getText().toString();
        String getLocation = location.getText().toString();
        String getPassword = password.getText().toString();
        String getConfirmPassword = confirmPassword.getText().toString();

        // Pattern match for email id
        Pattern p = Pattern.compile(".+@.+\\..+");
        Matcher m = p.matcher(getEmailId);

        // Check if all strings are null or not
        if (getFullName.equals("") || getFullName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getLocation.equals("") || getLocation.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0) {

            // new CustomToast().Show_Toast(this, view,
            //        "All fields are required.");
            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();
            return false;
        }

            // Check if email id valid or not
        else if (!m.find()) {
//            new CustomToast().Show_Toast(this, view,
//                    "Your Email Id is Invalid.");

            Toast.makeText(getApplicationContext(), "Your Email Id is Invalid.", Toast.LENGTH_LONG).show();
            return false;
        }

            // Check if both password should be equal
        else if (!getConfirmPassword.equals(getPassword)) {
//            new CustomToast().Show_Toast(this, view,
//                    "Both password doesn't match.");
            Toast.makeText(getApplicationContext(), "Both password doesn't match.", Toast.LENGTH_LONG).show();
            return false;
        }


            // Make sure user should check Terms and Conditions checkbox
        else if (!terms_conditions.isChecked()) {
//            new CustomToast().Show_Toast(this, view,
//                    "Please select Terms and Conditions.");
            Toast.makeText(getApplicationContext(), "Please select Terms and Conditions.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;


}}