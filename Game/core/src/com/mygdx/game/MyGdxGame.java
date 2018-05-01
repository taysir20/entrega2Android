package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.game.Game;
import com.mygdx.game.game.Pelota;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Game game;


	/*
	Clase que representa el motor de nuestro videojuego.
	El método create representa todo aquello (elementos)
	que queremos crear para ser utilizado en nuestro en juego
	 */
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		game = new Game();
	}
/*
Método de pintado que se ejecuta constantemente para ir pintando los elementos
y actualizandolos en pantalla
 */
	@Override
	public void render () {
		/*
		Estos métodos propios de Gdx se encargan de limpiar y repintar constantemente el entorno del videojuego
		 */
		Gdx.gl.glClearColor(1, 0, 0, 1); // pinta el fondo de un color determinado limpiando el anterior color
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // define el tipo de buffer bit
		batch.begin();//comienza el pintado

		batch.draw(img, 0, 0); //pinta el elemento en la posición indicada
		game.draw(batch);
		batch.end();//finaliza el pintando
		/*
		being y end se usan apra mejorar el flujo de pintado y que no quede nada en memoria,
		se conoce como doble buffer
		 */


	}

	/*
	Dispose es un destroy que limpia de la memoria las cosas cuando salimos de
	la apliacación
	 */
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
