package com.example.tay.examenapuntes;



import com.example.tay.examenapuntes.SQLite.DatabaseHandler;
import com.example.tay.examenapuntes.firebase.FirebaseAdmin;

import org.json.JSONObject;

/**
 * Created by tay on 25/11/17.
 */

public class DataHolder {

    public static class MyDataHolder {
        public static FirebaseAdmin firebaseAdmin;
        public static DatabaseHandler databaseHandler;


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
    }


}