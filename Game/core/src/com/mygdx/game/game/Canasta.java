package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by tay on 5/2/18.
 */

public class Canasta {
    Texture img; // variable que representa la img de la pelota que es de tipo textura
    int x, y, z;
    public Canasta() {

        img = new Texture("canasta.png");
        x = 1500;
        y = 600;


    }

    public void draw(SpriteBatch spriteBatch){
        spriteBatch.draw(img, x, y, 300, 350);
    }


}
