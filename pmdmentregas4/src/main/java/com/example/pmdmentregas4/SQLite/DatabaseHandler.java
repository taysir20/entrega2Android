package com.example.pmdmentregas4.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pmdmentregas4.entity.RemoteMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tay on 29/1/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    /*
    Esta clase es un gestor que encarga de administrar la bbdd SQ Lite
     */

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 902;

    // Database Name
    private static final String DATABASE_NAME = "Messages";

    // Contacts table name
    private static final String TABLE_MESSAGES = "Message";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String title = "title";
    private static final String sender_id = "sender_id";
    private static final String receiver_id = "receiver_id";
    private static final String message = "message";
    private static final String urlImage = "urlImage";
    private static final String action = "action";
    private static final String action_destination = "action_destination";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getReadableDatabase();
        System.out.println("------------------------------------------->>>>>>>>>dentro del handler del SQlite Databse");
          /*
        Cuando se hace el new de DatabaseHandel se llama al super del que hereda que es SQLiteOpenHelper y se comprueba
        si esa versión de la bbdd existe. Si existe entonces no se llama a nada más y si no eciste se llama al oncreate.
        Si ya exister pero hay una versión distinta de la bbdd entonces se llama al onUpgrade.
         */
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("------------------------------------------->>>>>>>>>dentro del onCreate del SQlite Databse");
        String CREATE_MESSAGES_TABLE = " CREATE TABLE " + TABLE_MESSAGES + "("
                + sender_id + " TEXT," + receiver_id + " TEXT," + title + " TEXT," + message + " TEXT," + urlImage + " TEXT," + action + " TEXT," + action_destination + " TEXT" + ")";
        db.execSQL(CREATE_MESSAGES_TABLE);
        System.out.println("---------------->>>>>>>>>>>>>>>>>>>>>>>EJECUTA ELA CREACIÓN DE LA BBDD");

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("------------------------>>>>>>>>>>>>>>>>>>se borra????");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);

        // Create tables again
        // onCreate(db);
    }


    ////MÉTODOS CRUD

    //añadir nuevo Mensaje
    public void addMessage(RemoteMessage remoteMessage) {
        SQLiteDatabase db = this.getWritableDatabase(); // para poder escribir en la bbdd SQLite hay que pedir permisos de escritura

        ContentValues values = new ContentValues(); //ContentValues es un hashMap de tipo ContentValues que se usa para recoger los datos que se quieren instar del objeto
        values.put(sender_id, remoteMessage.getSender_id()); // Contact Name
        values.put(receiver_id, remoteMessage.getReceiver_id()); // Contact Phone Number
        values.put(title, remoteMessage.getTitle());
        values.put(message, remoteMessage.getMessage());
        values.put(urlImage, remoteMessage.getUrlImage());
        values.put(action, remoteMessage.getAction());
        values.put(action_destination, remoteMessage.getAction_destination());
        // Inserting Row
        db.insert(TABLE_MESSAGES, null, values);
        db.close(); // Closing database connection

        //Para completar el insert tenemos que hacer un .close() dado que funciona a modo de transacción y es como si hicieramos un commit
    }

    /*
    Obtener un Mensaje ( En nuestro caso como no estamos dando una id identificadora para cada mensaje pues
    los queremos meter dentro del InboxStyle no pasaremos una id si no que borraremos todo el contenido de la tabla
    cuando se presiona sobre el grupo de notificaciones.
     */
    public RemoteMessage getMessage(int id) {
        SQLiteDatabase db = this.getReadableDatabase(); //tenemos que pedir permisos para leer de la bbdd

        //Un cursor es igual que un ResultSet de SQL que contiene los resultados obtenidos de la consulta y el cuál hay que interar para sacarlos
        Cursor cursor = db.query(TABLE_MESSAGES, new String[]{KEY_ID,
                        title, sender_id, receiver_id, message, urlImage, action, action_destination}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst(); //situa el cursor en el primer resultado para sacar los elementos a partir del primero


        //Estos resutlados se seteran en un nuevo obejto de tipo Remotemessage para usarlo como queramos
        RemoteMessage remoteMessage = new RemoteMessage(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),
                cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        // return remoteMessage
        return remoteMessage;
    }



    /*
    Método para obtener de golpe todos los mensajes de la bbdd SQ lite y guardarlos en un arraylist de tipo RemoteMessage
     */

    public List<RemoteMessage> getAllRemoteMessage() {
        List<RemoteMessage> messageList = new ArrayList<>();
        // Select All Query
        String selectQuery = " SELECT  * FROM " + TABLE_MESSAGES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                RemoteMessage remoteMessage = new RemoteMessage();
                remoteMessage.setSender_id(Integer.parseInt(cursor.getString(0)));
                remoteMessage.setReceiver_id(Integer.parseInt(cursor.getString(1)));
                remoteMessage.setTitle(cursor.getString(2));
                remoteMessage.setMessage(cursor.getString(3));
                remoteMessage.setUrlImage(cursor.getString(4));
                remoteMessage.setAction(cursor.getString(5));
                remoteMessage.setAction_destination(cursor.getString(6));
                // Adding contact to list
                messageList.add(remoteMessage);
            } while (cursor.moveToNext());
        }

        // return contact list
        return messageList;
    }


    //Método que devuelve el número de mensajes
    public int getMessagesCount() {


        String countQuery = " SELECT  * FROM " + TABLE_MESSAGES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = -1;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    //Método para actualizar un mensaje, en nuestro vcaso como no hemos puesto identificadores para cada id entonces no vamos a actualizar, como hemos dicho borraremos todo el contenido de la bbdd
    public int updateMessage(RemoteMessage remoteMessage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues(); //ContentValues es un hashMap de tipo ContentValues que se usa para recoger los datos que se quieren instar del objeto
        values.put(sender_id, remoteMessage.getSender_id());
        values.put(receiver_id, remoteMessage.getReceiver_id());
        values.put(title, remoteMessage.getTitle());
        values.put(message, remoteMessage.getMessage());
        values.put(urlImage, remoteMessage.getUrlImage());
        values.put(action, remoteMessage.getAction());
        values.put(action_destination, remoteMessage.getAction_destination());

        ///Actualizamos la fila que corresponde a un objeto de tipo RemoteMessage
        return db.update(TABLE_MESSAGES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(/*remoteMessage.getID()*/0)});
    }

    //Método para borrar un mensaje, de nuevo lo mismop no vamos a borrar por ids porque no tenemos identificadores
    public void deleteMessage(RemoteMessage remoteMessage) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MESSAGES, KEY_ID + " = ?",
                new String[]{String.valueOf(/*remoteMessage.getID()*/0)});
        db.close();
    }

    public void deleteAllMessage() {


        SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
        db.delete(TABLE_MESSAGES, null, null);

        getMessagesCount();
    }


}
