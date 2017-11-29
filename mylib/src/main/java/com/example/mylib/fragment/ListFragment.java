package com.example.mylib.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylib.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    private RecyclerView myLista;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        myLista = (RecyclerView) view.findViewById(R.id.myList);
        //Le seteamos al RecyclerView un Layout. En este caso le seteamos por ejemplo un GridLayout
        //Al ser un recyclerView puedo poner cualquier tipo de elemento.
        myLista.setLayoutManager(new GridLayoutManager(getContext(),2)); // spanCount es el número de columnas. Esto hace directamente que sea una colección, dado que una tabla solo tiene una columna.
        //Ahora creamos un adapter que va a ser el encargado de rellenar con datos la colección.
        //Creamos el  nuevo ListAdapter y pintara todo el contenido de list Adapter
        myLista.setAdapter(new ListAdapter());

        return view;
    }

    public RecyclerView getMyLista() {
        return myLista;
    }

    public void setMilista(RecyclerView myLista) {
        this.myLista = myLista;
    }
}
