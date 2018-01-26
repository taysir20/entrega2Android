package com.example.pmdmentregas4;

import com.example.mylib.fragment.ListFragment;
import com.example.pmdmentregas4.firebase.FirebaseAdmin;

/**
 * Created by tay on 25/11/17.
 */

public class DataHolder {

    public static class MyDataHolder {
        public static FirebaseAdmin firebaseAdmin;
        public static ListFragment listFragment;

        public static FirebaseAdmin getFirebaseAdmin() {
            return firebaseAdmin;
        }

        public static void setFirebaseAdmin(FirebaseAdmin firebaseAdmin) {
            MyDataHolder.firebaseAdmin = firebaseAdmin;
        }

    }


}