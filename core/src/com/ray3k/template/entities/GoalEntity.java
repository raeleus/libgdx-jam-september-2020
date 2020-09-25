package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.Collisions;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.collisions.NullCollisionFilter.*;
import static com.ray3k.template.screens.GameScreen.*;

public class GoalEntity extends Entity {
    public static final Color DEBUG_COLOR = new Color();
    
    @Override
    public void create() {
        setCollisionBox(-8, -8, 16, 16, nullCollisionFilter);
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
            DEBUG_COLOR.set(Color.PURPLE);
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
