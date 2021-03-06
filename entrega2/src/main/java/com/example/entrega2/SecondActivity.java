package com.example.entrega2;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.entrega2.adapter.ListAdapter;
import com.example.entrega2.firebase.FirebaseAdmin;
import com.example.mylib.fragment.ListFragment;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private TextView lblBievenida;
    private Button btnLogOut;
    private SecondActivityEvents events;
    private ListFragment listFragment;
    private LinearLayout llContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.lblBievenida = this.findViewById(R.id.lblBienvenida);
        this.btnLogOut = this.findViewById(R.id.btnLogOut);
        this.lblBievenida.setText(R.string.lblBienvenida);
        this.btnLogOut.setText(R.string.btnLogOut);
        events = new SecondActivityEvents(this);//instanciamos el events del secondActivity
        DataHolder.MyDataHolder.getFirebaseAdmin().setFirebaseAdminListener(events);
        /*
        Para no perder la referencia a firebaseAdmin dado que de un activity a otro todo pasa a null,
        entonces lo guardamos en el dataHolder y se desde el second activity lo llamamos y decimos que sobrescriba
        los eventos que escucha dado qeu ahora escuchará los del second activity.
         */
        this.btnLogOut.setOnClickListener(events); // decimos que ekl escuchardor del onclick del btn de logout sea el SecondActivityEvents

        //this.listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        /*
        Para añadir de forma dinámica fragments a un layout y luego trabajar con ellos para poder destruirlos
        si queremos o conservarlos vamos a realizar los siguientes pasos:
         */
        this.llContainer = this.findViewById(R.id.llContainer); // sobre el LinearLayout vamos a insetar los fragments

        this.listFragment= new ListFragment(); // Uno de los fragments a insertar


        //El fragmentManager se encarga de fgestionar los fragmentos para poder insertarlos en el linearLayout o sacarlos.

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //Con la operación add especificamos el contenedor donde queremos meter el fragment, el fragment que queremos meter y un identificador para es fragment
        transaction.add(this.getLlContainer().getId(), this.getListFragment(), "fragmentList");
        transaction.commit(); // comiteamos
    /*
    Hay que tener en cuenta que si añadimos otro fragment al linearLayout este último sobrescribirá al primero por tanto para poder
    meter varios, lo mejor es crear distintos linearlayouts dentro un linearlayout padre.
    Cada linear layout hijo contendrá un fragment.
     */


       /* ArrayList<String> contenidoLista = new ArrayList<>(); // este array lo creamos de forma manual, pero a posteriori lo que haremos es descargarlo de firebase
        contenidoLista.add("Yony");
        contenidoLista.add("Javier");
        contenidoLista.add("Ramsés");
        contenidoLista.add("Sergio");
        contenidoLista.add("Oscar");
        contenidoLista.add("Manuel");
        contenidoLista.add("Taysir");
       this.listFragment.getMyLista().setAdapter(new ListAdapter(contenidoLista)); // pasamos por parámetro el arrayList creado para inicializar el arrayList del listAdapter
        */
       DataHolder.MyDataHolder.getFirebaseAdmin().downloadDataAndObserveBranchChanges("Coches"); // llamo al métood de descarga con la rama que quiero que observe a partir de la raíz.
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

    public SecondActivityEvents getEvents() {
        return events;
    }

    public void setEvents(SecondActivityEvents events) {
        this.events = events;
    }


    public ListFragment getListFragment() {
        return listFragment;
    }

    public void setListFragment(ListFragment listFragment) {
        this.listFragment = listFragment;
    }

    public LinearLayout getLlContainer() {
        return llContainer;
    }

    public void setLlContainer(LinearLayout llContainer) {
        this.llContainer = llContainer;
    }


}
