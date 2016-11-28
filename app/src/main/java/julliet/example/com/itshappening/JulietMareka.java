package julliet.example.com.itshappening;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

import julliet.example.com.itshappening.helper.SQLiteHandler;
import julliet.example.com.itshappening.helper.SessionManager;

public class JulietMareka extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView txtName;
    private TextView txtEmail;
    private ImageButton btnLogout;

    private SQLiteHandler db;
    private SessionManager session;
    private Toolbar mToolbar;

    ImageButton btnViewevents;
    ImageButton btnNewProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juliet_mareka);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (ImageButton) findViewById(R.id.btnLogout);
        btnViewevents = (ImageButton) findViewById(R.id.btnViewevents);
        btnNewProduct = (ImageButton) findViewById(R.id.btnCreateEvent);

        btnViewevents.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching All events Activity
                Intent i = new Intent(getApplicationContext(), AllEventsActivity.class);
                startActivity(i);

            }
        });

        btnNewProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching create new product activity
                Intent i = new Intent(getApplicationContext(), NewEventActivity.class);
                startActivity(i);

            }
        });

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }
        HashMap<String, String> user = db.getUserDetails();
        String name = user.get("name");
        String email = user.get("email");
        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




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
        getMenuInflater().inflate(R.menu.juliet_mareka, menu);
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

        return super.onOptionsItemSelected(item);
    }
//supressWarnings
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_recently) {
            Intent recentlyintent1 = new Intent(this,AllEventsActivity.class);
            startActivity(recentlyintent1);
        } else if (id == R.id.nav_categories) {
            Intent recentlyintent = new Intent(this,Categories1.class);
            startActivity(recentlyintent);
        } else if (id == R.id.nav_twitter) {

        } else if (id == R.id.nav_maps) {
            Intent mapsintent = new Intent(this,MapsActivity.class);
            startActivity(mapsintent);
        } else if (id == R.id.nav_favorites) {

        } else if (id == R.id.nav_admin) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(JulietMareka.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
