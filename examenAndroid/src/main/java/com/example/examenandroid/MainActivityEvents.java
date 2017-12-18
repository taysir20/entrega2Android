package com.example.examenandroid;

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

    }

    @Override
    public void OnLoginClicked() {

    }

    @Override
    public void OnClickRegistered() {

    }
}
