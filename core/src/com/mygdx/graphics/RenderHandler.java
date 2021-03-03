package com.mygdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.mygdx.game.MainGame;
import jdk.tools.jaotc.Main;

public class RenderHandler {

    private SpriteBatch batch;
    private BitmapFont font;
    private ShaderProgram shader;
    private String vertexShader = "attribute vec4 a_position;    \n" +
            "attribute vec4 a_color;\n" +
            "attribute vec2 a_texCoord0;\n" +
            "uniform mat4 u_projTrans;\n" +
            "varying vec4 v_color;" +
            "varying vec2 v_texCoords;" +
            "void main()                  \n" +
            "{                            \n" +
            "   v_color = vec4(1, 1, 1, 1); \n" +
            "   v_texCoords = a_texCoord0; \n" +
            "   gl_Position =  u_projTrans * a_position;  \n"      +
            "}                            \n" ;
    private String fragmentShader = "#ifdef GL_ES\n" +
            "precision mediump float;\n" +
            "#endif\n" +
            "varying vec4 v_color;\n" +
            "varying vec2 v_texCoords;\n" +
            "uniform sampler2D u_texture;\n" +
            "void main()                                  \n" +
            "{                                            \n" +
            "  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n" +
            "}";

    public RenderHandler(SpriteBatch Batch){
        batch = Batch;
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        parameter.color = Color.BLACK;
        font = MainGame.fg.generateFont(parameter); // font size 12 pixels
        shader = new ShaderProgram(vertexShader, fragmentShader);
    }

    public void renderTick(){
        calcChanges();
        renderImage();
    }

    private void calcChanges(){
        if(MainGame.ih.touching){
            GameTexture t = MainGame.th.getAllTextures().get("ship1");
                t.x = MainGame.ih.touchX - t.tex.getHeight()/2;
                t.y = MainGame.ih.touchY - t.tex.getWidth()/2;

        }
    }

    private void renderImage(){
        batch.begin();

            GameTexture t = MainGame.th.getAllTextures().get("ship1");

            applyShader(t.tex);

            batch.draw(t.tex, t.x, t.y);

            writeText("X = " +  MainGame.ih.touchX + " Y=" + MainGame.ih.touchY + " resX=" + Gdx.graphics.getHeight() + " resY=" + Gdx.graphics.getWidth(), 100, 100);

        batch.end();
    }

    private void applyShader(Texture tex){
        Mesh mesh = new Mesh(true, 4, 6, VertexAttribute.Position(), VertexAttribute.ColorUnpacked(), VertexAttribute.TexCoords(0));
        mesh.setVertices(new float[]
                {-0.5f, -0.5f, 0, 1, 1, 1, 1, 0, 1,
                        0.5f, -0.5f, 0, 1, 1, 1, 1, 1, 1,
                        0.5f, 0.5f, 0, 1, 1, 1, 1, 1, 0,
                        -0.5f, 0.5f, 0, 1, 1, 1, 1, 0, 0});
        mesh.setIndices(new short[] {0, 1, 2, 2, 3, 0});
        tex.bind();
        shader.begin();
        shader.setUniformMatrix("u_projTrans", MainGame.camera.combined);
        shader.setUniformi("u_texture", 0);
        mesh.render(shader, GL20.GL_TRIANGLES);
        shader.end();
    }

    public void writeText(String text, float x, float y){

        font.draw(batch, text, x, y);

    }

    public void dispose(){
        for(GameTexture tex : MainGame.th.getAllTextures().values()){
            tex.tex.dispose();
        }
        font.dispose();
    }
}
