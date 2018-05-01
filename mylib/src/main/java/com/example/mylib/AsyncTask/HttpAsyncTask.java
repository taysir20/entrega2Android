package com.example.mylib.AsyncTask;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by tay on 15/1/18.
 */

public class HttpAsyncTask extends AsyncTask<String,Integer,String[]>{
    /*
    Las tareas asíncronas sirve para crear pequeños hilos de ejecución.
    Para poder crear una tarea asínscrona, lo mejor es crear cada una en una clase distintas.
    Para ello extenderemos del padsre AsyncTask para heredar sus métodos.
    El método doInBackground es onligatorio de implementar dado que al extender de AsyncTask, este
    método es un método de una clase abstracta y nos obliga.
    Cuando extendemos de AyncTask podemos especificar los parámetros que llevaron los métodos de los que
    extenderemos. Son 3, el primero es el parámetro de entrada del método de doInBackground, el segundo
    es el de entrada del onProgressUpdate y por último, el de salida del doInBackground es el de entrada del de
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
    //Preparado para cuando queramos descargar url como imagenes
    @Override
    protected String[] doInBackground(String... strings) {
        int count;
        try {
            String root = Environment.getExternalStorageDirectory().toString();

            System.out.println("Downloading");
            URL url = new URL(strings[0]);

            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lenghtOfFile = conection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file

            OutputStream output = new FileOutputStream(root+"/downloadedfile.jpg");
            byte data[] = new byte[1024];

            long total = 0;
            while ((count = input.read(data)) != -1) {
                total += count;

                // writing data to file
                output.write(data, 0, count);

            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }
    //Método que notifica el estado de la tarea asíncrona
    @Override
    protected void onProgressUpdate(Integer... integers) {
        super.onProgressUpdate(integers);
    }
    //Método que devuelve el resultado final de la tarea asíncrona.
    @Override
    protected void onPostExecute(String... strings) {
        super.onPostExecute(strings);
    }
    //Método llamado cuando se cancela o cancelamos nosotros mismos la tarea asíncrona.
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
