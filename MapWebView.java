package com.example.lenovo.employeetrackingsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MapWebView extends AppCompatActivity {
private WebView myWebView;
private String lat, lng;
    private TextView currentlocation,lati, longi;

    //    UserSessionManager session;
    private static final String TAG = "LocDatabase";

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;


    String mapPath = "http://maps.google.com/?q="+lati+","+longi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_web_view);
        currentlocation = (TextView) findViewById(R.id.currentloc);
        myWebView = (WebView) findViewById(R.id.mapView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(mapPath);

   //     session = new UserSessionManager(getApplicationContext());
  //    if(session.checkLogin())
   //         finish();
      /* lati =(TextView) findViewById(R.id.lati);
        longi =(TextView) findViewById(R.id.longi);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Emp_details");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Loc uInfo = new Loc();
            uInfo.setLat(ds.child("latitude").getValue(Loc.class).getLat()); //set the name
            uInfo.setLng(ds.child("longitude").getValue(Loc.class).getLng()); //set the email

            lati.setText(uInfo.getLat());
            longi.setText(uInfo.getLng());

        }*/
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
                Toast.makeText(getApplicationContext(),"Log Out",Toast.LENGTH_LONG).show();
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