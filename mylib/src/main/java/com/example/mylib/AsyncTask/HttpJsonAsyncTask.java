package com.example.mylib.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tay on 15/1/18.
 */

public class HttpJsonAsyncTask extends AsyncTask<String, Integer, String>{
    /*
    Para la tarea asíncrona que descarga un JSON vamos a usar como parámetros de entrada del
    doInBackground un string que será la url desde la que queremos descargar el Json.
    El onProgressUpdate recibe un int que usaremos apra marcar el porcentaje del progreso.
    Y por último, en el onPostExecute recibimos desde el doInBackground un string que es el resultado del json
    en string.
     */
    private HttpJsonAsyncTaskListener httpJsonAsyncTaskListener; //Listener para las notificaciones del onPostExecute
    public static final String REQUEST_METHOD = "GET"; // CONSTANTE QUE INDICA EL MÉTODO DE PETICIÓN. Usaremos el GET para pasarlo por URL
    public static final int READ_TIMEOUT = 15000; // cte que indica el tiempo máximo de lectura
    public static final int CONNECTION_TIMEOUT = 15000; // cte que indica el tiempo máximo de conexión
    /*
    Tanto el Read_timeout y el connection_timeout si se llega a estos 15 segundos y no se deuvelve nada entonces se cancela
    la operación.
     */

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... urls) {
        System.out.println("La URL QUE LLEGA ES: " + urls[0]);
        Log.v("hola","hola");
        // como podemos pasar varias urls ponemos la posición de la url que queremos pasar o un for que las recorra todas
        String result="";
        String stringUrl;
        String inputLine;
        for (int i=0; i<urls.length; i++) {
            stringUrl = urls[0];

            try {
                //Creación de un URL Object a partir de la url que hemos enviado por parámetro
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Seteamos el método d eenvío(get o post) y las restricción de timeout
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);
                System.out.print("<------------------------------JSON-----------------------------------_>");

                //Nos conectamos a esta URL Object
                connection.connect();
                //Creamos un inputstreamreader para recibir los datos que obtenemos de la conexión a esta URL Oject que nos devovlerá el JSON
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Creamos un stringbuffer para poder leer lo que nos han devuelve en el inputStream que hemos almacenado
                /*
                ¿Cuál es la diferencia entre un StringBuilder y un StringBuffer?
                    La diferencia entre un StringBuilder y un StringBuffer es que un StringBuffer provee métodos sincronizados por lo que es seguro usarlos en un ambiente multi-hilos. Los métodos son sincronizados donde sea necesario, para que las operaciones en cualquier instancia de un StringBuffer puedan comportarse como si ocurrieran en serie de manera consistente con el orden de las llamadas a los métodos hechas por cada hilo.

                    Generalmente es más común el uso de StringBuilder. Usar StringBuffer en un ambiente de un solo hilo de ejecución no tiene sentido, y al tener métodos sincronizados, es más lenta en general.

                    Así que ahí tienen algo que nunca me voy a olvidar, la diferencia entre StringBuilder y StringBuffer 😛
                    Lo bueno de posts como éste son reafirmar conocimientos, y que generalmente se genera un poco más de conocimiento con el intercambio con los comentarios (bienvenidos sean comentarios, correcciones y demás aportes sobre el tema).
                 */
                //Usaremos StringBuffer para tratar estas cadenas que obtenemos
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Cerramos el BufferReader y el streamReader para limpiar
                reader.close();
                streamReader.close();
                //Seteamos al resultado el stringBuilder casteado a string
                result = stringBuilder.toString();
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }



        }




        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        this.getHttpJsonAsyncTaskListener().FinalTasknotification(s);

    }


    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    public HttpJsonAsyncTaskListener getHttpJsonAsyncTaskListener() {
        return httpJsonAsyncTaskListener;
    }

    public void setHttpJsonAsyncTaskListener(HttpJsonAsyncTaskListener httpJsonAsyncTaskListener) {
        this.httpJsonAsyncTaskListener = httpJsonAsyncTaskListener;
    }
}




































































































//PLATANO GUAPETON
