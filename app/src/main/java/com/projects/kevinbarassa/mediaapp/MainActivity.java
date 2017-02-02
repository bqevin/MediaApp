package com.projects.kevinbarassa.mediaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.projects.kevinbarassa.mediaapp.helper.SQLiteHandler;
import com.projects.kevinbarassa.mediaapp.helper.SessionManager;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private BottomNavigationView bottomNavigationView;
    NavigationView navigationView = null;
    Toolbar toolbar;
    private SQLiteHandler db;
    private SessionManager session;

    //Init frags
    final TopStoriesFragment topStory = new TopStoriesFragment();
    final NearMeFragment nearMe = new NearMeFragment();
    final LiveFragment live = new LiveFragment();
    final SubscriptionsFragment subscription = new SubscriptionsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fresco init
        ImagePipelineConfig config = ImagePipelineConfig
                .newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);


        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());
        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        if (savedInstanceState != null) {
            return;
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Ensure no blank screen on launch
//        openFragment(topStory);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_trending:
                                openFragment(topStory);
                                break;
                            case R.id.action_nearme:
                                openFragment(nearMe);
                                break;
                            case R.id.action_live:
                                openFragment(live);
                                break;
                            case R.id.action_subscription:
                                openFragment(subscription);
                                break;
                        }
                        return false;
                    }
                });

        /**
         * TODO: Remember to use this code with RecyclerViewer to call the custom divide decorator
         */
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
//        //or
//        recyclerView.addItemDecoration(
//                new DividerItemDecoration(getActivity(), R.drawable.divider));


    }



    //Open any Frag
    private void openFragment(final Fragment fragment)   {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        if (id == R.id.logout) {
            logoutUser();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_topstories) {
            // Handle the top stories click
            openFragment(topStory);
        } else if (id == R.id.nav_videos) {
            Intent intent = new Intent(MainActivity.this, VideosActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_nearme) {
            openFragment(nearMe);
        } else if (id == R.id.nav_music) {
            Intent intent = new Intent(MainActivity.this, MusicAlbumActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_subscriptions) {
            openFragment(subscription);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_contacts) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutUser() {
        session.setLogin(false);

        db.dropDB();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
