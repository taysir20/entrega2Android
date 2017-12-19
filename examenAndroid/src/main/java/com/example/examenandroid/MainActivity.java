package com.example.examenandroid;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.examenandroid.firebase.FirebaseAdmin;
import com.example.mylib.fragment.LoginFragment;
import com.example.mylib.fragment.RegisterFragment;

/*
Para usar nuestra librería vamos a importarla en el build.gradle del módulo "examen android"
 */

public class MainActivity extends AppCompatActivity {
    // Declaración de las variables de los fragments que se encuentran en el mainActivity gracias a que hemos importado la librería
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    /*
    Declaración del MainActivityEvents que gestionará todos los eventos que se llevan a cabo en todos los componentes
    del MainActivity.
     */
    private MainActivityEvents mainActivityEvents;
    private FirebaseAdmin firebaseAdmin;//variable para manejar todas las acciones de Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Como el MainActivity será lo primero que vamos ejecutar al arrancar la aplicación y el onCreate es el método
        que se ejecutará en primera instancia, vamos usarlo para instanciar los fragments y el events.
         */
         /*
          Como el constructor debe de recibir el mainActivity, le pasamos mediante "this" el propio MainActivity dado que
          estamos a la propia clase MainActivity
         */
         /*
         Hacemos el new del FirebaseAdmin para instanciarlo, y como el constructor del Admin llama al método oncreate
         entonces se instanciará la variable Auth con la que vamos a trabajar para el registro y logueo de usuarios.
          */
        this.setFirebaseAdmin(new FirebaseAdmin());
        this.setMainActivityEvents(new MainActivityEvents(this));
        //Decimos a la clase FirebaseAdmin quién será su escuchador, en este caso MainActivityEvents esuchará por todos los eventos de FirebaseAdmin
        this.getFirebaseAdmin().setFirebaseAdminListener(this.getMainActivityEvents());

        //Instanciación de los fragmentos
        this.setLoginFragment(new LoginFragment());
        this.setRegisterFragment(new RegisterFragment());
        /*
        En un principio  asignabamos directamente a cada variable de un fragment su correspondiente XML con el método
        propio de las views llamado "findViewByid()" y haciendo uso del fichero id al que se accede por R..id.idAsignada.
        Para mejorar la optimización y agregar de forma dinámica fragments a nuestra app, vamos a crear un linearLayout
        que contendrá a su vez linearLayouts hijas las cuáles cada una tendrá un fragment.
        De esta forma, podremos gestionar estos fragmentos (añadirlos, borrarlos,etc). Este linearLayout se ecuentra creado
        desde el XML del mainActivity.
        Por último, para poder añadir dichos fragments a su correspondiente layout utilizaremos una transacción para operar con estas acciones.
        Las transacciones contienen el método  getSupportFragmentManager().beginTransaction() que permite administrar
        fragmentos e iniciar una transacción.

         */
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //Con la operación add especificamos el contenedor donde queremos meter el fragment, el fragment que queremos meter y un identificador para ese fragment
        transaction.add(R.id.logLayout, this.getLoginFragment(), "logFragment");
        transaction.add(R.id.regLayout, this.getRegisterFragment(), "regFragment");
        /*
        Ahora establecemos el esuchador de los eventos que ocurran en los fragments.
        Cada fragment tiene su propio events, pero todos ellos llamarán mediante sus correspondientes listener (variables de las interfaces)
        a quién implemente los métodos de dichas interfaces. El MainActivityEvents va a implementar los métodos de las interfaces
        de los fragments.
        Por lo tanto, los fragmentos tanto login como register tienen que saber que su escuchador es el MainActivityEvents que es el que
        implementa sus métodos para que desde los events de los fragments se pueda llamar a los que implementen esos métodos(MainActivityEvents),
        pues si no, estaría a null.
         */
        this.loginFragment.setLoginFragmentListener(this.getMainActivityEvents());
        this.registerFragment.setRegisterFragmentListener(this.getMainActivityEvents());
        //Como inicialmente queremos que se muestre el loginFragment y oculte el registerFragment usamos show y hide respectivamente.
        //Estos métodos se pueden usar gracias a que como hemos dicho antes, el transaction permite gestionar los frtagments.
        transaction.hide(this.getRegisterFragment());
        transaction.show(this.getLoginFragment());
        /*
        Por último, para que se lleven a cabo los cambios debemos de "firmar" que es lo que se conoce como comitear para que se
        produzcan los cambios
         */
        transaction.commit(); // comiteamos

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
}
