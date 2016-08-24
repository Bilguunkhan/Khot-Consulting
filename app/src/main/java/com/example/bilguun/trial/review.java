package com.example.bilguun.trial;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;
public class review extends AppCompatActivity {

    RatingBar rtn;
    TextView txt1;
    private BottomBar mBottomBar;

    private TextView tvLabel;

    private Toolbar toolbar;
    Button send;
    String [] restStartingWithR = new String[]{
            "Rosewood Kitchen + Enoteca", "Romance Restaurant", "Rock Salt Restaurant & Lounge", "Round Table Pizza", "Revo Bar & Venue"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        addListenerOnRatingBar();
        //rating bar change color
        rtn = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) rtn.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#FF41B9F6"), PorterDuff.Mode.SRC_ATOP);

        //dropdown suggestions
        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.restaurantSearch);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, restStartingWithR);
        textView.setAdapter(adapter);

        //pop-up on-click send
        send = (Button)findViewById(R.id.sendButton);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(review.this, Pop.class));
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

                } else if (menuItemId == R.id.button4) {
                    updateTextView("Events");
                    startActivity(new Intent(review.this, coupons.class));



                } else if (menuItemId == R.id.button5) {
                    updateTextView("Alerts");
                    startActivity(new Intent(review.this, Login.class));

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

    //for further database connection
    public class Review {
        private String one;
        private String two;
        private String nameOfRest;
        public Review() {}
        public Review(String to, String one, String two) {
            this.one = one;
            this.two = two;
            this.nameOfRest = to;
        }
    }

    public void addListenerOnRatingBar(){
        rtn = (RatingBar) findViewById(R.id.ratingBar);
        txt1 = (TextView)findViewById(R.id.txt1);
        rtn.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                if(rating == 1.0) {
                    txt1.setText("Маш муу");
                }
                else if(rating == 2.0){
                    txt1.setText("Mуу");
                }
                else if(rating == 3.0) {
                    txt1.setText("Дундаж");
                }
                else if(rating == 4.0) {
                    txt1.setText("Гоё");
                }
                else
                    txt1.setText("Маш гоё");
            }
        });
    }


}
