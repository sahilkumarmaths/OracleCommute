package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;


public class Welcome extends Activity {
 EditText fullName,phoneNumber,address, empId,timeToOracle,timeFromOracle;
 Button save,logout;
 String fullNametxt,phoneNumbertxt,addresstxt, empIdtxt,timeToOracletxt,timeFromOracletxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        fullName = (EditText) findViewById(R.id.fullName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        address = (EditText) findViewById(R.id.address);
       //empId = (EditText) findViewById(R.id.eID);
        timeToOracle = (EditText) findViewById(R.id.depTo);
        timeFromOracle = (EditText) findViewById(R.id.depFrom);
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fullNametxt = fullName.getText().toString();
                phoneNumbertxt = phoneNumber.getText().toString();
                addresstxt = address.getText().toString();
                timeToOracletxt = timeToOracle.getText().toString();
                timeFromOracletxt= timeFromOracle.getText().toString();
                // virendra's code

                Intent intent = new Intent(
                        Welcome.this,
                        MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        // Locate Button in welcome.xml
        logout = (Button) findViewById(R.id.logout);

        // Logout Button Click Listener
        logout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Logout current user
                ParseUser.logOut();
                finish();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
