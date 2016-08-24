package com.example.bilguun.trial;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.firebase.client.Firebase;

/**
 * Created by bilguun on 7/29/2016.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        Firebase.setAndroidContext(this);
        AppEventsLogger.activateApp(this);
    }
}
