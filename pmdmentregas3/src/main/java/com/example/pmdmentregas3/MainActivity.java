package com.example.pmdmentregas3;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pmdmentregas3.firebase.FirebaseAdmin;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private MainActivityEvents mainActivityEvents;
    /*
    Variable de llamadas de retorno de facebook para gestionar las respuestas que obtenemos en el activity del inicio
     de sesión de facebook
     */

    private CallbackManager callbackManager;
    /*
    Variable del logueo de facebook que se vincula a su componente xml
     */
    private LoginButton loginButton;

    private FirebaseAdmin firebaseAdmin;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setMainActivityEvents(new MainActivityEvents(this));
        firebaseAdmin = new FirebaseAdmin();
        //Inicializamos el callBackManager
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
           //loginButton.setLoginBehavior(LoginBehavior.WEB_ONLY);
        //Añadimos los permisos que queramos solicitar al usuario en el momento en el que se loguea
        loginButton.setReadPermissions(Arrays.asList
                ("public_profile","user_friends", "email" , "user_photos","  user_birthday","read_custom_friendlists" ));

        /*
        Si queremos meter el botón dentro de un fragment se usará
         loginButton.setFragment(this);
         */


        /*
         Registro de llamadas de devolución. Se hace un new del retorno de llamada
         que tiene como parámetro un LoginResult que se devuelve en cada método
         propio del botón.
         NO SE PUEDEN LLAMAR A ESTOS MÉTODOS HASTA QUE el método onActivityResult del propio MainActivity
         el cuál presentan todos los activitys, no los llama.
         Esto se debe a que este método recibe todas las llamadas que se hagan de manera externa al activity
         en cuestión. En este caso el facebook devolverá la llamada a este onActivityResult con un okey o un error
         y entonces al haber sido recibido por el onActivityResult se podrán ejecutar los tres métodos.
          */
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            //Se llama si se ha realizado el logueo correctamente
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());

                //Mediante la API GraphRequest podemos realizar solicitudes para devolver un JSONObject asociado al usuario logueada que contiene toda su información
                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                System.out.println("---------->El objeto devuelto por facebook es: " + object);
                                //JSONObject para almacenar los datos del json que nos devuelve facebook al loguearnos con todos los datos del usuario
                                DataHolder.MyDataHolder.jsonObject=object;
                                //Al ser un método asíncrono solo hay que llamar a abrir el second activity cuando se haya descargado el jsonObject
                               // getMainActivityEvents().iniciarAppSecondActivity();

                            }
                        });
                Bundle parameters = new Bundle();
                //Con el parámetro fields especificamos los campos que queremos que nos devuelva el json de facebook
                parameters.putString("fields", "id,name,link,picture,birthday");
                request.setParameters(parameters);
                request.executeAsync();



                /*
                Podemos usar la API Picasso, Glide o BipMap para cargar la imagen de perfil de facebook del usuario logueado
                public static Bitmap getFacebookProfilePicture(String userID){
                    URL imageURL = new URL("https://graph.facebook.com/" + userID + "/picture?type=large");
                    Bitmap bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());

                    return bitmap;
                }

                Bitmap bitmap = getFacebookProfilePicture(userId);

                //////////////////
                userpicture = (ImageView) row.findViewById(R.id.postuserid);

                Picasso.with(context)
                       .load("https://graph.facebook.com/" + userID+ "/picture?type=large")
                       .into(userpicture);
                 */

                // App code









            }


            private void handleFacebookAccessToken(AccessToken token) {
                Log.d("FirebaseFacebook", "handleFacebookAccessToken:" + token);

                AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
                DataHolder.MyDataHolder.getFirebaseAdmin().getmAuth().signInWithCredential(credential)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Logueo", "signInWithCredential:success");
                                    FirebaseUser user = DataHolder.MyDataHolder.getFirebaseAdmin().getmAuth().getCurrentUser();
                                    getMainActivityEvents().iniciarAppSecondActivity();
                                    //updateUI(user);
                                } else {
                                   /* Intent myIntent = new Intent(this, FacebookActivity.class);
                                    startActivity(myIntent);
                                    */
                                    // If sign in fails, display a message to the user.
                                    Log.w("Logueo", "signInWithCredential:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
            }
            //Se llama si se ha cancelado el logueo
            @Override
            public void onCancel() {
                // App code
            }
            //Se llama si se ha producido un error durante el logueo
            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }
    //Método onActivity al que llegan las llamadas de facebook
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

    public void setCallbackManager(CallbackManager callbackManager) {
        this.callbackManager = callbackManager;
    }

    public MainActivityEvents getMainActivityEvents() {
        return mainActivityEvents;
    }

    public void setMainActivityEvents(MainActivityEvents mainActivityEvents) {
        this.mainActivityEvents = mainActivityEvents;
    }

    public LoginButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(LoginButton loginButton) {
        this.loginButton = loginButton;
    }

    public FirebaseAdmin getFirebaseAdmin() {
        return firebaseAdmin;
    }

    public void setFirebaseAdmin(FirebaseAdmin firebaseAdmin) {
        this.firebaseAdmin = firebaseAdmin;
    }
}
