package com.example.examenandroid.fragment;

import android.view.View;

import com.example.examenandroid.R;

/**
 * Created by tay on 19/12/17.
 */

public class InfoCocheEvents implements View.OnClickListener {
    private InfoCoche infoCoche;

    public InfoCocheEvents(InfoCoche infoCoche) {
        this.infoCoche = infoCoche;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnVolver){
            if(this.infoCoche.getInfoCocheListener()!=null)
                System.out.println("click volver");
            this.infoCoche.getInfoCocheListener().volverAtras();
        }
    }

    public InfoCoche getInfoCoche() {
        return infoCoche;
    }

    public void setInfoCoche(InfoCoche infoCoche) {
        this.infoCoche = infoCoche;
    }
}
