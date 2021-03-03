package com.mygdx.graphics;

import com.badlogic.gdx.graphics.Texture;

public class GameTexture {

    public Texture tex;
    public float x;
    public float y;
    public float velx;
    public float vely;
    public float accx;
    public float accy;

    public GameTexture(Texture texture, float X, float Y){
        tex = texture;
        x = X;
        y = Y;
        velx = 0;
        vely = 0;
        accx = 0;
        accy = 0;
    }
}
