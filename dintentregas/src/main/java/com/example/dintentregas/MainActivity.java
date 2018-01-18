package com.example.dintentregas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MainActivityEvents mainActivityEvents;
    private FloatingActionButton  fab;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private HelloWorldFragment helloWorldFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //El ActivityBasic define un toolbar y un botón flotante
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.fab = (FloatingActionButton) findViewById(R.id.fab);

        /*
        declaramos los tres fragment en el MainActivity, instanciamos el events de este y al fab se le setea
        el listener del onlock
         */

       this.setMainActivityEvents(new MainActivityEvents(this));
       this.fab.setOnClickListener(this.getMainActivityEvents());
       this.fragment1= (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragment);
       this.fragment2= (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fragment2);
       this.fragment3= (Fragment3) getSupportFragmentManager().findFragmentById(R.id.fragment3);

       //Inicialmente el fragmento de helloworld estará ocultado, Semostrará al pinchar sobre el fab y se ocultará al volver a darle
       this.helloWorldFragment=(HelloWorldFragment) getSupportFragmentManager().findFragmentById(R.id.HelloWorldFragment);
        FragmentTransaction transition = this.getSupportFragmentManager().beginTransaction();
        transition.hide(this.getHelloWorldFragment());
        transition.commit();


    }

    public MainActivityEvents getMainActivityEvents() {
        return mainActivityEvents;
    }

    public void setMainActivityEvents(MainActivityEvents mainActivityEvents) {
        this.mainActivityEvents = mainActivityEvents;
    }

    public FloatingActionButton getFab() {
        return fab;
    }

    public void setFab(FloatingActionButton fab) {
        this.fab = fab;
    }

    public Fragment1 getFragment1() {
        return fragment1;
    }

    public void setFragment1(Fragment1 fragment1) {
        this.fragment1 = fragment1;
    }

    public Fragment2 getFragment2() {
        return fragment2;
    }

    public void setFragment2(Fragment2 fragment2) {
        this.fragment2 = fragment2;
    }

    public Fragment3 getFragment3() {
        return fragment3;
    }

    public void setFragment3(Fragment3 fragment3) {
        this.fragment3 = fragment3;
    }

    public HelloWorldFragment getHelloWorldFragment() {
        return helloWorldFragment;
    }

    public void setHelloWorldFragment(HelloWorldFragment helloWorldFragment) {
        this.helloWorldFragment = helloWorldFragment;
    }
}
