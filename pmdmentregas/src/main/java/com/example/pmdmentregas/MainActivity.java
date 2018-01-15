package com.example.pmdmentregas;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;


import com.example.mylib.AsyncTask.HttpAsyncTask;
import com.example.mylib.AsyncTask.HttpJsonAsyncTask;
import com.example.mylib.AsyncTask.HttpJsonAsyncTaskListener;
import com.example.mylib.GPSAdmin.GPSTrackerAdmin;
import com.example.mylib.GPSAdmin.GPSTrackerAdminListener;
import com.example.pmdmentregas.entity.Paises;
import com.example.pmdmentregas.entity.Perfiles;
import com.example.pmdmentregas.firebase.FirebaseAdmin;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity {
    private MainActivityEvents mainActivityEvents;
    private SupportMapFragment mapFragment; //creamos el SupportMapFragment que es más compatible que un MapFragment
    private InfoCiudadesFragment infoCiudadesFragment; // declaramos el fragmento de la información de las ciudades
    private Button btnTracker;
    private MostrarPosicionFragment mostrarPosicionFragment;
    private GPSTrackerAdmin gpsTrackerAdmin; // creamos la variable del GPSTrackerAdmin para poder inicializarla
    private Button btnSignOut;
    private GPSTrackerAdminListener gpsTrackerAdminListener;
    private HttpJsonAsyncTaskListener httpJsonAsyncTaskListener; //Listener para setear como escuchador de JsonAsyncTask al MainActivityEvents

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnTracker = this.findViewById(R.id.btnTracker);
        this.setMainActivityEvents(new MainActivityEvents(this));
        DataHolder.MyDataHolder.getFirebaseAdmin().setFirebaseAdminListener(this.getMainActivityEvents());
        this.btnTracker.setOnClickListener(this.getMainActivityEvents());
        this.btnTracker.setText(R.string.btnTracker);
        this.btnSignOut = this.findViewById(R.id.btnSignOut);
        this.btnSignOut.setText(R.string.btnSignOut);
        this.btnSignOut.setOnClickListener(this.getMainActivityEvents());
       /*
       Mediante el método getSpportFragemntManagr que ya conocemos pues es un método exclusivo de clases
       que extienden AppCompatActivity vamos a asignar el componente MapFragment a la variable SupportMapFragment
        */
        this.mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        this.mapFragment.getMapAsync(this.getMainActivityEvents()); // se setea el escuchador del mapa que es el events del main activity pues es quién lo implementa
        /*Con esté método getMapAsync se espera a que se hayan cargado
        todos los servicios de google maps.
        Una vez que se hayan cargado entonces se procede a llamar al mñetodo "onMapReady"
        dado que para trabajar con el mapa este debe de estar totalmente cargado.
                Este método "onMapReady" de implementa mediante el listener "OnMapReadyCallback"
         */
        this.infoCiudadesFragment = (InfoCiudadesFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentInfo);
        /*
        LLamamos a un método creado por nosotros que inicialice el GPSTracker del lib y que pregunta si
        se puede obtener la localización o no y si no se puede entonces que llame a la función de pedir permisos.
         */
        this.GPSTrackerInitialize();
        this.mostrarPosicionFragment = (MostrarPosicionFragment) getSupportFragmentManager().findFragmentById(R.id.trackerFragment);
        this.getInfoCiudadesFragment().setInfoCiudadesFragmentListener(this.getMainActivityEvents());
        this.getMostrarPosicionFragment().setMostrarPosicionFragmentListener(this.getMainActivityEvents());
        FragmentTransaction transition = this.getSupportFragmentManager().beginTransaction();
        transition.hide(this.getInfoCiudadesFragment());
        transition.hide(this.getMostrarPosicionFragment());
        transition.show(this.getMapFragment());
        transition.commit();


       /*
       Creamos una URL que envía por parámetros mediante el método get, la latitud, la longitud y la API KEY del weather API
       Se crea un nuevo objecto de tipo HttpJsonAsyncTask y se inicia la ejecución de la tarea asíncrona/hijo pasándole por
       parámetro esta URLK que recibirá el doInBackground
        */
        String url ="http://api.openweathermap.org/data/2.5/weather?lat="+ this.getGpsTrackerAdmin().getLatitude()+"&lon="+ this.getGpsTrackerAdmin().getLongitude()+"&appid="+DataHolder.MyDataHolder.API_KEY;
        HttpJsonAsyncTask httpJsonAsyncTask = new HttpJsonAsyncTask();
        httpJsonAsyncTask.setHttpJsonAsyncTaskListener(this.getMainActivityEvents());
        httpJsonAsyncTask.execute(url);



    }

    public void GPSTrackerInitialize() {
        this.setGpsTrackerAdmin(new GPSTrackerAdmin(this)); //inicializamos el GPSTrackerAdmin pasándole por parámetro el contexto que en este caso es el propio activity
        //Seteamos el escuchador/listener del GPSTracker que será en MainActivityEvents
        this.getGpsTrackerAdmin().setGpsTrackerAdminListener(this.getMainActivityEvents());
        this.getGpsTrackerAdmin().getLocation(); // la primera llamada al getLocation que se produce al hacer el new del GPSTrackerAdmin
        /*
        IMPORTANTE: El contexto para inicializarlo siempre debe de ser un activity
         */
        //Comprobamos que se puede obtener la localización en el caso contrario entonces se piden los permisos correspondientes.
        if (this.getGpsTrackerAdmin().canGetLocation()) {
            System.out.println("La localización por primera vez es: " + this.getGpsTrackerAdmin().getLatitude() + " " + this.getGpsTrackerAdmin().getLongitude());
            //creamos un método para insertar en la bbdd
            this.insertLocationFirebase(this.getGpsTrackerAdmin().getLatitude(), this.getGpsTrackerAdmin().getLongitude());
        } else {
            // Se llama a la función para pedir los permisos
            this.getGpsTrackerAdmin().showSettingsAlert();
        }
    }


    public void insertLocationFirebase(double lat, double lon) {
        if (gpsTrackerAdmin != null) {
        }
        /*
        Cuando se llama al método de envío de los datos a subir, hay que convertir esta información en un mapa a través
        del método que tenemos en nuestra entidad "toMap"
        */
        DataHolder.MyDataHolder.getFirebaseAdmin().writeNewPost("/Perfiles/", new Perfiles(lat, lon).toMap());

    }

    public MainActivityEvents getMainActivityEvents() {
        return mainActivityEvents;
    }

    public void setMainActivityEvents(MainActivityEvents mainActivityEvents) {
        this.mainActivityEvents = mainActivityEvents;
    }


    public SupportMapFragment getMapFragment() {
        return mapFragment;
    }

    public void setMapFragment(SupportMapFragment mapFragment) {
        this.mapFragment = mapFragment;
    }

    public InfoCiudadesFragment getInfoCiudadesFragment() {
        return infoCiudadesFragment;
    }

    public void setInfoCiudadesFragment(InfoCiudadesFragment infoCiudadesFragment) {
        this.infoCiudadesFragment = infoCiudadesFragment;
    }

    public Button getBtnTracker() {
        return btnTracker;
    }

    public void setBtnTracker(Button btnTracker) {
        this.btnTracker = btnTracker;
    }

    public MostrarPosicionFragment getMostrarPosicionFragment() {
        return mostrarPosicionFragment;
    }

    public void setMostrarPosicionFragment(MostrarPosicionFragment mostrarPosicionFragment) {
        this.mostrarPosicionFragment = mostrarPosicionFragment;
    }

    public GPSTrackerAdmin getGpsTrackerAdmin() {
        return gpsTrackerAdmin;
    }

    public void setGpsTrackerAdmin(GPSTrackerAdmin gpsTrackerAdmin) {
        this.gpsTrackerAdmin = gpsTrackerAdmin;
    }

    public Button getBtnSignOut() {
        return btnSignOut;
    }

    public void setBtnSignOut(Button btnSignOut) {
        this.btnSignOut = btnSignOut;
    }

    public GPSTrackerAdminListener getGpsTrackerAdminListener() {
        return gpsTrackerAdminListener;
    }

    public void setGpsTrackerAdminListener(GPSTrackerAdminListener gpsTrackerAdminListener) {
        this.gpsTrackerAdminListener = gpsTrackerAdminListener;
    }

    public HttpJsonAsyncTaskListener getHttpJsonAsyncTaskListener() {
        return httpJsonAsyncTaskListener;
    }

    public void setHttpJsonAsyncTaskListener(HttpJsonAsyncTaskListener httpJsonAsyncTaskListener) {
        this.httpJsonAsyncTaskListener = httpJsonAsyncTaskListener;
    }
}
