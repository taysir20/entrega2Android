package com.example.entrega2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.entrega2.firebase.FirebaseAdmin;
import com.example.mylib.fragment.LoginFragment;
import com.example.mylib.fragment.RegisterFragment;

public class MainActivity extends AppCompatActivity{
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private MainActivityEvents event;
    private FirebaseAdmin firebaseAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Para poder llamar al findFragmentById que es el equivalente del findViewById hay que usar el método getSupportFragmentManager
        this.firebaseAdmin = new FirebaseAdmin();
        this.loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.loginFragment);
        this.registerFragment=(RegisterFragment)getSupportFragmentManager().findFragmentById(R.id.registerFragment);
        event = new MainActivityEvents(this);
        this.loginFragment.setLoginFragmentListener(event);
        this.registerFragment.setRegisterFragmentListener(event);

        android.support.v4.app.FragmentTransaction transition = this.getSupportFragmentManager().beginTransaction();
        transition.hide(this.getRegisterFragment());
        transition.commit();
    }

    public LoginFragment getLoginFragment() {
        return loginFragment;
    }

    public void setLoginFragment(LoginFragment loginFragment) {
        this.loginFragment = loginFragment;
    }

    public RegisterFragment getRegisterFragment() {
        return registerFragment;
    }

    public void setRegisterFragment(RegisterFragment registerFragment) {
        this.registerFragment = registerFragment;
    }

    public MainActivityEvents getEvent() {
        return event;
    }

    public void setEvent(MainActivityEvents event) {
        this.event = event;
    }

    public FirebaseAdmin getFirebaseAdmin() {
        return firebaseAdmin;
    }

    public void setFirebaseAdmin(FirebaseAdmin firebaseAdmin) {
        this.firebaseAdmin = firebaseAdmin;
    }
}
