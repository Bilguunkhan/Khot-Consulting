package com.example.bilguun.trial;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toolbar;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

public class Login extends FragmentActivity {

    CallbackManager callbackManager;
    private BottomBar mBottomBar;

    private TextView tvLabel;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        startActivity(new Intent(Login.this, MainActivity.class));

                    }

                    @Override
                    public void onCancel() {
                        startActivity(new Intent(Login.this, Login.class));
                    }

                    @Override
                    public void onError(FacebookException exception) {
                    }
                });


        tvLabel = (TextView) findViewById(R.id.tvLabel);

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.noNavBarGoodness();

        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {

                if (menuItemId == R.id.button1) {

                    //setContentView(R.layout.home_layout);
                    updateTextView("My home page");
                } else if (menuItemId == R.id.button2) {
                    updateTextView("Favourites");

                } else if (menuItemId == R.id.button3) {
                    updateTextView("Gallery");
                    startActivity(new Intent(Login.this, review.class));


                } else if (menuItemId == R.id.button4) {
                    updateTextView("Events");
                    startActivity(new Intent(Login.this, coupons.class));


                } else if (menuItemId == R.id.button5) {
                    updateTextView("Alerts");


                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.button1) {

                }
            }
        });


        // Setting colors for different tabs when there's more than three of them.
        // You can set colors for tabs in three different ways as shown below.
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.khot_color));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.khot_color));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.khot_color));
        mBottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.khot_color));
        mBottomBar.mapColorForTab(4, ContextCompat.getColor(this, R.color.khot_color));


        // Set the color for the active tab. Ignored on mobile when there are more than three tabs.
        //  mBottomBar.setActiveTabColor("#009688");


        // mBottomBar.selectTabAtPosition(1, true);

        setNotificationBadge();

    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setNotificationBadge() {

        // Make a Badge for the first tab, with red background color and a value of "13".
        BottomBarBadge unreadMessages = mBottomBar.makeBadgeForTabAt(4, "#FF0000", 10);

        // Control the badge's visibility
        unreadMessages.show();
        //     unreadMessages.hide();

        // Change the displayed count for this badge.
        unreadMessages.setCount(4);

        // Change the show / hide animation duration.
        unreadMessages.setAnimationDuration(200);

        // If you want the badge be shown always after unselecting the tab that contains it.
        unreadMessages.setAutoShowAfterUnSelection(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }


    public void updateTextView(String text) {


        tvLabel.setText(text);
    }
}
