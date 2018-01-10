package com.example.dintentregas;

import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * Created by tay on 10/1/18.
 */

public class MainActivityEvents implements View.OnClickListener{


    private MainActivity mainActivity;

    public MainActivityEvents(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.fab){
            FragmentTransaction transition = mainActivity.getSupportFragmentManager().beginTransaction();
            if(this.getMainActivity().getHelloWorldFragment().isVisible()==false){
                transition.show(mainActivity.getHelloWorldFragment());
                transition.commit();
            }else{
                transition.hide(mainActivity.getHelloWorldFragment());
                transition.commit();
            }

        }
    }
}
