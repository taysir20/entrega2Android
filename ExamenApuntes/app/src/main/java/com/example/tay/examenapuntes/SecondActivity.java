package com.example.tay.examenapuntes;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tay.examenapuntes.fragments.InfoUsers;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


public class SecondActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {


    private FloatingActionButton fab;
    private SecondActivityEvents secondActivityEvents;
    //Variable del map fragment
    private SupportMapFragment mapFragment;
    //creamos la variable de fragment infoUsers
    private InfoUsers infoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.secondActivityEvents = new SecondActivityEvents(this);
        DataHolder.MyDataHolder.getFirebaseAdmin().setFirebaseAdminListener(secondActivityEvents);




        fab = (FloatingActionButton) findViewById(R.id.fab);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //Instanciamos la variable mapFragment con su componente es decir linkamos la parte visual del fragment mapa
        //con su código

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapUsers);
        //Se setea el escuchador del mapa que es el events del second activity pues es quién lo implementa
        mapFragment.getMapAsync(secondActivityEvents);

        //LLamamos al método de firebase que descargará los datos de la rama que especificamos
        DataHolder.MyDataHolder.getFirebaseAdmin().downloadDataAndObserveBranchChanges("Usuarios");
        //Instanciamos el infoFragment
        this.infoFragment = (InfoUsers) getSupportFragmentManager()
                .findFragmentById(R.id.infoUsers);

    //Con el fragment transaction ocultamos inicialmente el infoUser
        FragmentTransaction transition = this.getSupportFragmentManager().beginTransaction();
        transition.hide(this.getInfoFragment());
        transition.show(this.getMapFragment());
        transition.commit();
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
        getMenuInflater().inflate(R.menu.second, menu);
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

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onclick(View view) {
        if (view.getId() == R.id.fab) {

            Snackbar.make(view, "Botón fab pulsado con método linkeado", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }

    }

    public SecondActivityEvents getSecondActivityEvents() {
        return secondActivityEvents;
    }

    public void setSecondActivityEvents(SecondActivityEvents secondActivityEvents) {
        this.secondActivityEvents = secondActivityEvents;
    }

    public SupportMapFragment getMapFragment() {
        return mapFragment;
    }

    public void setMapFragment(SupportMapFragment mapFragment) {
        this.mapFragment = mapFragment;
    }

    public FloatingActionButton getFab() {
        return fab;
    }

    public void setFab(FloatingActionButton fab) {
        this.fab = fab;
    }

    public InfoUsers getInfoFragment() {
        return infoFragment;
    }

    public void setInfoFragment(InfoUsers infoFragment) {
        this.infoFragment = infoFragment;
    }
}
