package com.appsnipp.education;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.appsnipp.education.databinding.ActivityMainBinding;
import com.appsnipp.education.ui.fragments.CoursesStaggedFragment;
import com.appsnipp.education.ui.fragments.HomeCoursesFragment;
import com.appsnipp.education.ui.fragments.MatchesCoursesFragment;
import com.appsnipp.education.ui.helpers.BottomNavigationBehavior;
import com.appsnipp.education.ui.helpers.DarkModePrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //private BottomNavigationView bottomNavigationView;


    ActivityMainBinding binding;
//    NavHostFragment navHostFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            Fragment fragmentoGenerico = null;
            Intent intentGetStarted;
            switch (item.getItemId()) {
                case R.id.navigationMyProfile:
//                    return true;
                    break;
                case R.id.navigationMyCourses:
                    //https://dribbble.com/shots/6482664-Design-Course-App-UI
                    fragmentoGenerico = new CoursesStaggedFragment();
                    break;
                case R.id.navigationHome:
                    fragmentoGenerico = new HomeCoursesFragment();
                    break;
                case R.id.navigationSearch:

                    fragmentoGenerico = new MatchesCoursesFragment();
                    break;
                case R.id.navigationMenu:
                    binding.drawerLayout.openDrawer(GravityCompat.START);
                    break;
            }
            if (fragmentoGenerico != null) {
//                fragmentManager
//                        .beginTransaction()
//                        .replace(R.id.container, fragmentoGenerico)
//                        .commit();
                loadFragment(fragmentoGenerico);
            }

            setTitle(item.getTitle());
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (new DarkModePrefManager(this).isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(binding.appBarMain.toolbar);


//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.appBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(this);

        //region REGION OLD METHOD BOTTOM NAVIGATION
        binding.appBarMain.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) binding.appBarMain.bottomNavigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
        binding.appBarMain.bottomNavigation.setSelectedItemId(R.id.navigationHome);
        //endregion

        //setupNavigation();

    }

//    private void setupNavigation() {
//        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
//    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(
                R.anim.fragment_fade_enter,  // enter
                R.anim.fragment_fade_exit,  // exit
                R.anim.fragment_fade_enter,   // popEnter
                R.anim.fragment_fade_exit  // popExit
        );

        transaction.replace(R.id.container_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_dark_mode) {
            //code for setting dark mode
            //true for dark mode, false for day mode, currently toggling on each click
            DarkModePrefManager darkModePrefManager = new DarkModePrefManager(this);
            darkModePrefManager.setDarkMode(!darkModePrefManager.isNightMode());
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            recreate();

        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
