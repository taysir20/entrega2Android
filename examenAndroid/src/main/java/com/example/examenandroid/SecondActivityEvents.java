package com.example.examenandroid;

import android.content.Intent;
import android.view.View;

import com.example.examenandroid.adapter.ListAdapter;
import com.example.examenandroid.adapter.ListAdapterListener;
import com.example.examenandroid.adapter.ListViewHolderEvents;
import com.example.examenandroid.adapter.MyViewHolder;
import com.example.examenandroid.entity.Coche;
import com.example.examenandroid.firebase.FirebaseAdminListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

/**
 * Created by tay on 19/12/17.
 */

public class SecondActivityEvents implements View.OnClickListener, FirebaseAdminListener, ListAdapterListener{


    private SecondActivity secondActivity;

    public SecondActivityEvents(SecondActivity secondActivity) {
        this.secondActivity = secondActivity;
    }

    public SecondActivity getSecondActivity() {
        return secondActivity;
    }

    public void setSecondActivity(SecondActivity secondActivity) {
        //Al igual que el mainactivityEvents que recibe su mainActivity, aquí recibimos el secondActivity
        this.secondActivity = secondActivity;
    }

/*
El onclick recibirá la llmada del botón de logout desde el seoncdActivity y cerrará sesión, matará el secondActivity y
iniciará una transición al mainActivity
 */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogOut) {
            System.out.println(DataHolder.MyDataHolder.getFirebaseAdmin().getmAuth());
            DataHolder.MyDataHolder.getFirebaseAdmin().logOut();

        }
    }

    @Override
    public void loginIsOk(boolean ok) {

    }

    @Override
    public void registerOk(boolean ok) {

    }
//Método apra cerrar sesión
    @Override
    public void signOutOk(boolean ok) {
        // Si se recibe un ok se realzia un intent para matar el secondActivity y dirigirnos de nuevo al maiActivity
        if (ok) {
            Intent intent = new Intent(secondActivity, MainActivity.class);
            secondActivity.startActivity(intent);
            secondActivity.finish();
        }
    }
//Método para la descarga de los datos de una rama de firebase
    @Override
    public void downloadBranch(String branch, DataSnapshot dataSnapshot) {
         /*
        Por aquí pasaran todas las ramas y snapshots que obseervemos. Para elegir entre una rama u otra,
        basta con usar un if/else
         */
        if(branch.equals("Coches")){
            System.out.println("ldentro : " + branch);
            //Tenemos que usar un GenericTypeIndicator dado que firebase devuelve los datos utlizando esta clase abstracta
            GenericTypeIndicator<ArrayList<Coche>> indicator = new GenericTypeIndicator<ArrayList<Coche>>(){};
            ArrayList<Coche> arrCoches = dataSnapshot.getValue(indicator);//desde el value podemos castearlo al tipo que queramos, en este caso lo casteamos al genericTypeIndicator
            ListAdapter listAdapter = new ListAdapter(arrCoches,this.getSecondActivity());
            this.secondActivity.getListFragment().getMyLista().setAdapter(listAdapter);
             /*
            Decimos al adapter que su escuchador de eventos será el secondActivityEvents, En el adapter cada celda tiene que tener el escuchador
            y por tanto a cada celda se le dirá que su escuchador es este escuchador SecondActivityEvents que hemos pasado
             */
            listAdapter.setListAdapterListener(this);

        }
    }

// Método que recibe los onclicks de la lista
    @Override
    public void listAdapterCellClicked(MyViewHolder cell) {
        System.out.println("estoy pinchando la celda: " + cell.getAdapterPosition());
    }
}
