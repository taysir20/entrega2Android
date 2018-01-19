package com.example.pmdmentregas2;


import org.json.JSONObject;

/**
 * Created by tay on 25/11/17.
 */

public class DataHolder {

    public static class MyDataHolder {
        public static JSONObject jsonObject;

        public static JSONObject getJsonObject() {
            return jsonObject;
        }

        public static void setJsonObject(JSONObject jsonObject) {
            MyDataHolder.jsonObject = jsonObject;
        }
    }


}