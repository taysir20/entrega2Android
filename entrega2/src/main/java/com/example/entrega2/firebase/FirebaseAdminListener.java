package com.example.entrega2.firebase;

/**
 * Created by tay on 25/11/17.
 */

public interface FirebaseAdminListener {

   public void loginIsOk(boolean ok);
   public void registerOk(boolean ok);
   public void signOutOk(boolean ok);
}
