package com.example.examenandroid;

import com.example.examenandroid.firebase.FirebaseAdmin;
import com.example.mylib.fragment.ListFragment;

/**
 * Created by tay on 19/12/17.
 */

public class DataHolder {
    public static class MyDataHolder {
        public static FirebaseAdmin firebaseAdmin;

        public static FirebaseAdmin getFirebaseAdmin() {
            return firebaseAdmin;
        }

        public static void setFirebaseAdmin(FirebaseAdmin firebaseAdmin) {
            MyDataHolder.firebaseAdmin = firebaseAdmin;
        }

    }

}
