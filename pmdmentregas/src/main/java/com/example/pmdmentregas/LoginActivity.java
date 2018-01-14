package com.example.pmdmentregas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mylib.fragment.LoginFragment;
import com.example.pmdmentregas.firebase.FirebaseAdmin;
import com.google.android.gms.maps.SupportMapFragment;

public class LoginActivity extends AppCompatActivity {
    private LoginFragment loginFragment;
    private LoginActivityEvents loginActivityEvents;
    private FirebaseAdmin firebaseAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.firebaseAdmin = new FirebaseAdmin();

        this.loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.loginFragment);
        this.loginActivityEvents = new LoginActivityEvents(this);
        this.loginFragment.setLoginFragmentListener(this.getLoginActivityEvents());
        this.firebaseAdmin.setFirebaseAdminListener(this.getLoginActivityEvents());


    }

    public LoginFragment getLoginFragment() {
        return loginFragment;
    }

    public void setLoginFragment(LoginFragment loginFragment) {
        this.loginFragment = loginFragment;
    }

    public LoginActivityEvents getLoginActivityEvents() {
        return loginActivityEvents;
    }

    public void setLoginActivityEvents(LoginActivityEvents loginActivityEvents) {
        this.loginActivityEvents = loginActivityEvents;
    }

    public FirebaseAdmin getFirebaseAdmin() {
        return firebaseAdmin;
    }

    public void setFirebaseAdmin(FirebaseAdmin firebaseAdmin) {
        this.firebaseAdmin = firebaseAdmin;
    }
}
