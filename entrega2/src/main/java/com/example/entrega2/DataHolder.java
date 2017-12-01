package com.example.entrega2;

import com.example.entrega2.firebase.FirebaseAdmin;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by tay on 25/11/17.
 */

public class DataHolder {

    public static class MyDataHolder {
        public static FirebaseAdmin firebaseAdmin;
        //private static FirebaseDatabase firebaseDatabase;

        public static FirebaseAdmin getFirebaseAdmin() {
            return firebaseAdmin;
        }

        public static void setFirebaseAdmin(FirebaseAdmin firebaseAdmin) {
            MyDataHolder.firebaseAdmin = firebaseAdmin;
        }
        /*
        public static FirebaseDatabase getFirebaseDatabase() {
            return firebaseDatabase;
        }

        public static void setFirebaseDatabase(FirebaseDatabase firebaseDatabase) {
            MyDataHolder.firebaseDatabase = firebaseDatabase;
        }
        */
    }


}