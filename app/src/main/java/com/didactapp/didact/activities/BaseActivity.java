package com.didactapp.didact.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;


/**
 * This class is a base class for all the activities in the project - it's meant to aggregate
 * activity specific utility methods
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * convinience method to launch an activity
     *
     * @param activityFrom
     * @param activityTo
     */
    public static void launchActivity(BaseActivity activityFrom, Class<? extends BaseActivity> activityTo) {
        Intent activityIntent = new Intent(activityFrom, activityTo);
        activityFrom.startActivity(activityIntent);
    }

    /**
     * convinience method to launch an activity with an int intent extra payload
     * @param activityFrom
     * @param activityTo
     */
    public static void launchActivity(BaseActivity activityFrom, Class<? extends BaseActivity> activityTo, String key, int value) {
        Intent intent = new Intent(activityFrom, activityTo);
        intent.putExtra(key, value);
        activityFrom.startActivity(intent);
    }

    /**
     * convinience method to launch an activity with a String intent extra payload
     * @param activityFrom
     * @param activityTo
     */
    public static void launchActivity(BaseActivity activityFrom, Class<? extends BaseActivity> activityTo, String key, String value) {
        Intent intent = new Intent(activityFrom, activityTo);
        intent.putExtra(key, value);
        activityFrom.startActivity(intent);
    }

    /**
     * check whether or not the there's a network connection - doesn't necessarily mean there's an
     * actual internet connection.
     * @param activityFrom
     * @param activityTo
     */
    protected boolean isNetworkConnected() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // ARE WE CONNECTED TO THE NET
        return conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected();
    }


}
