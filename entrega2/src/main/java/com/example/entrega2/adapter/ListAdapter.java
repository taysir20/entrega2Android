package com.example.entrega2.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.entrega2.R;
import com.example.entrega2.entity.Coche;

import java.util.ArrayList;

/**
 * Created by tay on 28/11/17.
 */


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private ArrayList<Coche> contenidoLista; //declaramos un array que contiene contenido que queremos que s epinte en las celdas de la lista

    public ListAdapter(ArrayList<Coche> contenidoLista) {
        System.out.println("contenidoLista: " + contenidoLista);
        this.contenidoLista = contenidoLista;
    }

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
        // holder.getTxtNombre().setText(this.getContenidoLista().get(position)); // por cada posición se pinta una posición del arraylist
        holder.getTxtMarca().setText(this.getContenidoLista().get(position).marca);
        holder.getTxtModelo().setText(this.getContenidoLista().get(position).modelo);


    /*if(position==0){
        holder.getTxtNombre().setText("Yony");
    }else if(position==1){
        holder.getTxtNombre().setText("Javi");
    }
    */

    }

    @Override
    public int getItemCount() {
        return this.getContenidoLista().size();// decimos que pinte tantas columnas de dos en dos como pusimos antes como size sea del arraylist
    } //extendemos del padre genérico adapter.
    //Necesitamos que recoja datos de tipo VH--> View Holder que es un dataholder basicamente que contiene los datos de la colección


    public ArrayList<Coche> getContenidoLista() {
        return contenidoLista;
    }

    public void setContenidoLista(ArrayList<Coche> contenidoLista) {
        this.contenidoLista = contenidoLista;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMarca;
        public TextView txtModelo;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtMarca = (TextView) itemView.findViewById(R.id.txtMarca);
            this.txtModelo = (TextView) itemView.findViewById(R.id.txtModelo);
        }

        public TextView getTxtMarca() {
            return txtMarca;
        }

        public void setTxtMarca(TextView txtMarca) {
            this.txtMarca = txtMarca;
        }

        public TextView getTxtModelo() {
            return txtModelo;
        }

        public void setTxtModelo(TextView txtModelo) {
            this.txtModelo = txtModelo;
        }
    }
}
