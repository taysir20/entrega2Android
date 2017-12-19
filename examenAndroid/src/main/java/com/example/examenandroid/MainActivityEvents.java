package com.example.examenandroid;

import android.support.v4.app.FragmentTransaction;

import com.example.mylib.fragment.LoginFragmentListener;
import com.example.mylib.fragment.RegisterFragmentListener;

/**
 * Created by tay on 18/12/17.
 */

/*
Vamos a hacer que implemente los métodos de los dragment mediante las interfaces loginFragmentlistener y registerFragmentListener
 */
public class MainActivityEvents implements LoginFragmentListener, RegisterFragmentListener { //
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

    }

    @Override
    public void OnClickRegistered() {

    }
}