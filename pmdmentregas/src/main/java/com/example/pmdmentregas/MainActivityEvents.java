package com.example.pmdmentregas;

import com.example.pmdmentregas.entity.Paises;
import com.example.pmdmentregas.firebase.FirebaseAdmin;
import com.example.pmdmentregas.firebase.FirebaseAdminListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

/**
 * Created by tay on 11/1/18.
 */

public class MainActivityEvents implements FirebaseAdminListener, OnMapReadyCallback {
    /*
    Implementamos el lsitener OnMapReadyCallBack que tiene el métood onMapReady(ir al mapsActivity para
    ver la explicación
     */
    private MainActivity mainActivity;
    private GoogleMap mMap; //creamos referencia al googleMap del OnMapReady
    private ArrayList<Paises> arrPaises;


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

    }

    @Override
    public void downloadBranch(String branch, DataSnapshot dataSnapshot) {
        System.out.println(dataSnapshot);
        System.out.println("la rama es : " + branch);
        /*
        Por aquí pasaran todas las ramas y snapshots que obseervemos. Para elegir entre una rama u otra,
        basta con usar un if/else
         */
        if(branch.equals("Paises")){
            System.out.println("ldentro : " + branch);
            //Tenemos que usar un GenericTypeIndicator dado que firebase devuelve los datos utlizando esta clase abstracta
            GenericTypeIndicator<ArrayList<Paises>> indicator = new GenericTypeIndicator<ArrayList<Paises>>(){};
            arrPaises = dataSnapshot.getValue(indicator);//desde el value podemos castearlo al tipo que queramos, en este caso lo casteamos al genericTypeIndicator
            System.out.println("snapshoot : " + arrPaises.get(1).lat);
            /*
            Una vez descargados los datos, es decir una vez que nos hemos asegurado que los datos están ya descargados y seteados en
            el array de paises (además de que nos hemos asegurado que antes de proceder a descargar los datos se haya cargado el mapa
            en el onMapReady) entonces podemos llamara  un método que creamos que recorre este array y por cada posición entonces creo
            un objeto de la clase Paises del que sacaremos sus atributos para marcar un pin en el mapa. Este objeto no hace falta pues se puede
            setear directamente desde cada posición del array de paises los atributos es decir arrPaises.get(i).latitud por ejemplo
             */
            this.addPin();

        }
    }
    //Método para añadir los pines
    public void addPin(){
        for(int i=0; i<this.getArrPaises().size(); i++){
           // Paises pais = new Paises(this.getArrPaises().get(i).latitud,this.getArrPaises().get(i).longitud,this.getArrPaises().get(i).nombre);
            //Como podemos ver se crea una coordenada/posición
            LatLng pos = new LatLng(this.getArrPaises().get(i).lat, this.getArrPaises().get(i).lon);
            //Después se añade un marker es decir un pin/anotación en esa coordenada con un título
            if(mMap!=null){
                mMap.addMarker(new MarkerOptions().position(pos).title(this.getArrPaises().get(i).nombre));
                //Por último, se establece que la cámara que sigue el centro del mapa se colo que justo en esa coordenada que hemos creado arriba
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                //Si queremos que se haga zoom en los pines podemos elegir por ejemplo la primera posición y decir que haga zoom sobre ella
                /*
                El método en concreto es el moveCamera que a su vez funciona a través de un cameraUpdatefactory que nos permite elegir entre
                múltiples métodos, ya sea seguir con la cámara un punto o mismamente hacer zoom sobre ese punto.
                Para hacer zoom usamos el método newLatLngZoom que nos pide como parámetros la posición y la cantidad de zoom que queremos hacer
                 */
                if(i==0){
                    this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,5));
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
        /*
        Para asegurarnos que primero se carga todo el mapa y luego descargamos los datos para ir pintando los pines que
        obtenemos del snapshoot del firebase que guardamos en el array de paises, necesitamos asegurarnos que se realice
        en orden. Por tanto en el método onMapReady que solo se ejecuta cuando el mapa esta listo decimos que descarga la rama
        pues ya habrá cargado dicho mapa
         */
        this.mainActivity.getFirebaseAdmin().downloadDataAndObserveBranchChanges("Paises"); // llamo al método de descarga con la rama que quiero que observe a partir de la raíz.
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
}
