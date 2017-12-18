package com.example.examenandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mylib.fragment.LoginFragment;
import com.example.mylib.fragment.RegisterFragment;

/*
Para usar nuestra librería vamos a importarla en el build.gradle del módulo "examen android"
 */

public class MainActivity extends AppCompatActivity {
    // Declaración de las variables de los fragments que se encuentran en el mainActivity
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    /*
    Declaración del MainActivityEvents que gestionará todos los eventos que se llevan a cabo en todos los componentes
    del MainActivity.
     */
    private MainActivityEvents mainActivityEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public MainActivityEvents getMainActivityEvents() {
        return mainActivityEvents;
    }

    public void setMainActivityEvents(MainActivityEvents mainActivityEvents) {
        this.mainActivityEvents = mainActivityEvents;
    }
}
