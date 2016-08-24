package com.example.bilguun.trial;

        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.annotation.IdRes;
        import android.support.v4.content.ContextCompat;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.firebase.client.ChildEventListener;
        import com.firebase.client.DataSnapshot;
        import com.firebase.client.Firebase;
        import com.firebase.client.FirebaseError;
        import com.roughike.bottombar.BottomBar;
        import com.roughike.bottombar.BottomBarBadge;

        import java.util.ArrayList;

        import com.roughike.bottombar.BottomBar;
        import com.roughike.bottombar.BottomBarBadge;
        import com.roughike.bottombar.OnMenuTabClickListener;
public class DisplaySendSearch extends AppCompatActivity {

    private ViewPager viewPager;
    CustomSwipeAdapter adapter;
    private static int NUM_AWESOME_VIEWS = 10;
    private Context cxt;
    Button button;
    Firebase mRootRef;
    private BottomBar mBottomBar;

    private TextView tvLabel;

    private Toolbar toolbar;

    ListView tryout;
    ArrayList<String> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_send_search);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);

        //new
        tryout = (ListView)findViewById(R.id.tryout);

        button = (Button)

                findViewById(R.id.call);

        button.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+97695708911"));
                startActivity(intent);
            }
        });
        Button btn = (Button)findViewById(R.id.get_direction);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplaySendSearch.this, MapsActivity.class);
                startActivity(intent);
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
                    startActivity(new Intent(DisplaySendSearch.this, review.class));


                } else if (menuItemId == R.id.button4) {
                    updateTextView("Events");
                    startActivity(new Intent(DisplaySendSearch.this, coupons.class));


                } else if (menuItemId == R.id.button5) {
                    updateTextView("Alerts");
                    startActivity(new Intent(DisplaySendSearch.this, Login.class));


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
    protected void onStart() {
        super.onStart();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
        tryout.setAdapter(adapter);

        mRootRef = new Firebase("https://khot-gsl.firebaseio.com/restaurants/fine-dining");
        Firebase rosewood = mRootRef.child("Rosewood");
        rosewood.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String details = dataSnapshot.getValue(String.class);
                array.add(details);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                adapter.notifyDataSetChanged();
            }
        });
    }
}

