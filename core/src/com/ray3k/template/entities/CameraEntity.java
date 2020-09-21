package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;

public class CameraEntity extends Entity {
    private Viewport viewport;
    private OrthographicCamera camera;
    private PlayerEntity player;
    public float boundaryRight = Float.MAX_VALUE;
    public float boundaryLeft = -Float.MAX_VALUE;
    public float boundaryTop = Float.MAX_VALUE;
    public float boundaryBottom = -Float.MAX_VALUE;
    
    public CameraEntity(Viewport viewport, OrthographicCamera camera, PlayerEntity player) {
        this.viewport = viewport;
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
        x = Math.max(x, boundaryLeft + viewport.getScreenWidth() / 2f * camera.zoom);
        x = Math.min(x, boundaryRight - viewport.getScreenWidth() / 2f * camera.zoom);
        y = Math.max(y, boundaryBottom + viewport.getScreenHeight() / 2f * camera.zoom);
        y = Math.min(y, boundaryTop - viewport.getScreenHeight() / 2f * camera.zoom);
        camera.position.set(x, y, 0);
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
