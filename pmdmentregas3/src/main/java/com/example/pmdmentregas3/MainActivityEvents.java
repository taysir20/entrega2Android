package com.example.pmdmentregas3;

import android.content.Intent;

/**
 * Created by tay on 19/1/18.
 */

public class MainActivityEvents {
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
    public void iniciarAppSecondActivity(){
        Intent intent = new Intent(mainActivity, SecondActivity.class);
        mainActivity.startActivity(intent);
        mainActivity.finish();
    }
    public void iniciarAppThirdActivity(){
        Intent intent = new Intent(mainActivity, ThirdActivity.class);
        mainActivity.startActivity(intent);
        mainActivity.finish();
    }
}
