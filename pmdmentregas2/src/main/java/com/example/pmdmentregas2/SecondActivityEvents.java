package com.example.pmdmentregas2;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by tay on 18/1/18.
 */

public class SecondActivityEvents implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{
   private SecondActivity secondActivity;

    public SecondActivityEvents(SecondActivity secondActivity) {
        this.secondActivity = secondActivity;
    }
    //Detecta cuando se han presionado los botones/opciones del menú de la izquierda es decir del navigation menuu
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

        DrawerLayout drawer = (DrawerLayout) this.secondActivity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //Si presionamos el fab, se recoge desde aquí ese click
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.fab){
            Snackbar.make(view, "ESTOY DENTRO ANDA!!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show(); SecondActivity secondActivity;
        }


    }










    public SecondActivity getSecondActivity() {
        return secondActivity;
    }

    public void setSecondActivity(SecondActivity secondActivity) {
        this.secondActivity = secondActivity;
    }






}
