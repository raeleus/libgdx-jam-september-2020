package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.dongbat.jbump.Collisions;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.MonsterAnimation.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.collisions.MonsterCollisionFilter.*;
import static com.ray3k.template.screens.GameScreen.*;

public class MonsterEntity extends Entity {
    public static final Color DEBUG_COLOR = new Color();
    public static final  float MOVE_SPEED = 50f;
    public static final float HURT_SPEED = 100f;
    public static final float HURT_TIME = .5f;
    public float hurtTimer;
    
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
        if (hurtTimer > 0) {
            hurtTimer -= delta;
            if (hurtTimer <= 0) {
                hurtTimer = 0;
                switch (MathUtils.random(10)) {
                    case 0:
                        setMotion(MOVE_SPEED, 0);
                        break;
                    case 1:
                        setMotion(MOVE_SPEED, 90);
                        break;
                    case 2:
                        setMotion(MOVE_SPEED, 180);
                        break;
                    case 3:
                        setMotion(MOVE_SPEED, 270);
                        break;
                    case 4:
                        setMotion(0, 0);
                        break;
                    default:
                        setSpeed(MOVE_SPEED);
                        break;
                }
            }
        }
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
        for (int i = 0; i < collisions.size(); i++) {
            var collision = collisions.get(i);
            if (collision.other.userData instanceof WallEntity) {
                if (collision.normal.x == -1) {
                    animationState.setAnimation(0, left, true);
                    deltaX = collision.normal.x * Math.abs(deltaX);
                } else if (collision.normal.x == 1) {
                    animationState.setAnimation(0, right, true);
                    deltaX = collision.normal.x * Math.abs(deltaX);
                }
    
                if (collision.normal.y == -1) {
                    animationState.setAnimation(0, down, true);
                    deltaY = collision.normal.y * Math.abs(deltaY);
                } else if (collision.normal.y == 1) {
                    animationState.setAnimation(0, up, true);
                    deltaY = collision.normal.y * Math.abs(deltaY);
                }
            }
        }
    }
}
