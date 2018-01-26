package com.example.dintentregas3;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by tay on 24/1/18.
 */

public class MainActivityEvents implements View.OnClickListener, Animation.AnimationListener {
    private MainActivity mainActivity;
    private boolean animation;
    public MainActivityEvents(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        animation=false;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRotar) {
            this.mainActivity.getBtnRotar().startAnimation(this.mainActivity.getAnimationRotate());


        } else if (view.getId() == R.id.imgDesplazar) {

            this.mainActivity.getImgDesplazar().startAnimation(this.mainActivity.getAnimationTranslate());

        } else if (view.getId() == R.id.txtOcultar) {
            this.mainActivity.getTxtOcultar().startAnimation(this.mainActivity.getAnimationFade());


        }else  if(view.getId() == R.id.imgTranslate2){
            /*
            Estamos obteniendo los parámetros de la imagen 2 que está dentor de un constrainLayout en este caso para así setearle la posición de la img y que vaya acorde con la transición
             */
            if(animation==false){



                this.mainActivity.getImgDesplazar2().startAnimation(this.mainActivity.getAnimationTranslate2());
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.getMainActivity().getImgDesplazar2().getLayoutParams();
                //Seteamos la posición a la que se quedaría la animación  para que coincidan
                layoutParams.leftMargin+=(int)(this.mainActivity.getImgDesplazar2().getWidth()*5);
                this.mainActivity.getImgDesplazar2().setLayoutParams(layoutParams);


                animation=true;



            }else{
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.getMainActivity().getImgDesplazar2().getLayoutParams();
                //Seteamos la posición a la que se quedaría la animación  para que coincidan
                layoutParams.leftMargin-=(int)(this.mainActivity.getImgDesplazar2().getWidth()*5);
                this.mainActivity.getImgDesplazar2().setLayoutParams(layoutParams);
                this.mainActivity.getImgDesplazar2().startAnimation(this.mainActivity.getAnimationTranslate2_2());

                animation=false;
            }

        }
    }

    /*
    Meidante el listener de Animation podemos hacer uso de los siguiente métodos muy comodos para
    setear por ejemplo en 'onAnimationEnd' la posición de forma programatica del elemento al acabar la
    animación como por ejemplo en las transiciones de un punto a otro.
     */

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        System.out.println("Animación finalizada: " + animation);




    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
