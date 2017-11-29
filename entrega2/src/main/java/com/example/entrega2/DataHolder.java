package com.example.entrega2;

import com.example.entrega2.firebase.FirebaseAdmin;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by tay on 25/11/17.
 */

public class DataHolder {

    public static class MyDataHolder {
        public static FirebaseAuth firebaseAuth;

        public static FirebaseAuth getFirebaseAuth() {
            return firebaseAuth;
        }

        public static void setFirebaseAuth(FirebaseAuth firebaseAuth) {
            MyDataHolder.firebaseAuth = firebaseAuth;
        }
    }


}