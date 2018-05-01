package com.example.tay.examenapuntes;

import android.content.Intent;
import android.view.View;

import com.example.tay.examenapuntes.firebase.FirebaseAdminListener;
import com.google.firebase.database.DataSnapshot;

/**
 * Created by tay on 16/2/18.
 */

public class MainActivityEvents implements View.OnClickListener, FirebaseAdminListener{

    private MainActivity mainActivity;

    public MainActivityEvents(MainActivity mainActivity) {

        this.mainActivity= mainActivity;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.sign_in_button){
            signIn();
        }
    }
    //Si el btn pulsado es el de google se llamará al método de inicio de sesión de google que habré un activity propio de google
    private void signIn() {
        Intent signInIntent = this.mainActivity.getmGoogleSignInClient().getSignInIntent();
        //Le tenemos que pasar un request code para que al devovler una respuesta al onActivityResult le llegue este requestcode.
        //Ponemos por ejemplo 1
        mainActivity.startActivityForResult(signInIntent, 1);
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void loginIsOk(boolean ok) {
        if(ok==true){
            Intent intent = new Intent(this.getMainActivity(),SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
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
}
