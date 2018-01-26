package com.example.pmdmentregas4;

import android.content.Intent;
import android.view.View;

import com.example.pmdmentregas4.firebase.FirebaseAdminListener;
import com.google.firebase.database.DataSnapshot;

/**
 * Created by tay on 26/1/18.
 */

public class SecondActivityEvents implements View.OnClickListener, FirebaseAdminListener{
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
}
