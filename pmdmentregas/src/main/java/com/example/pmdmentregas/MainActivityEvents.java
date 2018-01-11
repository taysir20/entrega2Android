package com.example.pmdmentregas;

import com.example.pmdmentregas.firebase.FirebaseAdmin;

/**
 * Created by tay on 11/1/18.
 */

public class MainActivityEvents {
    private MainActivity mainActivity;


    public MainActivityEvents(MainActivity mainActivity) {

        this.mainActivity = mainActivity;



    }

    public MainActivity getMainActivity() {

        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
