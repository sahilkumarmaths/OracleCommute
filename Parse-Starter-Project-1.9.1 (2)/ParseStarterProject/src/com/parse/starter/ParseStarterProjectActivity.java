package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseCrashReporting;
import com.parse.ParseUser;

public class ParseStarterProjectActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        ParseCrashReporting.enable(this);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here

        Parse.initialize(this,"MpTpblv5HgufOlBxG3q4xdrKYskcA8rJbQLCozyW", "pa1yuxLCOjK3llXPeRzK9gpWlk8521AuzbOx3NEh");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
        Intent intent = new Intent(ParseStarterProjectActivity.this,
                MainActivity.class);
        startActivity(intent);
        finish();

    }
}
