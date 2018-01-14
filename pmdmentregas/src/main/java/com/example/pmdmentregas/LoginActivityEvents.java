package com.example.pmdmentregas;

import android.content.Intent;
import android.view.View;

import com.example.mylib.fragment.LoginFragmentListener;
import com.example.pmdmentregas.firebase.FirebaseAdminListener;
import com.google.firebase.database.DataSnapshot;

/**
 * Created by tay on 12/1/18.
 */

public class LoginActivityEvents implements View.OnClickListener, FirebaseAdminListener, LoginFragmentListener{
    private LoginActivity loginActivity;

    public LoginActivityEvents(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    public LoginActivity getLoginActivity() {
        return loginActivity;
    }

    public void setLoginActivity(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void loginIsOk(boolean ok) {
        if(ok){
            Intent intent = new Intent(this.loginActivity, MainActivity.class);
            this.loginActivity.startActivity(intent);
            this.loginActivity.finish();
        }

    }

    @Override
    public void registerOk(boolean ok) {

    }

    @Override
    public void signOutOk(boolean ok) {

    }

    @Override
    public void downloadBranch(String branch, DataSnapshot dataSnapshot) {

    }

    @Override
    public void OnRegisteredClicked() {

    }

    @Override
    public void OnLoginClicked() {
        this.getLoginActivity().getFirebaseAdmin().signInWithEmailAndPassword(this.getLoginActivity().getLoginFragment().getTxtEmail().getText().toString(),this.getLoginActivity().getLoginFragment().getTxtPass().getText().toString());
    }
}
