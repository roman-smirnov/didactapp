package com.didactapp.didact.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by roman on 12/03/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static void launchActivity(BaseActivity activityFrom, Class<? extends BaseActivity> activityTo) {
        Intent activityIntent = new Intent(activityFrom, activityTo);
        activityFrom.startActivity(activityIntent);
    }

}
