package com.example.pmdmentregas3;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pmdmentregas3.firebase.ApiInterface;
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
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.internal.network.OAuth1aInterceptor;
import com.twitter.sdk.android.core.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    /*
    Variable del logueo de twitter que se vincula a su componente xml
     */
    private TwitterLoginButton twitterLoginButton;

    private FirebaseAdmin firebaseAdmin;
    // API que usaremos para hacer peticiones a twitter para sacar los datos del usuario
    private Retrofit retrofit;

    //JSONObject para almacenar los datos de twitter
    private JSONObject datosTwitter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datosTwitter = new JSONObject();

        //Inicializamos el SDK de Twitter llamándolo psándole como contexto la propio Activity en la que nos encontramos
        /*
        Tenemos que realizar el initialize antes que el setContentView pues si no intenta inicializar los
        componentes visuales es decir los botones antes de cargar twitter y entonces el botón no funciona.
        Esto ocurre con twitter y no conf acebook porque el facebook sdk que se hace referencia en el propio manifiest
        del módulo se apaecifica la key mientras que en twitter se coloca en el string y por tanto está cargado todavía
         */
        Twitter.initialize(this);
        /*
        Podemos setear de manera manual la configuración de twitter pero el método initialize lo hará por nosotros
        public void onCreate() {
            TwitterConfig config = new TwitterConfig.Builder(this)
                    .logger(new DefaultLogger(Log.DEBUG))
                    .twitterAuthConfig(new TwitterAuthConfig("CONSUMER_KEY", "CONSUMER_SECRET"))
                    .debug(true)
                    .build();
            Twitter.initialize(config);
        }
        */
        setContentView(R.layout.activity_main);
          /*
                Al igual que con Facebook, twitter requiere también de la instanciación de un callback específico de twitter.
                esta llamada hará que twitter devuelva al onActivityResult una respuesta desde el exterior del activity como
                con facebook
                 */
        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.login_button2);
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {

            //Se el logueo se ha realizado correctamente, se ejecuta el success
            @Override
            public void success(Result<TwitterSession> result) {


                System.out.println("El result de twitter es------------------____>  " + result.data.getUserName());
                try{

                    datosTwitter.put("UserName", result.data.getUserName().toString());
                    datosTwitter.put("id", String.valueOf(result.data.getUserId()));
                    handleTwitterSession(result.data);

                }catch (JSONException e){
                    e.printStackTrace();
                }

                //Inicializamos la API Retrofit para las peticiones a twitter
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.twitter.com/1.1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        // Twitter interceptor
                        .client(new OkHttpClient.Builder()
                                .addInterceptor(new OAuth1aInterceptor(TwitterCore.getInstance().getSessionManager().getActiveSession(), TwitterCore.getInstance().getAuthConfig()))
                                .build())
                        .build();
                //Una vez inicializada la API RETROFIT entonces llamamos al método getUserDetails que hemos creado para sacar los datos del usuario

                // Do something with result, which provides a TwitterSession for making API calls
            }

            //Método de logueo con firebase para twitter.

            private void handleTwitterSession(TwitterSession session) {
                Log.d("twitterSession", "handleTwitterSession:" + session);

                AuthCredential credential = TwitterAuthProvider.getCredential(
                        session.getAuthToken().token,
                        session.getAuthToken().secret);

                DataHolder.MyDataHolder.getFirebaseAdmin().getmAuth().signInWithCredential(credential)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TwitterSuccess", "signInWithCredential:success");
                                    FirebaseUser user = DataHolder.MyDataHolder.getFirebaseAdmin().getmAuth().getCurrentUser();
                                    //updateUI(user);
                                    getUserDatails();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TwitterFailure", "signInWithCredential:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
            }

            // Si el logueo ha fallado se ejecuta el failure
            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });

        this.setMainActivityEvents(new MainActivityEvents(this));
        firebaseAdmin = new FirebaseAdmin();
        //Inicializamos el callBackManager
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        //loginButton.setLoginBehavior(LoginBehavior.WEB_ONLY);
        //Añadimos los permisos que queramos solicitar al usuario en el momento en el que se loguea
        loginButton.setReadPermissions(Arrays.asList
                ("public_profile", "user_friends", "email", "user_photos", "  user_birthday", "read_custom_friendlists"));


        /*
        Si queremos meter el botón de facebook dentro de un fragment se usará
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
                                DataHolder.MyDataHolder.jsonObject = object;
                                //Al ser un método asíncrono solo hay que llamar a abrir el second activity cuando se haya descargado el jsonObject
                                // getMainActivityEvents().iniciarAppSecondActivity();

                            }
                        });
                Bundle parameters = new Bundle();
                //Con el parámetro fields especificamos los campos que queremos que nos devuelva el json de facebook
                parameters.putString("fields", "id,name,link,picture,birthday,email");
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
            /*
            Nota: En una función como puede ser el onclick de un floating button, muchas veces no podemos
            realizar el famoso this.'nombre de la variable/método' dado que ese método onclick no se encuentra en
            el propio contexto de esa función método al que llamamos.
            Para solucionarlo llamamos a su contexto por ejemplo MainActivity.this.'nombre de la variable/ función'
            a la que deseamos llamar.
            Hay que tener en cuenta que un componente con un botón puede tener como contextos por ejemplo el entorno
            del activity al que pertenece y a sus padres es decir la aplicación el propio sistema operativo donde se
            está ejecutando y por lo tanto sería válido cualquier pues todos son su contenedor.
            Además hay veces que diferentes funciones nos puede pedir como parámetro solamente el contexto padre absoluto
            que en android se conoce como baseContext
            Por últmo, hay que diferenciarlo de las llamadas a la REFERENCIA de las clases como por ejemplo MainActivity.class
            que como hemos dicho solo hacen referencia a la propia clase
             */


            private void handleFacebookAccessToken(AccessToken token) {
                Log.d("FirebaseFacebook", "handleFacebookAccessToken:" + token);

                AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
                DataHolder.MyDataHolder.getFirebaseAdmin().getmAuth().signInWithCredential(credential)
                        .addOnCompleteListener(MainActivity.this,  new OnCompleteListener<AuthResult>() {
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

            private void handleTwitterSession(TwitterSession session) {

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

    public void getUserDatails() {
        /*
        Con RAetrofit no se puede devolver el email dato que se necesitan permisos, por eso usaremos la clase
        de TwitterAuthCliente que sirve para devolver el email del usuario logueado previamente hayamos dado
        permisos desde nuestra aplicación de que podamos pedir el email
         */
        TwitterAuthClient authClient = new TwitterAuthClient();

        authClient.requestEmail(TwitterCore.getInstance().getSessionManager().getActiveSession(), new Callback<String>() {
            @Override
            public void success(Result<String> result) {
                try {
                    System.out.println("----------------_>>>>>>>El email de twitter es: " + result.data);
                    datosTwitter.put("email", result.data.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                /*
                Ahora mediante la API RETROFIT una vez inicializada en el onCreate del MainActivity, vamos a descargar
                todos los datos del usuario y después lo meteremos en JSON Object que pasaremos al dataholder para cargar
                desde el SecondActivity estos datos del usuario
                 */
                ApiInterface apiInterface = retrofit.create(ApiInterface.class);
                retrofit2.Call<User> call = apiInterface.getUserDetails(TwitterCore.getInstance().getSessionManager().getActiveSession().getUserName());
                call.enqueue(new retrofit2.Callback<User>() {
                    @Override
                    public void onResponse(retrofit2.Call<User> call, retrofit2.Response<User> response) {
                        System.out.println("-------------------------------->>>>>>>>>>>>>>>>><EL RESPONSE DE TWITTER : " + response.body().name.toString() + " y la url de su imagen es: " + response.body().profileImageUrl);
                        try {
                            datosTwitter.put("name",response.body().name.toString());
                            datosTwitter.put("description",response.body().description.toString());
                            datosTwitter.put("picture",response.body().profileImageUrl);

                            DataHolder.MyDataHolder.jsonObjectTwitter = datosTwitter;
                            getMainActivityEvents().iniciarAppThirdActivity();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailure(retrofit2.Call<User> call, Throwable t) {

                    }
                });
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });

    }

    //Método onActivity al que llegan las llamadas de facebook y twitter
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("------------------------------------------------------>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>El requestCode es: " + requestCode + " y el resultCode: " + resultCode + " y el data: " + data);
        //Si es facebook
        if (requestCode == 64206) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);
            //Si es twitter
        } else {
            super.onActivityResult(requestCode, resultCode, data);
            // Pass the activity result to the login button.
            twitterLoginButton.onActivityResult(requestCode, resultCode, data);
        }

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
