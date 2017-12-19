package com.example.examenandroid;

import android.content.Intent;
import android.view.View;

import com.example.examenandroid.firebase.FirebaseAdminListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

/**
 * Created by tay on 19/12/17.
 */

public class SecondActivityEvents implements View.OnClickListener, FirebaseAdminListener{
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

    @Override
    public void signOutOk(boolean ok) {
        // Si se recibe un ok se realzia un intent para matar el secondActivity y dirigirnos de nuevo al maiActivity
        if (ok) {
            Intent intent = new Intent(secondActivity, MainActivity.class);
            secondActivity.startActivity(intent);
            secondActivity.finish();
        }
    }


}
