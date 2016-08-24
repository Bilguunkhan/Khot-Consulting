package com.example.bilguun.trial;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.SearchView;
import android.telecom.Call;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    final String [] locations = {"Error", "Silk Road", "Rosewood"};
    Firebase mRef;
    Button button1;
    Button button2;
    //for the bottom bar
    private BottomBar mBottomBar;
    private TextView tvLabel;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, review.class));
            }
        });*/


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
                    startActivity(new Intent(MainActivity.this, review.class));

                } else if (menuItemId == R.id.button4) {
                    updateTextView("Events");
                    startActivity(new Intent(MainActivity.this, coupons.class));


                } else if (menuItemId == R.id.button5) {
                    updateTextView("Alerts");
                    startActivity(new Intent(MainActivity.this, Login.class));

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }
    public void sendSearch(View view){
        Intent startNewActivity = new Intent(this, DisplaySendSearch.class);
        startActivity(startNewActivity);
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        // User pressed the search button
        for(int i=0; i<locations.length; i++){
            if((locations[i].equalsIgnoreCase(query) == true)){
                startActivity(new Intent(MainActivity.this, DisplaySendSearch.class));
            }
            else
                startActivity(new Intent(MainActivity.this, MainActivity.class));
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // User changed the text
        return false;
    }

}
