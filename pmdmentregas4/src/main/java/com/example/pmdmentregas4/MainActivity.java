package com.example.pmdmentregas4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mylib.fragment.LoginFragment;
import com.example.mylib.fragment.RegisterFragment;
import com.example.pmdmentregas4.SQLite.DatabaseHandler;
import com.example.pmdmentregas4.firebase.FirebaseAdmin;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.crash.FirebaseCrash;


public class MainActivity extends AppCompatActivity {
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private MainActivityEvents mainActivityEvents;
    private FirebaseAdmin firebaseAdmin;
    private DatabaseHandler databaseHandler;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setMainActivityEvents(new MainActivityEvents(this));
        this.loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.lFragment);
        this.registerFragment=(RegisterFragment)getSupportFragmentManager().findFragmentById(R.id.rFragment);
        this.loginFragment.setLoginFragmentListener(this.getMainActivityEvents());
        this.registerFragment.setRegisterFragmentListener(this.getMainActivityEvents());
        this.setFirebaseAdmin(new FirebaseAdmin());
        this.getFirebaseAdmin().setFirebaseAdminListener(this.getMainActivityEvents());

        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.hide(this.getRegisterFragment());
        transition.show(this.getLoginFragment());
        transition.commit();
//Comprobación de los servicios de google si no los tenemos los pedirá que instalemos
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int resultCode = api.isGooglePlayServicesAvailable(getApplicationContext());

        /*
        EJEMPLO DE EXCEPCIÓN AÑADIDA A MANO. SIEMPRE SE VA A EJECUTAR Y ENVIAR A FIREBASE
         */
        FirebaseCrash.report(new Exception("Mi primera excepción a Firebase"));



        if (resultCode == ConnectionResult.SUCCESS) {
            System.out.println("Hay servicios google play");

        }else{
            try{
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Atención");
                alertDialog.setMessage("Para usar está aplicación debe de instalar los servicios de Google.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                                try {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                }
                                catch (android.content.ActivityNotFoundException anfe) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                }
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }catch(ExceptionInInitializerError e){
                //Excepción si no se ejecuta el alertDialog cuando no hay Google Play instalada
                FirebaseCrash.report(e);
            }


        }

        //Enviamos también un log para debuggear nuestra app dede firebase. Si todo el oncreate se ha ejecutado correctamente se enviará el log
        FirebaseCrash.log("ONCREATE EJECUTADO CORRECTAMENTE");



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

    public FirebaseAdmin getFirebaseAdmin() {
        return firebaseAdmin;
    }

    public void setFirebaseAdmin(FirebaseAdmin firebaseAdmin) {
        this.firebaseAdmin = firebaseAdmin;
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public void setDatabaseHandler(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    @Override
    protected void onResume() {
        super.onResume();
        DatabaseHandler databaseHandler= new DatabaseHandler(this);
        databaseHandler.deleteAllMessage();
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();

    }
}





