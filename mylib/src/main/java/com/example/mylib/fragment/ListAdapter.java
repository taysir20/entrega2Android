package com.example.mylib.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mylib.R;

/**
 * Created by tay on 28/11/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //creamos el molde de la celda y lo asociamos con su xml que es el que hemos creado llamado list_cell_layout.
        //Este xml es la parte visual de la celda (lo que queremos que contenga)
        // Linkamos el ViewHolder(controlador) con su xml(vista).Para hacerlo se tiene que usar el método inflate.
        //En Android se considera que los xml son una especie de globos desinchados que hay que hinchar para darle una forma
        //antes de meter los contenidos.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell_layout, null); // se pone null porque no tiene padre de jerarquía. Normalmente lo dejaremos a null.
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {//pinta el contenido de los elementos de la celda a través del molde y para cada posición de las celda.
    if(position==0){
        holder.getTxtNombre().setText("Yony");
    }else if(position==1){
        holder.getTxtNombre().setText("Javi");
    }

    }

    @Override
    public int getItemCount() {
        return 2;
    } //extendemos del padre genérico adapter.
    //Necesitamos que recoja datos de tipo VH--> View Holder que es un dataholder basicamente que contiene los datos de la colección



    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNombre;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtNombre = (TextView) itemView.findViewById(R.id.txtNombre);
        }

        public TextView getTxtNombre() {
            return txtNombre;
        }

        public void setTxtNombre(TextView txtNombre) {
            this.txtNombre = txtNombre;
        }
    }
}
