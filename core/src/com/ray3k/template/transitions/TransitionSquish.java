package com.ray3k.template.transitions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ray3k.template.*;

public class TransitionSquish implements Transition {
    private TransitionEngine te;
    private Interpolation interpolation;
    
    public TransitionSquish(Interpolation interpolation) {
        this.interpolation = interpolation;
        te = JamGame.transitionEngine;
    }
    
    @Override
    public void create() {
        te.frameBuffer.begin();
        te.screen.draw(0);
        te.frameBuffer.end();
    
        te.jamGame.setScreen(te.nextScreen);
        te.nextFrameBuffer.begin();
        te.nextScreen.act(0);
        te.nextScreen.draw(0);
        te.nextFrameBuffer.end();
    }
    
    @Override
    public void resize(int width, int height) {
        create();
    }
    
    @Override
    public void act() {
    
    }
    
    @Override
    public void draw(Batch batch, float delta) {
        float normal = interpolation.apply((te.time + delta) / te.duration);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        te.textureRegion.setRegion(new TextureRegion(te.frameBuffer.getFbo().getColorBufferTexture()));
        te.textureRegion.flip(false, true);

        batch.setColor(1, 1, 1, 1);
        batch.draw(te.textureRegion, 0, 0, (1 - normal) * te.textureRegion.getRegionWidth(), te.textureRegion.getRegionHeight());

        te.textureRegion.setRegion(new TextureRegion(te.nextFrameBuffer.getFbo().getColorBufferTexture()));
        te.textureRegion.flip(false, true);
        
        batch.draw(te.textureRegion, Gdx.graphics.getWidth() * (1 - normal), 0, normal * te.textureRegion.getRegionWidth(), te.textureRegion.getRegionHeight());
    }
    
    @Override
    public void end() {
    
    }
}
