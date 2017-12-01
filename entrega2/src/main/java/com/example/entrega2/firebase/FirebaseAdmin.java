package com.example.entrega2.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.DatePicker;


import com.example.entrega2.DataHolder;
import com.example.entrega2.MainActivity;
import com.example.entrega2.SecondActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by tay on 25/11/17.
 */

public class FirebaseAdmin {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAdminListener firebaseAdminListener;

    public FirebaseAdmin() {

        this.onCreate();
    }

    public void onCreate() {
        this.setmAuth(FirebaseAuth.getInstance());
        DataHolder.MyDataHolder.setFirebaseAdmin(this);

    }

    public void initDataBase() {
        this.setDatabase(FirebaseDatabase.getInstance());
        this.setMyRef(database.getReference("message"));
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

    public void createUserWithEmailAndPassword(String emailAddress, String password) {
        System.out.println("Email: " + emailAddress + " Pass: " + password);
        this.getmAuth().createUserWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            getmAuth().signOut();
                            firebaseAdminListener.registerOk(true);

                        }
                    }
                });
    }


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

                            }
                        }
                    });
        }
    }


    public void logOut() {
        this.getmAuth().signOut();
        System.out.println(firebaseAdminListener);
       firebaseAdminListener.signOutOk(true);

    }

}
