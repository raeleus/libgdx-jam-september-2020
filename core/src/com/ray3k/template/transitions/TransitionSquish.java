package com.ray3k.template.transitions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.*;
import com.ray3k.template.*;

public class TransitionSquish implements Transition {
    private TransitionEngine te;
    private Interpolation interpolation;
    private final Color color = new Color();
    
    public TransitionSquish(Color backgroundColor, Interpolation interpolation) {
        this.color.set(backgroundColor);
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
        float normal = interpolation.apply(MathUtils.clamp((te.time + delta) / te.duration * 2, 0, 1));

        Gdx.gl.glClearColor(color.r, color.g, color.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        te.textureRegion.setRegion(new TextureRegion(te.frameBuffer.getFbo().getColorBufferTexture()));
        te.textureRegion.flip(false, true);

        batch.setColor(1, 1, 1, 1);
        batch.draw(te.textureRegion, Gdx.graphics.getWidth() * normal * .5f, 0, (1 - normal) * te.textureRegion.getRegionWidth(), te.textureRegion.getRegionHeight());
    
        normal = interpolation.apply(MathUtils.clamp((te.time + delta) / te.duration * 2 - 1f, 0, 1));
        
        te.textureRegion.setRegion(new TextureRegion(te.nextFrameBuffer.getFbo().getColorBufferTexture()));
        te.textureRegion.flip(false, true);
        
        batch.draw(te.textureRegion, Gdx.graphics.getWidth() * (1 - normal)  * .5f, 0, normal * te.textureRegion.getRegionWidth(), te.textureRegion.getRegionHeight());
    }
    
    @Override
    public void end() {
    
    }
}
