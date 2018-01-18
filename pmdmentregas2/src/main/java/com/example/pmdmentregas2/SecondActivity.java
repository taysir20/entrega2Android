package com.example.pmdmentregas2;

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

public class SecondActivity extends AppCompatActivity {
    private SecondActivityEvents secondActivityEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //Barra de tareas superior y el administrador de la barra de tareas
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //el botón flotante
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //Pintado del menú izquierdo
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //Vista de navegación encargada de la navegación de los elementos del menú de la izquierda (es el menú de la izquierda)
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
       //New del secondActivity
        this.setSecondActivityEvents(new SecondActivityEvents(this));
        //Seteamos al fab y al navigator el listener que será el second activity
        fab.setOnClickListener(this.getSecondActivityEvents()) ;
        navigationView.setNavigationItemSelectedListener(this.getSecondActivityEvents());
    }
    //Se llama cuando se presiona la pantalla de la aplicación que no sea el menú de la izquierda para poder ocultarlo si está abierto
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*
        Si está abierto el menú, decimos que se oculte
         */
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       //Infla las opciones del menú del menú settins
        getMenuInflater().inflate(R.menu.second, menu);
        return true;
    }
    //Detecta si se ha presionado el menú de settins
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




    public SecondActivityEvents getSecondActivityEvents() {
        return secondActivityEvents;
    }

    public void setSecondActivityEvents(SecondActivityEvents secondActivityEvents) {
        this.secondActivityEvents = secondActivityEvents;
    }
}
