package com.example.pmdmentregas4.entity;

/**
 * Created by tay on 29/1/18.
 */

public class RemoteMessage {
    /*
    Clase para la creación de objetos tipo RemoteMessage para luego usarlos para agruparlos
     */

    private int sender_id;
    private int receiver_id;
    private String title;
    private String message;
    private String urlImage;
    private String action;
    private String action_destination;




    public RemoteMessage() {
    }

    public RemoteMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public RemoteMessage(int sender_id, int receiver_id, String title, String message) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.title = title;
        this.message = message;
    }

    public RemoteMessage(String title, String message, String urlImage, String action, String action_destination) {
        this.title = title;
        this.message = message;
        this.urlImage = urlImage;
        this.action = action;
        this.action_destination = action_destination;
    }

    public RemoteMessage(int sender_id, int receiver_id, String title, String message, String urlImage, String action, String action_destination) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.title = title;
        this.message = message;
        this.urlImage = urlImage;
        this.action = action;
        this.action_destination = action_destination;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction_destination() {
        return action_destination;
    }

    public void setAction_destination(String action_destination) {
        this.action_destination = action_destination;
    }
}
