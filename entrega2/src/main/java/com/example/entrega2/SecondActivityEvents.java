package com.example.entrega2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.entrega2.firebase.FirebaseAdminListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * Created by tay on 25/11/17.
 */

public class SecondActivityEvents implements View.OnClickListener, FirebaseAdminListener {

    private SecondActivity secondActivity;

    public SecondActivityEvents(SecondActivity secondActivity) {
        this.secondActivity = secondActivity;
    }

    public SecondActivity getSecondActivity() {
        return secondActivity;
    }

    public void setSecondActivity(SecondActivity secondActivity) {
        this.secondActivity = secondActivity;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogOut) {
            System.out.println(  DataHolder.MyDataHolder.getFirebaseAdmin().getmAuth());
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
        System.out.println("eeebuehhhKitipasa: " + ok);
        if(ok){
            Intent intent = new Intent(secondActivity, MainActivity.class);
            secondActivity.startActivity(intent);
            secondActivity.finish();
        }
    }
}
