package com.example.dintentregas3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtOcultar;
    private Button btnRotar;
    //esta img se desplazará hasta la posición inicial
    private ImageView imgDesplazar;
    //esta img habrá que ir pulsándola para desplazarla a la posición inicial
    private ImageView imgDesplazar2;
    private MainActivityEvents mainActivityEvents;

    //Variables de animaciones
    private Animation animationFade;
    private Animation animationRotate;
    private Animation animationTranslate;
    private Animation animationTranslate2;
    private Animation animationTranslate2_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setMainActivityEvents(new MainActivityEvents(this));
        this.txtOcultar = this.findViewById(R.id.txtOcultar);
        this.imgDesplazar = this.findViewById(R.id.imgDesplazar);
        this.imgDesplazar2 = this.findViewById(R.id.imgTranslate2);
        this.btnRotar = this.findViewById(R.id.btnRotar);
        this.btnRotar.setOnClickListener(this.getMainActivityEvents());
        this.imgDesplazar.setOnClickListener(this.getMainActivityEvents());
        this.txtOcultar.setOnClickListener(this.getMainActivityEvents());
        this.imgDesplazar2.setOnClickListener(this.getMainActivityEvents());
        /*
        A través de la clase AnimationUtils podemos asignar a un elemento una animación que tengamos creada
        en 'anim'. Para ello haremos uso de su método loadAnimation para cargarla y la guardaremos
         en una variable de tipo animation para luego llamar a startAnimation desde el onclick del events y pasarsela como parámetro
        para asignarsela al elemento.
         */
        //recordamos que el contexto debe de ser cualquierla que actue de contenedor de este componente por lo tanto nos vale el propio MainActivity
       this.setAnimationFade(AnimationUtils.loadAnimation(this,R.anim.txt_fade));
       this.setAnimationRotate(AnimationUtils.loadAnimation(this,R.anim.btn_rotate));
       this.setAnimationTranslate(AnimationUtils.loadAnimation(this,R.anim.img_translate));
       this.setAnimationTranslate2(AnimationUtils.loadAnimation(this,R.anim.img_translate2));
       this.setAnimationTranslate2_2(AnimationUtils.loadAnimation(this,R.anim.imgtranslate2_2));
       //Seteamos el listener de la animación que será nuestro MainActivityEvents
       this.getAnimationFade().setAnimationListener(this.getMainActivityEvents());
       this.getAnimationRotate().setAnimationListener(this.getMainActivityEvents());
        this.getAnimationTranslate().setAnimationListener(this.getMainActivityEvents());
        this.getAnimationTranslate2_2().setAnimationListener(this.getMainActivityEvents());

    }

    public TextView getTxtOcultar() {
        return txtOcultar;
    }

    public void setTxtOcultar(TextView txtOcultar) {
        this.txtOcultar = txtOcultar;
    }

    public Button getBtnRotar() {
        return btnRotar;
    }

    public void setBtnRotar(Button btnRotar) {
        this.btnRotar = btnRotar;
    }

    public ImageView getImgDesplazar() {
        return imgDesplazar;
    }

    public void setImgDesplazar(ImageView imgDesplazar) {
        this.imgDesplazar = imgDesplazar;
    }

    public MainActivityEvents getMainActivityEvents() {
        return mainActivityEvents;
    }

    public void setMainActivityEvents(MainActivityEvents mainActivityEvents) {
        this.mainActivityEvents = mainActivityEvents;
    }

    public Animation getAnimationFade() {
        return animationFade;
    }

    public void setAnimationFade(Animation animationFade) {
        this.animationFade = animationFade;
    }

    public Animation getAnimationRotate() {
        return animationRotate;
    }

    public void setAnimationRotate(Animation animationRotate) {
        this.animationRotate = animationRotate;
    }

    public Animation getAnimationTranslate() {
        return animationTranslate;
    }

    public void setAnimationTranslate(Animation animationTranslate) {
        this.animationTranslate = animationTranslate;
    }

    public ImageView getImgDesplazar2() {
        return imgDesplazar2;
    }

    public void setImgDesplazar2(ImageView imgDesplazar2) {
        this.imgDesplazar2 = imgDesplazar2;
    }

    public Animation getAnimationTranslate2() {
        return animationTranslate2;
    }

    public void setAnimationTranslate2(Animation animationTranslate2) {
        this.animationTranslate2 = animationTranslate2;
    }

    public Animation getAnimationTranslate2_2() {
        return animationTranslate2_2;
    }

    public void setAnimationTranslate2_2(Animation animationTranslate2_2) {
        this.animationTranslate2_2 = animationTranslate2_2;
    }
}
