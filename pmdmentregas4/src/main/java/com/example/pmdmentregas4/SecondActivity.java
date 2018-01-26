package com.example.pmdmentregas4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    private SecondActivityEvents secondActivityEvents;
    private Button btnCierre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.setSecondActivityEvents(new SecondActivityEvents(this));
        DataHolder.MyDataHolder.getFirebaseAdmin().setFirebaseAdminListener(this.getSecondActivityEvents());
        this.btnCierre = this.findViewById(R.id.btnCierre);
        this.btnCierre.setOnClickListener(this.getSecondActivityEvents());

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
}
