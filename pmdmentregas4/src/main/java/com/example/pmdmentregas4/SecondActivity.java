package com.example.pmdmentregas4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.crash.FirebaseCrash;

public class SecondActivity extends AppCompatActivity {
    private SecondActivityEvents secondActivityEvents;
    private Button btnCierre;
    private Button btnSendNotfication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.setSecondActivityEvents(new SecondActivityEvents(this));
        DataHolder.MyDataHolder.getFirebaseAdmin().setFirebaseAdminListener(this.getSecondActivityEvents());
        this.btnCierre = this.findViewById(R.id.btnCierre);
        this.btnCierre.setOnClickListener(this.getSecondActivityEvents());
        this.btnSendNotfication = this.findViewById(R.id.btnSendNotification);
        this.btnSendNotfication.setOnClickListener(this.getSecondActivityEvents());

        //Enviamos también un log para debuggear nuestra app dede firebase. Si todo el oncreate se ha ejecutado correctamente se enviará el log
        FirebaseCrash.log("ONCREATE SECOND ACTIVITY EJECUTADO CORRECTAMENTE");


    }

    public SecondActivityEvents getSecondActivityEvents() {
        return secondActivityEvents;
    }

    public void setSecondActivityEvents(SecondActivityEvents secondActivityEvents) {
        this.secondActivityEvents = secondActivityEvents;
    }

    public Button getBtnCierre() {
        return btnCierre;
    }

    public void setBtnCierre(Button btnCierre) {
        this.btnCierre = btnCierre;
    }

    public Button getBtnSendNotfication() {
        return btnSendNotfication;
    }

    public void setBtnSendNotfication(Button btnSendNotfication) {
        this.btnSendNotfication = btnSendNotfication;
    }
}
