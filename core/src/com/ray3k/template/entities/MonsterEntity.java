package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.MonsterAnimation.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.collisions.MonsterCollisionFilter.*;
import static com.ray3k.template.screens.GameScreen.*;

public class MonsterEntity extends Entity {
    public static final Color DEBUG_COLOR = new Color();
    
    @Override
    public void create() {
        setSkeletonData(spine_monster, spine_monsterAnimationData);
        animationState.setAnimation(0, down, true);
    
        setCollisionBox(-8, -8, 16, 16, monsterCollisionFilter);
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
            DEBUG_COLOR.set(Color.ORANGE);
            shapeDrawer.setColor(DEBUG_COLOR);
            shapeDrawer.setDefaultLineWidth(1f);
            shapeDrawer.rectangle(rect.x, rect.y, rect.w, rect.h);
        }
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
