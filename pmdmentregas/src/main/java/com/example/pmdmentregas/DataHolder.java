package com.example.pmdmentregas;

import com.example.mylib.fragment.ListFragment;
import com.example.pmdmentregas.firebase.FirebaseAdmin;

/**
 * Created by tay on 25/11/17.
 */

public class DataHolder {

    public static class MyDataHolder {
        public static FirebaseAdmin firebaseAdmin;
        public static final String API_KEY="8be91a4bf08b6604a8b1a98633e459fb";

        public static FirebaseAdmin getFirebaseAdmin() {
            return firebaseAdmin;
        }

        public static void setFirebaseAdmin(FirebaseAdmin firebaseAdmin) {
            MyDataHolder.firebaseAdmin = firebaseAdmin;
        }


    }
}