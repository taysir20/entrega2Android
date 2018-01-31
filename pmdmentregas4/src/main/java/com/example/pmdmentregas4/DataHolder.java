package com.example.pmdmentregas4;

import android.app.NotificationManager;


import com.example.pmdmentregas4.SQLite.DatabaseHandler;
import com.example.pmdmentregas4.firebase.FirebaseAdmin;

/**
 * Created by tay on 25/11/17.
 */

public class DataHolder {

    public static class MyDataHolder {
        public static FirebaseAdmin firebaseAdmin;
        public static DatabaseHandler databaseHandler;
        public static String token;




        public static FirebaseAdmin getFirebaseAdmin() {
            return firebaseAdmin;
        }

        public static void setFirebaseAdmin(FirebaseAdmin firebaseAdmin) {
            MyDataHolder.firebaseAdmin = firebaseAdmin;
        }

        public static DatabaseHandler getDatabaseHandler() {
            return databaseHandler;
        }

        public static void setDatabaseHandler(DatabaseHandler databaseHandler) {
            MyDataHolder.databaseHandler = databaseHandler;
        }

        public static String getToken() {
            return token;
        }

        public static void setToken(String token) {
            MyDataHolder.token = token;
        }
    }


}