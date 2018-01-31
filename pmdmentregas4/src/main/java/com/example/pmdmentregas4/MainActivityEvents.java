package com.example.pmdmentregas4;







import android.content.Intent;
import android.support.v4.app.FragmentTransaction;

import com.example.mylib.fragment.LoginFragment;
import com.example.mylib.fragment.LoginFragmentListener;
import com.example.mylib.fragment.RegisterFragment;
import com.example.mylib.fragment.RegisterFragmentEvents;
import com.example.mylib.fragment.RegisterFragmentListener;
import com.example.pmdmentregas4.firebase.FirebaseAdminListener;
import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tay on 26/1/18.
 */

public class MainActivityEvents implements LoginFragmentListener, RegisterFragmentListener, FirebaseAdminListener{
    private MainActivity mainActivity;

    public MainActivityEvents(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void OnRegisteredClicked() {
        FragmentTransaction transition = mainActivity.getSupportFragmentManager().beginTransaction();
        transition.hide(this.mainActivity.getLoginFragment());
        transition.show(this.mainActivity.getRegisterFragment());
        transition.commit();


    }

    @Override
    public void OnLoginClicked() {
        this.mainActivity.getFirebaseAdmin().signInWithEmailAndPassword(this.mainActivity.getLoginFragment().getTxtEmail().getText().toString(), this.mainActivity.getLoginFragment().getTxtPass().getText().toString());

    }

    @Override
    public void OnClickRegistered() {

        this.mainActivity.getFirebaseAdmin().createUserWithEmailAndPassword(this.mainActivity.getRegisterFragment().getTxtEmail().getText().toString(), this.mainActivity.getRegisterFragment().getTxtPass().getText().toString());

    }


    @Override
    public void loginIsOk(boolean ok) {

        System.out.println("llega: " + ok);
        if(ok){
            this.mainActivity.getFirebaseAdmin().insertToken();
            Intent intent = new Intent(mainActivity, SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();

        }

    }

    @Override
    public void registerOk(boolean ok) {

        if(ok){
            FragmentTransaction transition = mainActivity.getSupportFragmentManager().beginTransaction();
            transition.hide(mainActivity.getRegisterFragment());
            transition.show(mainActivity.getLoginFragment());
            transition.commit();
        }

    }

    @Override
    public void signOutOk(boolean ok) {

    }

    @Override
    public void downloadBranch(String branch, DataSnapshot dataSnapshot) {

    }




}
