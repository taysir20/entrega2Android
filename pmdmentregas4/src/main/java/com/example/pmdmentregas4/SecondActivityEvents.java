package com.example.pmdmentregas4;

import android.content.Intent;
import android.view.View;

import com.example.mylib.AsyncTask.HttpJsonAsyncTask;
import com.example.mylib.AsyncTask.HttpJsonAsyncTaskListener;
import com.example.pmdmentregas4.firebase.FirebaseAdminListener;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;

/**
 * Created by tay on 26/1/18.
 */

public class SecondActivityEvents implements View.OnClickListener, FirebaseAdminListener, HttpJsonAsyncTaskListener{
    private  SecondActivity secondActivity;

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
        if(view.getId()==R.id.btnCierre){
            DataHolder.MyDataHolder.getFirebaseAdmin().logOut();
        }else if(view.getId()==R.id.btnSendNotification){
            String url ="https://us-central1-facebooktwitter-187f7.cloudfunctions.net/sendNewPush";
            HttpJsonAsyncTask httpJsonAsyncTask = new HttpJsonAsyncTask();
            HttpJsonAsyncTask.REQUEST_METHOD="GET";
            httpJsonAsyncTask.setHttpJsonAsyncTaskListener(this);
            httpJsonAsyncTask.execute(url);
            //Enviamos también un log para debuggear nuestra app dede firebase. Si todo el oncreate se ha ejecutado correctamente se enviará el log
            FirebaseCrash.log("HTTPJSONAsyncTask EJECUTADO CORRECTAMENTE");

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
        if (ok) {

            Intent intent = new Intent(secondActivity, MainActivity.class);
            secondActivity.startActivity(intent);
            secondActivity.finish();
        }

    }

    @Override
    public void downloadBranch(String branch, DataSnapshot dataSnapshot) {



    }

    @Override
    public void FinalTasknotification(Object object) {

    }
}
