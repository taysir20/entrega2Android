package com.example.entrega2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.entrega2.firebase.FirebaseAdmin;

public class SecondActivity extends AppCompatActivity {
    private TextView lblBievenida;
    private Button btnLogOut;
    private SecondActivityEvents events;
    private FirebaseAdmin firebaseAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        firebaseAdmin = new FirebaseAdmin();
        this.lblBievenida = this.findViewById(R.id.lblBienvenida);
        this.btnLogOut = this.findViewById(R.id.btnLogOut);
        this.lblBievenida.setText(R.string.lblBienvenida);
        this.btnLogOut.setText(R.string.btnLogOut);
        events = new SecondActivityEvents(this);
        this.btnLogOut.setOnClickListener(events);

    }

    public TextView getLblBievenida() {
        return lblBievenida;
    }

    public void setLblBievenida(TextView lblBievenida) {
        this.lblBievenida = lblBievenida;
    }

    public Button getBtnLogOut() {
        return btnLogOut;
    }

    public void setBtnLogOut(Button btnLogOut) {
        this.btnLogOut = btnLogOut;
    }
}
