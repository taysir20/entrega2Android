package com.example.examenandroid.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.examenandroid.DataHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    //variables aux que he creado para el cierre de sesión
    private  DatabaseReference myChildRef;
    private ValueEventListener valueEventListene=null;

    public FirebaseAdmin() {
    //Desde el constructor del Admin vamos a llamar a un método oncreate para que instancie el FifrebaseAuth
        this.onCreate();
    }

    public void onCreate(){
        this.setmAuth(FirebaseAuth.getInstance()); // Para instanciar el Auth llamamos a la clase FirebaseAuth y realizamos un getInstance()
       DataHolder.MyDataHolder.setFirebaseAdmin(this); // guardamos el firebaseAdmin en el dataholder para poder volver a usarlo en otros activitys
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
/*
Con este método Firebase creará un usuario, el email y password que reciba.
Lo hará desde el onclickregistered que pide los datos
al registerfragment.
Si la creación de los usuario se ha podido realizar, entonces llamará a quién haya implementado el método del firebaseAdminListener
en este caso MainAxctivityEvents devolviendole un true. Este se encargará entonces de realizar la transición de nuevo al loginfragment
 si ha recibido un true
 */
    public void createUserWithEmailAndPassword(String emailAddress, String password) {
        System.out.println("Email: " + emailAddress + " Pass: " + password);
        this.getmAuth().createUserWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.v("prueba","---->>" + task.getResult());
                        if (task.isSuccessful()) {
                            getmAuth().signOut(); // como no quiero que me loguee cuando estoy registrado, hago un CIERRE DE SESIÓN
                            firebaseAdminListener.registerOk(true);

                        }
                    }
                });
    }
/*
Método para loguearnos, funciona de manera similar al registro, recibe el email y password y devuelve
un true. Este true lo recogerá el  mainActivityEvents y realizará la transición al second Activity.
 */
    public void signInWithEmailAndPassword(String emailAddress, String password) {
        System.out.println("EmailAcceso: " + emailAddress + " PassAcceso: " + password);
        if (this.getmAuth().getCurrentUser() != null) {
            // Already signed in
            // Do nothing
            System.out.println("    ESTOY LOGUEADO!!!!!!!!!");
            firebaseAdminListener.loginIsOk(true);
        } else {
            this.getmAuth().signInWithEmailAndPassword(emailAddress, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            System.out.println("ACABO DE LOGUEARME!!!!!!!!");
                            if (task.isSuccessful()) {

                                System.out.println("dentroDelOnComplete");

                                firebaseAdminListener.loginIsOk(true);
                                // initDataBase();

                            }
                        }
                    });
        }
    }


    public void logOut() {
        //myChildRef.removeEventListener(valueEventListene);
        this.getmAuth().signOut();
        firebaseAdminListener.signOutOk(true);
        System.out.println(firebaseAdminListener);


    }

}
