package com.example.examenandroid.adapter;

import android.view.View;

/**
 * Created by tay on 19/12/17.
 */

public class ListViewHolderEvents implements View.OnClickListener {
    private MyViewHolder myViewHolder;


    public ListViewHolderEvents(MyViewHolder myViewHolder) {
        this.myViewHolder = myViewHolder;
    }

    public MyViewHolder getMyViewHolder() {
        return myViewHolder;
    }

    public void setMyViewHolder(MyViewHolder myViewHolder) {
        this.myViewHolder = myViewHolder;
    }

    @Override
    public void onClick(View view) {
        /*
        Cada vez que se pinche una celda entyonces se llamañra a quién tenga implementado el mñetodo listAdapterCellClicked
        que en este caso es el SecondActivity
         */
        this.getMyViewHolder().getListAdapterListener().listAdapterCellClicked(this.getMyViewHolder());
    }
}
