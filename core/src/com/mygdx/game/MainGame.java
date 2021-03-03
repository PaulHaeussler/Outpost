package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.graphics.RenderHandler;
import com.mygdx.graphics.TextureHandler;

public class MainGame extends ApplicationAdapter {
	private static SpriteBatch batch;
	public static TextureHandler th;
	public static RenderHandler rh;
	public static InputHandler ih;
	public static FreeTypeFontGenerator fg;
	public static OrthographicCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		th = new TextureHandler();
		ih = new InputHandler();
		fg = new FreeTypeFontGenerator(Gdx.files.internal("fonts/AldotheApache.ttf"));
		rh = new RenderHandler(batch);
		camera = new OrthographicCamera();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		rh.renderTick();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		rh.dispose();
		fg.dispose();
	}
}
