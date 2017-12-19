package com.example.examenandroid;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.mylib.fragment.ListFragment;

public class SecondActivity extends AppCompatActivity {
    private Button btnLogOut;
    private SecondActivityEvents events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.btnLogOut = this.findViewById(R.id.btnLogOut); //clincamos el btn con su componente visual
        this.btnLogOut.setText(R.string.btnLogOut); // establecemos el texto del btn
        events = new SecondActivityEvents(this);

        /*
        Para no perder la referencia a firebaseAdmin dado que de un activity a otro todo pasa a null,
        entonces lo guardamos en el dataHolder y se desde el second activity lo llamamos y decimos que sobrescriba
        los eventos que escucha dado qeu ahora escuchará los del second activity.
         */
        DataHolder.MyDataHolder.getFirebaseAdmin().setFirebaseAdminListener(events);
        this.btnLogOut.setOnClickListener(events);


    }

    public Button getBtnLogOut() {
        return btnLogOut;
    }

    public void setBtnLogOut(Button btnLogOut) {
        this.btnLogOut = btnLogOut;
    }
}
