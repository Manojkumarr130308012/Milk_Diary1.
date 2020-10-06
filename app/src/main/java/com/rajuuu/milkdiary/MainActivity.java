package com.rajuuu.milkdiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.rajuuu.milkdiary.Activity.AllLinesActivity;
import com.rajuuu.milkdiary.Activity.AllProductsActivity;
import com.rajuuu.milkdiary.Activity.LoginActivity;
import com.rajuuu.milkdiary.Activity.OrderActivity;
import com.rajuuu.milkdiary.Activity.RegisterActivity;
import com.rajuuu.milkdiary.Activity.ReportActivity;
import com.rajuuu.milkdiary.Fragment.HomeFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawerLayout);

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();
        String name = mPreferences.getString(getString(R.string.logusr), "");
        String mail = mPreferences.getString(getString(R.string.usrname), "");
        String empId = mPreferences.getString(getString(R.string.usrid), "");

//        Toast.makeText(MainActivity.this, empId, Toast.LENGTH_LONG).show();


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        // so friends my item listener is not working beacouse of some line of code i miss..
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // i miss these line so now lets check it..
        View header = navigationView.inflateHeaderView(R.layout.header);
        TextView header_usrname = header.findViewById(R.id.header_usrname);
        TextView header_usrmail = header.findViewById(R.id.header_usrmail);
        header_usrname.setText(name);
        header_usrmail.setText(mail);

        // friends now create fragments

        HomeFragment fragment = new HomeFragment();
        setTitle(getString(R.string.nav_homefragment));
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment, "Home");
        fragmentTransaction.commit();

        // so now implement onNavigationItemselected
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if (id == R.id.home) {
            HomeFragment fragment = new HomeFragment();
            setTitle(getString(R.string.nav_homefragment));
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Home");
            fragmentTransaction.commit();
        }
        else if (id == R.id.addProduct) {
            Intent i = new Intent(this, AllProductsActivity.class);
            startActivity(i);
        }
        else if (id == R.id.addLine) {
            Intent allCust = new Intent(this, AllLinesActivity.class);
            startActivity(allCust);

        }
        else if (id == R.id.order) {
            Intent ord = new Intent(this, OrderActivity.class);
            startActivity(ord);

        }

        else if (id == R.id.report) {
            Intent rept = new Intent(this, ReportActivity.class);
            startActivity(rept);

        }

        else if (id == R.id.logout) {
            Toast.makeText(MainActivity.this, "Logged Out Successfully", Toast.LENGTH_LONG).show();
            SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor spreferencesEditor = spreferences.edit();
            spreferencesEditor.clear();
            spreferencesEditor.commit();
            // Closing the current activity.
            finish();

            // Redirect to Main Login activity after log out.
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);

            startActivity(intent);
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.app_name);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setMessage("Do you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            moveTaskToBack(true);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }


}