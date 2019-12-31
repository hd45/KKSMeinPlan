package com.example.kks_meinplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.AGsFragment;
import com.AnkündigungenFragment;
import com.EinstellungenFragment;
import com.StundenplanFragment;
import com.VertretungsplanFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private SwitchCompat switch1, switch2;
    MenuItem nav_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigatonView = findViewById(R.id.nav_view);
        nav_logout = findViewById(R.id.nav_logout);

        setSupportActionBar(toolbar);

        navigatonView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new VertretungsplanFragment()).commit();
            navigatonView.setCheckedItem(R.id.nav_vertretungsplan);
        }


        SharedPreferences preferences = getSharedPreferences("save", MODE_PRIVATE);
        switch1.setChecked(preferences.getBoolean("value", true));


        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switch1.isChecked()) {

                    SharedPreferences.Editor editor = getSharedPreferences("save",
                            MODE_PRIVATE).edit();
                    editor.putBoolean("value", true);
                    editor.apply();
                    switch1.setChecked(true);
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("save",
                            MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    switch1.setChecked(false);
                }
            }


        });

        SharedPreferences preferences2 = getSharedPreferences("save2", MODE_PRIVATE);
        switch2.setChecked(preferences2.getBoolean("value2", true));

        switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switch2.isChecked()) {

                    SharedPreferences.Editor editor = getSharedPreferences("save2",
                            MODE_PRIVATE).edit();
                    editor.putBoolean("value2", true);
                    editor.apply();
                    switch2.setChecked(true);
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("save2",
                            MODE_PRIVATE).edit();
                    editor.putBoolean("value2", false);
                    editor.apply();
                    switch2.setChecked(false);
                }
            }


        });

        nav_logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch (menuItem.getItemId()) {
            case R.id.nav_vertretungsplan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new VertretungsplanFragment()).commit();
                break;
            case R.id.nav_stundenplan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StundenplanFragment()).commit();
                break;
            case R.id.nav_ankuendigungen:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AnkündigungenFragment()).commit();
                break;
            case R.id.nav_ags:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AGsFragment()).commit();
                break;
            case R.id.nav_einstellungen:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EinstellungenFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //todo logout Anzeige ob man scih wirklich abmelden will
    //todo willst du die App wirklich verlassen
}