package com.example.examenandroid.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.examenandroid.R;
import com.example.examenandroid.entity.Coche;

import java.util.ArrayList;

/**
 * Created by tay on 19/12/17.
 */

public class ListAdapter extends RecyclerView.Adapter<MyViewHolder>{ //extendemos del Recycler view importado en el gradle.
    //El adapter requiere de un viewholder que es el que va a llenar de contenidos la lista (MyViewHolder)
    private ArrayList<Coche> contenidoLista; //declaramos un array que contiene contenido que queremos que s epinte en las celdas de la lista
    private ListAdapterListener listAdapterListener; // declaramos el listener del ListAdapter para recibir los eventos cuando pinchamos una celda
    private Context context; // esta variable la creamos dado que la librería Glide para cargar imagenes desde firebase necesita una variable de tipo contexto
    public ListAdapter(ArrayList<Coche> contenidoLista, Context context) { // ListAdapter recibe como parámetro el context de Glide, en nuestro caso el contexto es el padre donde se encuentra que al fin de cuentas el list se encuentra en el second Activity
        this.contenidoLista = contenidoLista;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//creamos el molde de la celda y lo asociamos con su xml que es el que hemos creado llamado list_cell_layout.
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
        holder.getTxtMarca().setText(this.getContenidoLista().get(position).marca);
        holder.getTxtModelo().setText(this.getContenidoLista().get(position).modelo);
        holder.setListAdapterListener(listAdapterListener); // establecemos el escuchador del holder que será el secondActivityEvents
         /*
        En glide, tenemos una función que recibe por parámetro la url de la imagen, la descarga y la
        introduce en la caché. Po último, la sete a la variable img que tengamos declarada.
         */
        Glide.with(this.getContext()).load(this.getContenidoLista().get(position).urlImg).into(holder.getImageViewCoche());

    }

    @Override
    public int getItemCount() {
        return this.getContenidoLista().size();// decimos que pinte tantas columnas de dos en dos como pusimos antes como size sea del arraylist
    }

    public ArrayList<Coche> getContenidoLista() {
        return contenidoLista;
    }

    public void setContenidoLista(ArrayList<Coche> contenidoLista) {
        this.contenidoLista = contenidoLista;
    }

    public ListAdapterListener getListAdapterListener() {
        return listAdapterListener;
    }

    public void setListAdapterListener(ListAdapterListener listAdapterListener) {
        this.listAdapterListener = listAdapterListener;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
