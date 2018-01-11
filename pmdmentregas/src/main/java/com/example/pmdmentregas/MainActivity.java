package com.example.pmdmentregas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.pmdmentregas.firebase.FirebaseAdmin;

public class MainActivity extends AppCompatActivity {
    private MainActivityEvents mainActivityEvents;
    private FirebaseAdmin firebaseAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setFirebaseAdmin(new FirebaseAdmin());
        this.setMainActivityEvents(new MainActivityEvents(this));

    }

    public MainActivityEvents getMainActivityEvents() {
        return mainActivityEvents;
    }

    public void setMainActivityEvents(MainActivityEvents mainActivityEvents) {
        this.mainActivityEvents = mainActivityEvents;
    }

    public FirebaseAdmin getFirebaseAdmin() {
        return firebaseAdmin;
    }

    public void setFirebaseAdmin(FirebaseAdmin firebaseAdmin) {
        this.firebaseAdmin = firebaseAdmin;
    }
}
