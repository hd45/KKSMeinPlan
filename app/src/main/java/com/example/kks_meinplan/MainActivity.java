package com.example.kks_meinplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.AGsFragment;
import com.AnkündigungenFragment;
import com.EinstellungenFragment;
import com.StundenplanFragment;
import com.VertretungsplanFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigatonView = findViewById(R.id.nav_view);
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
}
