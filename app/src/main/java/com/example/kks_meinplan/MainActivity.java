package com.example.kks_meinplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
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

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigatonView = findViewById(R.id.nav_view);

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
            case R.id.nav_logout:
                AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(this);
                alertDialogBuilder2.setTitle("Logout");
                alertDialogBuilder2
                        .setMessage("Möchtest du dich abmelden?")
                        .setCancelable(false)
                        .setPositiveButton("Abmelden",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        moveTaskToBack(true);
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);
                                    }//todo nachm Klicken auf "Abmelden" soll man auf die Anmelde-
                                    //activity landen. Lg fragen, ob es so geht oder ob man
                                    //button ids braucht.
                                    // https://stackoverflow.com/questions/5070618/how-to-start-an
                                    // -activity-from-a-dialog-in-android
                                })

                        .setNegativeButton("Angemeldet bleiben",
                                new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder2.create();
                alertDialog.show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("App verlassen");
            alertDialogBuilder
                    .setMessage("Möchtest du die App verlassen?")
                    .setCancelable(false)
                    .setPositiveButton("Ja",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    moveTaskToBack(true);
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            })

                    .setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }


        //todo 1 logout alert dialog
        //todo 2 switches Code ist richtig
        // aber irgendwas ist falsch vllt mit OnCreate lifecycle

    }
}