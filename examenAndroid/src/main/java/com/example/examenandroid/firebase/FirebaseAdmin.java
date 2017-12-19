package com.example.examenandroid.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by tay on 18/12/17.
 */

public class FirebaseAdmin {
    /*
    La clase FirebaseAdmin se encargará de gestionar todas las acciones relaciones con firebase (registrar usuario, loguearlo,
    descargar ramas de la base de datos...).
    Para poder manejar Firebase necesitamos importar en el gradle del módulo examenAndroid
     */
    private FirebaseAuth mAuth; // variable necesaria para la autentificación
    private FirebaseDatabase database; // para el manejo de la base de datos
    private DatabaseReference myRef; // para establecer una referencia a la bbdd ya sea a una raíz o subraíz
    private FirebaseAdminListener firebaseAdminListener; // variable de la interfaz FirebaseAdminListener


    public FirebaseAdmin() {
    //Desde el constructor del Admin vamos a llamar a un método oncreate para que instancie el FifrebaseAuth
        this.onCreate();
    }

    public void onCreate(){
        this.setmAuth(FirebaseAuth.getInstance()); // Para instanciar el Auth llamamos a la clase FirebaseAuth y realizamos un getInstance()
       // DataHolder.MyDataHolder.setFirebaseAdmin(this);
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public void setDatabase(FirebaseDatabase database) {
        this.database = database;
    }

    public DatabaseReference getMyRef() {
        return myRef;
    }

    public void setMyRef(DatabaseReference myRef) {
        this.myRef = myRef;
    }

    public FirebaseAdminListener getFirebaseAdminListener() {
        return firebaseAdminListener;
    }

    public void setFirebaseAdminListener(FirebaseAdminListener firebaseAdminListener) {
        this.firebaseAdminListener = firebaseAdminListener;
    }
}
