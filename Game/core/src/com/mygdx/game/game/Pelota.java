package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by tay on 5/2/18.
 */

/*
El fragmento game contendrá todo aquello que queremos pintar en el videojuego.
En este caso queremos pintar una pelota por tanto para pìntar el sprite de pelota
vamos a crear una clase pelota que contendrá un método que se llama draw y que recibirá por parámetro
este sprite y lo pintará.
 */

public class Pelota {
    Texture img; // variable que representa la img de la pelota que es de tipo textura
    int x, y, z;

    public Pelota() {
        img = new Texture("pelota.png");
        x = 50;
        y = 50;
        z = 50;
    }

    public void draw(SpriteBatch spriteBatch) {
        /*
        Hacemos uso del acelerómetro, dependiendo de la posición que nos marque este, iremos modificando la x e y
         */

       if(Gdx.input.getAccelerometerY()<0){
           x +=(Gdx.input.getAccelerometerY());
       }else if(Gdx.input.getAccelerometerY()>0){
           x +=Gdx.input.getAccelerometerY();
       }

       if(Gdx.input.getAccelerometerX()<0){

           y-=Gdx.input.getAccelerometerX();

       }else if(Gdx.input.getAccelerometerX()>0){
           y-= (Gdx.input.getAccelerometerX());
       }


        spriteBatch.draw(img, x, y, 100, 100);

    }

    public Texture getImg() {
        return img;
    }

    public void setImg(Texture img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
