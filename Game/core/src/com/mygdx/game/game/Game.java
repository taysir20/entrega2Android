package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.TextField;

/**
 * Created by tay on 5/2/18.
 */

public class Game {

    private Pelota pelota;
    private Canasta canasta;

    public Game() {
        pelota = new Pelota();
        canasta = new Canasta();

    }

    public void draw(SpriteBatch spriteBatch) {

        this.pelota.draw(spriteBatch);
        this.canasta.draw(spriteBatch);

    }

    public Pelota getPelota() {
        return pelota;
    }

    public void setPelota(Pelota pelota) {
        this.pelota = pelota;
    }
}
