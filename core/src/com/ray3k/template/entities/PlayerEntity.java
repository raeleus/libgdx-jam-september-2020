package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;

import static com.ray3k.template.Core.Binding.*;
import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.collisions.PlayerCollisionFilter.*;
import static com.ray3k.template.screens.GameScreen.*;

public class PlayerEntity extends Entity {
    public static final float MOVE_SPEED = 50f;
    public static final Color DEBUG_COLOR = new Color();
    
    @Override
    public void create() {
        setSkeletonData(spine_player, spine_playerAnimationData);
        
        setCollisionBox(-8, -8, 16, 16, playerCollisionFilter);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        //movement
        if (gameScreen.isAnyBindingPressed(LEFT, RIGHT, UP, DOWN)) {
            setSpeed(MOVE_SPEED);
            float direction = 0;
            if (gameScreen.isAnyBindingPressed(LEFT, RIGHT)) {
                if (gameScreen.isBindingPressed(LEFT)) direction = 180;
                
                if (gameScreen.isBindingPressed(UP)) direction = Utils.approach360(direction, 90, 45);
                else if (gameScreen.isBindingPressed(DOWN)) direction = Utils.approach360(direction, 270, 45);
            } else if (gameScreen.isBindingPressed(UP)) direction = 90;
            else if (gameScreen.isBindingPressed(DOWN)) direction = 270;
            
            setDirection(direction);
        } else {
            setSpeed(0);
        }
    }
    
    @Override
    public void draw(float delta) {
        var rect = world.getRect(item);
        if (rect != null) {
            DEBUG_COLOR.set(Color.GREEN);
            DEBUG_COLOR.a = .25f;
            shapeDrawer.setColor(DEBUG_COLOR);
            shapeDrawer.filledRectangle(rect.x, rect.y, rect.w, rect.h);
        }
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void collisions(Result collisionResult) {
        for (int i = 0; i < collisionResult.projectedCollisions.size(); i++) {
            var collision = collisionResult.projectedCollisions.get(i);
            if (collision.other.userData instanceof MonsterEntity) {
                destroy = true;
            }
        }
    }
}
