package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.Collisions;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.collisions.NullCollisionFilter.*;
import static com.ray3k.template.screens.GameScreen.*;

public class WallEntity extends Entity {
    public static final Color DEBUG_COLOR = new Color();
    float width;
    float height;
    
    public WallEntity(float width, float height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void create() {
        setCollisionBox(0, 0, width, height, nullCollisionFilter);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
    
    }
    
    @Override
    public void draw(float delta) {
        var rect = world.getRect(item);
        if (rect != null) {
            DEBUG_COLOR.set(Color.RED);
            DEBUG_COLOR.a = .25f;
            shapeDrawer.setColor(DEBUG_COLOR);
            shapeDrawer.filledRectangle(rect.x, rect.y, rect.w, rect.h);
        }
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
