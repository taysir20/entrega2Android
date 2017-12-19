package com.example.examenandroid;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;

import com.example.examenandroid.firebase.FirebaseAdminListener;
import com.example.mylib.fragment.LoginFragmentListener;
import com.example.mylib.fragment.RegisterFragmentListener;
import com.google.firebase.database.DataSnapshot;

/**
 * Created by tay on 18/12/17.
 */

/*
Vamos a hacer que implemente los métodos de los dragment mediante las interfaces loginFragmentlistener y registerFragmentListener
Añadimos el implements del FirebaseAdminListener pues el mainActivityEvents recibirá las notificaciones de ok del firebaseAmin
 */
public class MainActivityEvents implements LoginFragmentListener, RegisterFragmentListener, FirebaseAdminListener{ //

    /*
    Declaramos y recibimos por parámetro la variable de tipo MainActivity pues ciertas acciones,
    requerirán de la llamada de esta activity
     */

    private MainActivity mainActivity;

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
    public void OnRegisteredClicked() {
    /*
    Esté método implementado de la interfaz del LoginFragment recibe las llamadas desde el onclick del
    events del LoginFragment
     */
    /*
    De nuevo, necesitaremos un FragmentTransition para administrar los fragments, al pinchar sobre el botón de registro
    desde el mainActivity, dicho botón llama al método onclick del LoginFragmentEvents y dentro de este método se llama
    a quién implementa los métodos de la interfaz de loginFragmentListener. Este MainacxtivityEvents implementa este método
    y por lo tanto se llama a este método en el que nos encontramos y realizamos la transición inversa es decir si al principio
    mostrabamos el loginFragment y ocultabamos el registerFragment, ahora lo hacmos al revés para que muestre el fragment del
    registro.
    ¿Por qué el MainActivityEvents se encarga de llevar a cabo en última instancia todos los eventos de los fragments?
    Basicamente, para no limitarnos al uso del los events de los fragments y de esta manera reciclar estos fragmentos desde
    la librería pues si los fragments se encargan de realizar los eventos entonces solo valdrían para ese fragment.
     */
        FragmentTransaction transition = mainActivity.getSupportFragmentManager().beginTransaction();
        transition.hide(this.getMainActivity().getLoginFragment());
        transition.show(this.getMainActivity().getRegisterFragment());
        transition.commit(); // comiteamos para que se lleve  a cambo la transacción
    }

    @Override
    public void OnLoginClicked() {
        //Recoge el email y password del loginFragment y se lo envía al registerUser del frirebaseAdmin
        this.mainActivity.getFirebaseAdmin().signInWithEmailAndPassword(this.mainActivity.getLoginFragment().getTxtEmail().getText().toString(), this.mainActivity.getLoginFragment().getTxtPass().getText().toString());
    }

    @Override
    public void OnClickRegistered() {
        //Recoge el email y password del registerfragment y se lo envía al createUser del frirebaseAdmin
        this.mainActivity.getFirebaseAdmin().createUserWithEmailAndPassword(this.mainActivity.getRegisterFragment().getTxtEmail().getText().toString(), this.mainActivity.getRegisterFragment().getTxtPass().getText().toString());
    }

    @Override
    public void loginIsOk(boolean ok) {
        //si llega un ok=true desde el registerUser de firebaseadmin se realiza un intent para pasar del mainActivity al secondActivity
        //Matando el mainActivity
        if(ok){
            Intent intent = new Intent(this.getMainActivity(), SecondActivity.class);
            this.getMainActivity().startActivity(intent);
            this.getMainActivity().finish();

        }

    }

    @Override
    public void registerOk(boolean ok) {
        System.out.println("pruebbaaaaaaaaaaaaaa");
        /*
        Si llega un true deasde el método de registro del firebaseAdmin entonces a través del gestor de fragmentos
        vamos a realizar la transición del registerFragment al loginFragment
        Recordar que hay que realizar el commit siemrpe apra completar la transacción!
         */
        if(ok){
            FragmentTransaction transition = mainActivity.getSupportFragmentManager().beginTransaction();
            transition.hide(this.getMainActivity().getRegisterFragment());
            transition.show(this.getMainActivity().getLoginFragment());
            transition.commit();
        }

    }

    @Override
    public void signOutOk(boolean ok) {

    }

    @Override
    public void downloadBranch(String branch, DataSnapshot dataSnapshot) {

    }
}
