package com.example.lenovo.employeetrackingsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    // Creating EditText.
    EditText email, password,name ;
    TextView fp;
    // Creating string to hold values.
    String EmailHolder, PasswordHolder,Designation,nameholder;
    private static final String TAG = "EmailPassword";
    // Creating buttons.
    Button Login,SignUP ;

    // Creating Boolean to hold EditText empty true false value.
    Boolean EditTextEmptyCheck;

    // Creating progress dialog.
    ProgressDialog progressDialog;
    //Get the firebase reference
  //  var ref = new Firebase("https://employee-tracking-system-7df39.firebaseio.com/");


    // Creating FirebaseAuth object.
    FirebaseAuth firebaseAuth;
    //UserSessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
      //  session = new UserSessionManager(getApplicationContext());
        // Assign ID's to EditText.
        email = (EditText)findViewById(R.id.editText_email);
        password = (EditText)findViewById(R.id.editText_password);
        name = (EditText) findViewById(R.id.name);
        fp = (TextView) findViewById(R.id.fp);
        firebaseAuth = FirebaseAuth.getInstance();
        // Assign ID's to button.
        Login = (Button)findViewById(R.id.button_login);

      /*  ref.onAuth(function(authData) {
            if (authData && isNewUser) {
                // save the user's profile into Firebase so we can list users,
                // use them in Security and Firebase Rules, and show profiles
                ref.child("users").child(authData.uid).set({
                        provider: authData.provider,
                        name: getName(authData)
                //some more user data
            });
            }
        }); */





        fp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String resetemail = email.getText().toString().trim();
                        progressDialog.setMessage("Please Wait");

                        // Showing progressDialog.
                        progressDialog.show();
                        firebaseAuth.sendPasswordResetEmail(resetemail)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            progressDialog.dismiss();

                                            Toast.makeText(LoginActivity.this, "Reset password mail sent",Toast.LENGTH_LONG).show();
                                            password.setText("");
                                        }
                                    }
                                });
                    }
                });



        progressDialog =  new ProgressDialog(LoginActivity.this);

        // Assign FirebaseAuth instance to FirebaseAuth object.
       // firebaseAuth = FirebaseAuth.getInstance();


        // Checking if user already logged in before and not logged out properly.
        /*if(firebaseAuth.getCurrentUser() != null){

            // Finishing current Login Activity.
            finish();

            // Opening UserProfileActivity .
            Intent intent = new Intent(LoginActivity.this, Employee.class);
            startActivity(intent);
        }*/


        // Adding click listener to login button.
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling method CheckEditTextIsEmptyOrNot().
                CheckEditTextIsEmptyOrNot();

                // If  EditTextEmptyCheck == true
                if(EditTextEmptyCheck)
                {

                    // If  EditTextEmptyCheck == true then login function called.
                    LoginFunction();

                }
                else {

                    // If  EditTextEmptyCheck == false then toast display on screen.
                    Toast.makeText(LoginActivity.this, "Please Fill All the Fields", Toast.LENGTH_LONG).show();
                }


            }
        });

        // Adding click listener to Sign up button.

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        //updateUI(currentUser);
    }





    // Creating method to check EditText is empty or not.
   public void CheckEditTextIsEmptyOrNot(){

       Log.d("test","verify");

        // Getting value form Email's EditText and fill into EmailHolder string variable.
        EmailHolder = email.getText().toString().trim();

        // Getting value form Password's EditText and fill into PasswordHolder string variable.
        PasswordHolder = password.getText().toString().trim();

        // Checking Both EditText is empty or not.
        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {

            // If any of EditText is empty then set value as false.
            EditTextEmptyCheck = false;

        }
        else {

            // If any of EditText is empty then set value as true.
            EditTextEmptyCheck = true ;

        }

    }

    // Creating login function.
    public void LoginFunction(){

        // Setting up message in progressDialog.
        progressDialog.setMessage("Please Wait");

        // Showing progressDialog.
        progressDialog.show();
        Log.d("test","verify2");
        EmailHolder = email.getText().toString().trim();

        // Getting value form Password's EditText and fill into PasswordHolder string variable.
        PasswordHolder = password.getText().toString().trim();

        // Calling  signInWithEmailAndPassword function with firebase object and passing EmailHolder and PasswordHolder inside it.
       firebaseAuth.signInWithEmailAndPassword(EmailHolder, PasswordHolder)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("test","verify3");
                        // If task done Successful.
                        if(task.isSuccessful()){
                            Log.d(TAG, "signInWithEmail:success");
                            // Hiding the progress dialog.
                            progressDialog.dismiss();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                           // updateUI(user);
                            // Closing the current Login Activity.
                            finish();


                            // Opening the UserProfileActivity.
                            Intent intent = new Intent(LoginActivity.this, Employee.class);

                            intent.putExtra("Name",name.getText().toString().trim());
                            startActivity(intent);
                        }
                        else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            // Hiding the progress dialog.
                            progressDialog.dismiss();

                            // Showing toast message when email or password not found in Firebase Online database.
                            Toast.makeText(LoginActivity.this, "Email or Password Not found, Please Try Again", Toast.LENGTH_LONG).show();
                         //   updateUI(null);
                        }
                    }


                });

    }

}