package com.didactapp.didact.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by roman on 12/03/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static void launchActivity(BaseActivity activityFrom, Class<? extends BaseActivity> activityTo) {
        Intent activityIntent = new Intent(activityFrom, activityTo);
        activityFrom.startActivity(activityIntent);
    }

    public static void launchActivity(BaseActivity activityFrom, Class<? extends BaseActivity> activityTo, String key, int value) {
        Intent intent = new Intent(activityFrom, activityTo);
        intent.putExtra(key, value);
        activityFrom.startActivity(intent);
    }

    public static void launchActivity(BaseActivity activityFrom, Class<? extends BaseActivity> activityTo, String key, String value) {
        Intent intent = new Intent(activityFrom, activityTo);
        intent.putExtra(key, value);
        activityFrom.startActivity(intent);
    }

    protected boolean isNetworkConnected() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // ARE WE CONNECTED TO THE NET
        return conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected();
    }


}
