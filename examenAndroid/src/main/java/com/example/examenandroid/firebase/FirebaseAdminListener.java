package com.example.examenandroid.firebase;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by tay on 19/12/17.
 */

public interface FirebaseAdminListener {
    /*
    Los métodos bolean servirán para devolver un ok a las clases que implementen esta interfaz
    de que ha ido como se esperaba.
     */
    public void loginIsOk(boolean ok);

    public void registerOk(boolean ok);

    public void signOutOk(boolean ok);

    public void downloadBranch(String branch, DataSnapshot dataSnapshot); // pasamos por parámetro la rama descargada para saber cuál es y de esta manera poder tener varias ramas de las que descargamos contenido

}