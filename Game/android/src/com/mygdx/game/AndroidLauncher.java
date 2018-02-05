package com.mygdx.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		/*
		El super.onCreate llama al padre y le pasa la última instancia que se ejecutó del juego,
		es decir es para volver al juego en el momento en que está en pausa.
		Con AndroidApplicationConfiguration config = new AndroidApplicationConfiguration(), haciendo un new
		cargamos una variable que almacena toda la configuración necesaria para poder inicializar el juego.
		con initialize pasamos como parametro un new de MYgdxgame para crear un nuevo escenario de juego
		y apsamos la configuración necersaria de la que hemos hecho un new
		Como podemos ver tenemos dos módulos, uno el de java que contendrá todo el contenido de java
		y otro que es el core y contiene el motor del juego para funcionar.
		Por eso se hace un new de MyGdxGame para inicializar el motor
		 */
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGdxGame(), config);
	}
}
