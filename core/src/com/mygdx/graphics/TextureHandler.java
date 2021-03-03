package com.mygdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;


import java.io.File;
import java.util.HashMap;

public class TextureHandler {

    private HashMap<String, GameTexture> textures;


    public TextureHandler(){
        textures = new HashMap<String, GameTexture>();

        loadTextures();
    }

    public void loadTextures(){
        textures.put("badlogic", new GameTexture(new Texture("badlogic.jpg"),0,0));
        textures.put("ship1", new GameTexture(new Texture("ship1.png"), 0,0));

    }

    public HashMap<String, GameTexture> getAllTextures(){
        return textures;
    }
}
