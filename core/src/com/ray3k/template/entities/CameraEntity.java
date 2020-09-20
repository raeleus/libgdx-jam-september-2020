package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;

import static com.ray3k.template.screens.GameScreen.*;

public class CameraEntity extends Entity {
    private OrthographicCamera camera;
    private PlayerEntity player;
    
    public CameraEntity(OrthographicCamera camera, PlayerEntity player) {
        this.camera = camera;
        this.player = player;
    }
    
    @Override
    public void create() {
    
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        setPosition(player.x, player.y);
        camera.position.set(x, y, 0);
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
    
    }
}
