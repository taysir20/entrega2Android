package com.example.pmdmentregas3;


import com.example.pmdmentregas3.firebase.FirebaseAdmin;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

/**
 * Created by tay on 25/11/17.
 */

public class DataHolder {

    public static class MyDataHolder {
        public static JSONObject jsonObject;
        public static  JSONObject jsonObjectTwitter;
        public static FirebaseAdmin firebaseAdmin;

        public static JSONObject getJsonObject() {
            return jsonObject;
        }

        public static void setJsonObject(JSONObject jsonObject) {
            MyDataHolder.jsonObject = jsonObject;
        }

        public static FirebaseAdmin getFirebaseAdmin() {
            return firebaseAdmin;
        }

        public static void setFirebaseAdmin(FirebaseAdmin firebaseAdmin) {
            MyDataHolder.firebaseAdmin = firebaseAdmin;
        }

        public static JSONObject getJsonObjectTwitter() {
            return jsonObjectTwitter;
        }

        public static void setJsonObjectTwitter(JSONObject jsonObjectTwitter) {
            MyDataHolder.jsonObjectTwitter = jsonObjectTwitter;
        }
    }


}