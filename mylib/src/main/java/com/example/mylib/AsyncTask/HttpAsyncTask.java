package com.example.mylib.AsyncTask;

import android.os.AsyncTask;

/**
 * Created by tay on 15/1/18.
 */

public class HttpAsyncTask extends AsyncTask{
    /*
    Las tareas asíncronas sirve para crear pequeños hilos de ejecución.
    Para poder crear una tarea asínscrona, lo mejor es crear cada una en una clase distintas.
    Para ello extenderemos del padsre AsyncTask para heredar sus métodos.
    El método doInBackground es onligatorio de implementar dado que al extender de AsyncTask, este
    método es un método de una clase abstracta y nos obliga.
    Cuando extendemos de AyncTask podemos especificar los parámetros que llevaron los métodos de los que
    extenderemos. Son 3, el primero es el parámetro de entrada del método de doInBackground, el segundo
    es el de entrada del onProgressUpdate y po último el de salida del doInBackground es el de entrada del de
    onPostExecute.
    Si espeficiamos estos tres parámetros los parámetros de estos métodos mencionados cambiaran al tipo de
    array con ... que es un array específico para android cuya peculiaridad es que al especificar en los parámeteros
    klos valores de ese array ya se están añadiendo directamente a este.
     */

    /* Método que se ejecuta en primera intancia al hacer el .execute(), llama al método del padre mediante super
    para cargar las variables y métodos necesarios apra inciar la tarea asíncrona.
    */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //Método donde se lleva a cabo la tarea asíncrona. Desde aquí se notifica a onProgressUpdate, onPostExecute y onCancelled
    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }
    //Método que notifica el estado de la tarea asíncrona
    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }
    //Método que devuelve el resultado final de la tarea asíncrona.
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
    //Método llamado cuando se cancela o cancelamos nosotros mismos la tarea asíncrona.
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
