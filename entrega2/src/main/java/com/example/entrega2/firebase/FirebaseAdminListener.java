package com.example.entrega2.firebase;

/**
 * Created by tay on 25/11/17.
 */

public interface FirebaseAdminListener {

    public void createUserWithEmailAndPassword(String emailAddress, String password);
    public void signInWithEmailAndPassword(String emailAddress, String password);
    public void logOut();
}
