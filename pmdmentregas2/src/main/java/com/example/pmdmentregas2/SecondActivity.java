package com.example.pmdmentregas2;

import android.media.Image;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class SecondActivity extends AppCompatActivity {
    private SecondActivityEvents secondActivityEvents;
    private DrawerLayout drawer;
    private TextView txtID;
    private TextView txtNombre;
    private TextView txtApellido;
    private TextView txtEdad;
    private TextView txtCiudad;
    private TextView txtEmail;
    private ImageView imgPerfil;
    private TextView txtNombrePerfil;
    private TextView txtEmailPerfil;
    private NavigationView navigationView;
    private LinearLayout linearLayout;


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
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*
        El toggle sirve para realziar la transición del navigation bar de izquierda a derecha y se definen dos estados,
        si está abierto o cerrado
         */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //Vista de navegación encargada de la navegación de los elementos del menú de la izquierda (es el menú de la izquierda)
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //New del secondActivity
        this.setSecondActivityEvents(new SecondActivityEvents(this));
        //Seteamos al fab y al navigator el listener que será el second activity
        fab.setOnClickListener(this.getSecondActivityEvents());
        navigationView.setNavigationItemSelectedListener(this.getSecondActivityEvents());
        this.txtID = (TextView) this.findViewById(R.id.txtID);
        this.txtNombre = (TextView) this.findViewById(R.id.txtNombre);
        this.txtApellido = (TextView) this.findViewById(R.id.txtApellido);
        this.txtEdad = (TextView) this.findViewById(R.id.txtEdad);
        this.txtCiudad = (TextView) this.findViewById(R.id.txtCiudad);
        this.txtEmail = (TextView) this.findViewById(R.id.txtEmail);
        this.navigationView = (NavigationView) this.findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        this.imgPerfil = (ImageView) headerView.findViewById(R.id.imgPerfil);
        this.txtNombrePerfil = (TextView) headerView.findViewById(R.id.txtPerfil_Nombre);
        this.txtEmailPerfil = (TextView) headerView.findViewById(R.id.txtPerfil_Email);
        this.setearDatos();

    }

    public void setearDatos() {
        JSONObject jsonObject = DataHolder.MyDataHolder.jsonObject;
        try {
            this.txtID.setText(jsonObject.getJSONObject("alumno").get("id").toString());
            this.txtNombre.setText(jsonObject.getJSONObject("alumno").get("nombre").toString());
            this.txtApellido.setText(jsonObject.getJSONObject("alumno").get("apellido").toString());
            this.txtEdad.setText(jsonObject.getJSONObject("alumno").get("edad").toString());
            this.txtCiudad.setText(jsonObject.getJSONObject("alumno").get("ciudad").toString());
            this.txtEmail.setText(jsonObject.getJSONObject("alumno").get("mail").toString());
            this.txtNombrePerfil.setText(jsonObject.getJSONObject("alumno").get("nombre").toString());
            this.txtEmailPerfil.setText(jsonObject.getJSONObject("alumno").get("mail").toString());

            Glide.with(this.getBaseContext()).load(jsonObject.getJSONObject("alumno").get("img").toString()).into(this.imgPerfil);
            // Glide.with(this.getBaseContext()).load("https://iosscripts.com/iosslider/_img/h-slider-5.jpg").into(this.imgFondoPerfil);

        } catch (JSONException e) {

        }

    }

    //Se llama cuando se presiona el botón de android de atrás y así el menú de la izquierda para poder ocultarlo si está abierto
    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        //Le pasamos el xml de los elementos es decir opciones que queremos que se pinten/inflen dentro de settins
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

    public DrawerLayout getDrawer() {
        return drawer;
    }

    public void setDrawer(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    public TextView getTxtID() {
        return txtID;
    }

    public void setTxtID(TextView txtID) {
        this.txtID = txtID;
    }

    public TextView getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(TextView txtNombre) {
        this.txtNombre = txtNombre;
    }

    public TextView getTxtApellido() {
        return txtApellido;
    }

    public void setTxtApellido(TextView txtApellido) {
        this.txtApellido = txtApellido;
    }

    public TextView getTxtEdad() {
        return txtEdad;
    }

    public void setTxtEdad(TextView txtEdad) {
        this.txtEdad = txtEdad;
    }

    public TextView getTxtCiudad() {
        return txtCiudad;
    }

    public void setTxtCiudad(TextView txtCiudad) {
        this.txtCiudad = txtCiudad;
    }

    public TextView getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(TextView txtEmail) {
        this.txtEmail = txtEmail;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }
}
