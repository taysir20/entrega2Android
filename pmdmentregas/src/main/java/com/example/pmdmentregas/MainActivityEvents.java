package com.example.pmdmentregas;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.mylib.AsyncTask.HttpJsonAsyncTask;
import com.example.mylib.AsyncTask.HttpJsonAsyncTaskListener;
import com.example.mylib.GPSAdmin.GPSTrackerAdminListener;
import com.example.pmdmentregas.entity.Paises;
import com.example.pmdmentregas.entity.Perfiles;
import com.example.pmdmentregas.firebase.FirebaseAdminListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tay on 11/1/18.
 */

public class MainActivityEvents implements FirebaseAdminListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener, InfoCiudadesFragmentListener, View.OnClickListener, MostrarPosicionFragmentListener, GPSTrackerAdminListener, HttpJsonAsyncTaskListener {


    //Para implementar el onlick de los markers y realizar acciones es decir que detecte cuando hemos pinchado un pon usamos OnMarkerClickListener
    /*
    Implementamos el lsitener OnMapReadyCallBack que tiene el métood onMapReady(ir al mapsActivity para
    ver la explicación
     */
    private MainActivity mainActivity;
    private GoogleMap mMap; //creamos referencia al googleMap del OnMapReady
    private ArrayList<Paises> arrPaises;
    private HashMap<String, Perfiles> hashMapPerfiles;
    private GoogleMap mapPosition;


    public MainActivityEvents(MainActivity mainActivity) {

        this.mainActivity = mainActivity;


    }

    public MainActivity getMainActivity() {

        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void loginIsOk(boolean ok) {

    }

    @Override
    public void registerOk(boolean ok) {

    }

    @Override
    public void signOutOk(boolean ok) {
        if (ok) {
            Intent intent = new Intent(this.getMainActivity(), LoginActivity.class);
            this.getMainActivity().startActivity(intent);
            this.getMainActivity().finish();
        }

    }

    @Override
    public void downloadBranch(String branch, DataSnapshot dataSnapshot) {
        if(dataSnapshot!=null){
            System.out.println(dataSnapshot);
            System.out.println("la rama es : " + branch);
        /*
        Por aquí pasaran todas las ramas y snapshots que obseervemos. Para elegir entre una rama u otra,
        basta con usar un if/else
         */
            if (branch.equals("Paises")) {
            /*
            Cuando descargamos la rama llamaremos al método removePin() que se encargará de eliminar los pines que ya están escritos.
            Esto lo hacemos porque como sabemos el método que descarga la rama de firebase esta observando continumanete posibles
            cambios que sucedan en la BBDD. Si cambiaramos en tiempo real algunas de las coordenadas en la bbdd se volvería a descargar
            la rama y se sobreescribiría el array de Países.
            Lo que pasa es que los pines se escriben a posteriori de la descarga dado que no se almacenan en la bbdd, sino que se setea
            cada país al pin después de descargasrse y por lo tanto los pines pintados ya en el mapa no se sobreescriben porque no se borran.
            Por eso llamaremos a este método que comprueba que si el pin ya está pintado y al descargarse de nuevo la rama se ha modificado
            la posición de este pin entonces que lo borre dado que eñ método addPin volverá a pintarlo en su nueva posición.
             */
                this.removePin("Paises");
                System.out.println("ldentro : " + branch);
                //Tenemos que usar un GenericTypeIndicator dado que firebase devuelve los datos utlizando esta clase abstracta
                GenericTypeIndicator<ArrayList<Paises>> indicator = new GenericTypeIndicator<ArrayList<Paises>>() {
                };
                arrPaises = dataSnapshot.getValue(indicator);//desde el value podemos castearlo al tipo que queramos, en este caso lo casteamos al genericTypeIndicator
                System.out.println("snapshoot : " + arrPaises.get(1).lat);
            /*
            Una vez descargados los datos, es decir una vez que nos hemos asegurado que los datos están ya descargados y seteados en
            el array de paises (además de que nos hemos asegurado que antes de proceder a descargar los datos se haya cargado el mapa
            en el onMapReady) entonces podemos llamara  un método que creamos que recorre este array y por cada posición entonces creo
            un objeto de la clase Paises del que sacaremos sus atributos para marcar un pin en el mapa. Este objeto no hace falta pues se puede
            setear directamente desde cada posición del array de paises los atributos es decir arrPaises.get(i).latitud por ejemplo
             */
                this.addPin("Paises");

            } else if (branch.equals("Perfiles/" + DataHolder.MyDataHolder.getFirebaseAdmin().getmAuth().getCurrentUser().getUid())) {
                System.out.println("¡¡¡¡¡¡¡¡¡¡¡Mostrar error 2 !!!!!!!!!!!!!!!!!!!");
                this.removePin("Perfiles/" + DataHolder.MyDataHolder.getFirebaseAdmin().getmAuth().getCurrentUser().getUid());
                System.out.println("ldentro : " + branch);
                //Tenemos que usar un GenericTypeIndicator dado que firebase devuelve los datos utlizando esta clase abstracta
                GenericTypeIndicator<Perfiles> indicator = new GenericTypeIndicator<Perfiles>() {
                };
                System.out.println("El snapshoot de perfiles: " + dataSnapshot.getValue());
                Perfiles perfil= dataSnapshot.getValue(indicator);
                System.out.println("el perfil casteado es " + perfil);
                hashMapPerfiles = new HashMap<String,Perfiles>();
                hashMapPerfiles.put(dataSnapshot.getKey(), perfil);//desde el value podemos castearlo al tipo que queramos, en este caso lo casteamos al genericTypeIndicator
                // System.out.println("snapshoot : " + hashMapPerfiles.get(DataHolder.MyDataHolder.getFirebaseAdmin().getmAuth().getCurrentUser().getUid()));
                this.addPin("Perfiles/" + DataHolder.MyDataHolder.getFirebaseAdmin().getmAuth().getCurrentUser().getUid());
                //Actualización de los datos del tiempo
                String url ="http://api.openweathermap.org/data/2.5/weather?lat="+ this.getMainActivity().getGpsTrackerAdmin().getLatitude()+"&lon="+ this.getMainActivity().getGpsTrackerAdmin().getLongitude()+"&appid="+DataHolder.MyDataHolder.API_KEY;
                HttpJsonAsyncTask httpJsonAsyncTask = new HttpJsonAsyncTask();
                httpJsonAsyncTask.setHttpJsonAsyncTaskListener(this.getMainActivity().getMainActivityEvents());
                httpJsonAsyncTask.execute(url);
            }
        }

    }

    //Método para añadir los pines
    public void addPin(String branch) {

        if (branch.equals("Paises")) {
            for (int i = 0; i < this.getArrPaises().size(); i++) {
                //Como podemos ver se crea una coordenada/posición
                LatLng pos = new LatLng(this.getArrPaises().get(i).lat, this.getArrPaises().get(i).lon);
                //Después se añade un marker es decir un pin/anotación en esa coordenada con un título
                if (mMap != null) {
                    this.getArrPaises().get(i).setMarker(mMap.addMarker(new MarkerOptions().position(pos).title(this.getArrPaises().get(i).nombre))); //guardamos el marker para luego poder acceder a él para poder borrarlo
                    this.getArrPaises().get(i).getMarker().setTag(this.getArrPaises().get(i));
                    //Añádimos un tag para cada pin que concretamente será el propio objeto es decir el propio país
                /*

                 */
                    //Por último, se establece que la cámara que sigue el centro del mapa se colo que justo en esa coordenada que hemos creado arriba
                    //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                    //Si queremos que se haga zoom en los pines podemos elegir por ejemplo la primera posición y decir que haga zoom sobre ella
                /*
                El método en concreto es el moveCamera que a su vez funciona a través de un cameraUpdatefactory que nos permite elegir entre
                múltiples métodos, ya sea seguir con la cámara un punto o mismamente hacer zoom sobre ese punto.
                Para hacer zoom usamos el método newLatLngZoom que nos pide como parámetros la posición y la cantidad de zoom que queremos hacer
                 */
                    if (i == 0) {
                        this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 5));
                    }

                }

            }
        } else if (branch.equals("Perfiles/" + DataHolder.MyDataHolder.getFirebaseAdmin().getmAuth().getCurrentUser().getUid())) {
            for (Map.Entry<String, Perfiles> entry : hashMapPerfiles.entrySet()) {
                LatLng pos = new LatLng(entry.getValue().lat, entry.getValue().lon);
                if (mapPosition != null) {
                    entry.getValue().setMarker(mapPosition.addMarker(new MarkerOptions().position(pos).title("Tú posición"))); //guardamos el marker para luego poder acceder a él para poder borrarlo
                    entry.getValue().getMarker().setTag(entry.getValue());
                    this.mapPosition.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 17));


                }


            }

        }


    }
    /*
    Método de removePin, se comprueba que el mapa no sea null y cuando se recorren los arrays que el marker no sea null, si no es null
    es porque ya existe pintado y se tiene que borrar y si es null es porque es la primera vez que se pinta
     */

    public void removePin(String branch) {

        if (branch.equals("Paises")) {
            if (this.getArrPaises() != null) {
                for (int i = 0; i < this.getArrPaises().size(); i++) {
                    if (this.getArrPaises().get(i).getMarker() != null) {
                        System.out.println("borrado???????");
                        this.getArrPaises().get(i).getMarker().remove();
                    }

                }
            }
        } else if (branch.equals("Perfiles/" + DataHolder.MyDataHolder.getFirebaseAdmin().getmAuth().getCurrentUser().getUid())) {
            if (this.getHashMapPerfiles() != null) {

                for (Map.Entry<String, Perfiles> entry : hashMapPerfiles.entrySet()) {

                    if (entry.getValue().getMarker() != null) {
                        System.out.println("borrado???????");
                        entry.getValue().getMarker().remove();
                    }


                }


            }
        }


    }

    /*
    Método que solo se ejecuta cuando el mapa está totalmente listo
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);//decimos al mapa cuál es su escuchador para cuando pulsemos los pines
        /*
        Para asegurarnos que primero se carga todo el mapa y luego descargamos los datos para ir pintando los pines que
        obtenemos del snapshoot del firebase que guardamos en el array de paises, necesitamos asegurarnos que se realice
        en orden. Por tanto en el método onMapReady que solo se ejecuta cuando el mapa esta listo decimos que descarga la rama
        pues ya habrá cargado dicho mapa
         */

        DataHolder.MyDataHolder.getFirebaseAdmin().downloadDataAndObserveBranchChanges("Paises"); // llamo al método de descarga con la rama que quiero que observe a partir de la raíz.
        /*// Add a marker in Sydney and move the camera
        //Como podemos ver se crea una coordenada/posición
        LatLng sydney = new LatLng(-34, 151);
        //Después se añade un marker es decir un pin/anotación en esa coordenada con un título
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //Po último, se establece que la cámara que sigue el centro del mapa se colo que justo en esa coordenada que hemos creado arriba
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        */
    }

    public GoogleMap getmMap() {
        return mMap;
    }

    public void setmMap(GoogleMap mMap) {
        this.mMap = mMap;
    }

    public ArrayList<Paises> getArrPaises() {
        return arrPaises;
    }

    public void setArrPaises(ArrayList<Paises> arrPaises) {
        this.arrPaises = arrPaises;
    }

    /*
    Método implementado por el listener OnMarkerClickListener que entra cuando hemos pìnchado un pin
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        /*
        Aquí devolvemos para el pin marcado su correspondiente tag asignado
         */

        Paises pais = (Paises) marker.getTag();//Primero casteamos el tag correspondiente al objeto país
        //Ahora ya podremos acceder a los atributos de ese país
        //Llamamos al método de InfoCiudadesFragment que creamos para setear cada objeto pais que pasamos por parámetro
        this.getMainActivity().getInfoCiudadesFragment().setContenido(pais);
        FragmentTransaction transition = this.getMainActivity().getSupportFragmentManager().beginTransaction();
        transition.show(this.getMainActivity().getInfoCiudadesFragment());
        transition.hide(this.getMainActivity().getMapFragment());
        this.getMainActivity().getBtnTracker().setVisibility(this.getMainActivity().getBtnTracker().GONE);
        this.getMainActivity().getBtnSignOut().setVisibility(this.getMainActivity().getBtnTracker().GONE);
        transition.commit();

        return false;
    }


    @Override
    public void pressBack() {
        FragmentTransaction transition = this.getMainActivity().getSupportFragmentManager().beginTransaction();
        transition.hide(this.getMainActivity().getInfoCiudadesFragment());
        transition.show(this.getMainActivity().getMapFragment());
        this.getMainActivity().getBtnTracker().setVisibility(this.getMainActivity().getBtnTracker().VISIBLE);
        this.getMainActivity().getBtnSignOut().setVisibility(this.getMainActivity().getBtnTracker().VISIBLE);
        transition.commit();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnTracker) {
            FragmentTransaction transition = this.getMainActivity().getSupportFragmentManager().beginTransaction();
            transition.hide(this.getMainActivity().getMapFragment());
            this.getMainActivity().getBtnTracker().setVisibility(view.GONE);
            this.getMainActivity().getBtnSignOut().setVisibility(this.getMainActivity().getBtnTracker().GONE);
            transition.show(this.getMainActivity().getMostrarPosicionFragment());
            transition.commit();

        } else if (view.getId() == R.id.btnSignOut) {
            this.getMainActivity().getGpsTrackerAdmin().stopUsingGPS();
            this.getMainActivity().getMostrarPosicionFragment().getMostrarPosicionFragmentEvents().onMapReady(null);
            DataHolder.MyDataHolder.getFirebaseAdmin().getMyChildRef().removeEventListener(DataHolder.MyDataHolder.getFirebaseAdmin().getValueEventListene());
            DataHolder.MyDataHolder.getFirebaseAdmin().getMyRef().removeEventListener(DataHolder.MyDataHolder.getFirebaseAdmin().getValueEventListene());
            DataHolder.MyDataHolder.getFirebaseAdmin().logOut();
        }
    }

    @Override
    public void ocultarMostrarPoscionFragment() {
        FragmentTransaction transition = this.getMainActivity().getSupportFragmentManager().beginTransaction();
        transition.hide(this.getMainActivity().getMostrarPosicionFragment());
        this.getMainActivity().getBtnTracker().setVisibility(this.getMainActivity().getBtnTracker().VISIBLE);
        this.getMainActivity().getBtnSignOut().setVisibility(this.getMainActivity().getBtnTracker().VISIBLE);
        transition.show(this.getMainActivity().getMapFragment());
        transition.commit();
    }

    @Override
    public void setMapPosition(GoogleMap mapPosition) {
        this.mapPosition = mapPosition;
    }

    //Cada vez que se reciba un true entonces se volverá a llamar a la función que iserta/actualiza del FirebaseAdmin
    @Override
    public void firebaseLocationUpdate(boolean ok) {
        if(this.getMainActivity().getGpsTrackerAdmin()!=null){
            if (ok == true) {
                if(this.getMainActivity().getGpsTrackerAdmin()!=null){
                    if (this.getMainActivity().getGpsTrackerAdmin().canGetLocation()) {
                        System.out.println("La localización actualizada es: " + this.getMainActivity().getGpsTrackerAdmin().getLatitude() + " " + this.getMainActivity().getGpsTrackerAdmin().getLongitude());
                        //creamos un método para insertar en la bbdd
                        this.getMainActivity().insertLocationFirebase(this.getMainActivity().getGpsTrackerAdmin().getLatitude(), this.getMainActivity().getGpsTrackerAdmin().getLongitude());
                    } else {
                        // Se llama a la función para pedir los permisos
                        this.getMainActivity().getGpsTrackerAdmin().showSettingsAlert();
                    }
                }

            }
        }



    }

    public HashMap<String, Perfiles> getHashMapPerfiles() {
        return hashMapPerfiles;
    }

    public void setHashMapPerfiles(HashMap<String, Perfiles> hashMapPerfiles) {
        this.hashMapPerfiles = hashMapPerfiles;
    }

    @Override
    public void FinalTasknotification(Object object) {
        System.out.println("El resultado del onPostExecute----------------------------------------->>>>>>>>>> " + object);
        try {
            //JsonObject total que contiene todo
            JSONObject jsonObject = new JSONObject(String.valueOf(object));
            //JsonArray del weather que contiene a su vez objects
            JSONArray jArrayWeather = jsonObject.getJSONArray("weather");
            //JsonObject del Main que contiene a su vez objects
            JSONObject jObjectMain = jsonObject.getJSONObject("main");
            this.getMainActivity().getMostrarPosicionFragment().getTxtLocation().setText(jsonObject.get("name").toString());
            this.getMainActivity().getMostrarPosicionFragment().getTxtTiempo().setText(jArrayWeather.getJSONObject(0).get("main").toString());
            this.getMainActivity().getMostrarPosicionFragment().getTxtTemperatura().setText(jObjectMain.get("temp").toString());
            this.getMainActivity().getMostrarPosicionFragment().getTxtHumedad().setText(jObjectMain.get("humidity").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
