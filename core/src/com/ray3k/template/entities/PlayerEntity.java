package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.dongbat.jbump.Collisions;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;
import com.ray3k.template.transitions.*;

import static com.ray3k.template.Core.Binding.*;
import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.collisions.PlayerCollisionFilter.*;
import static com.ray3k.template.screens.GameScreen.*;

public class PlayerEntity extends Entity {
    public static final float MOVE_SPEED = 50f;
    public static final Color DEBUG_COLOR = new Color();
    public static final float ATTACK_DELAY = .5f;
    public float attackTimer = .5f;
    
    @Override
    public void create() {
        setSkeletonData(spine_player, spine_playerAnimationData);
        
        setCollisionBox(-8, -8, 16, 16, playerCollisionFilter);
        animationState.setAnimation(0, PlayerAnimation.down, true);
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
        
        //animation
        if (gameScreen.isBindingPressed(RIGHT)) {
            if (animationState.getCurrent(0).getAnimation() != PlayerAnimation.right) animationState.setAnimation(0, PlayerAnimation.right, true);
        } else if (gameScreen.isBindingPressed(LEFT)) {
            if (animationState.getCurrent(0).getAnimation() != PlayerAnimation.left) animationState.setAnimation(0, PlayerAnimation.left, true);
        } else if (gameScreen.isBindingPressed(UP)) {
            if (animationState.getCurrent(0).getAnimation() != PlayerAnimation.up) animationState.setAnimation(0, PlayerAnimation.up, true);
        }  else if (gameScreen.isBindingPressed(DOWN)) {
            if (animationState.getCurrent(0).getAnimation() != PlayerAnimation.down) animationState.setAnimation(0, PlayerAnimation.down, true);
        }
        
        //attack
        attackTimer = Utils.approach(attackTimer, 0, delta);
        if (attackTimer == 0 && gameScreen.isBindingJustPressed(ATTACK)) {
            attackTimer = ATTACK_DELAY;
            int attackDirection = 0;
            if (animationState.getCurrent(0).getAnimation() == PlayerAnimation.up) attackDirection = 90;
            else if (animationState.getCurrent(0).getAnimation() == PlayerAnimation.left) attackDirection = 180;
            else if (animationState.getCurrent(0).getAnimation() == PlayerAnimation.down) attackDirection = 270;
            var attack = new AttackEntity(attackDirection);
            entityController.add(attack);
            if (animationState.getCurrent(0).getAnimation() == PlayerAnimation.right)attack.setPosition(x + AttackEntity.SIZE / 2 + 8, y);
            else if (animationState.getCurrent(0).getAnimation() == PlayerAnimation.left)attack.setPosition(x - AttackEntity.SIZE / 2 - 8, y);
            else if (animationState.getCurrent(0).getAnimation() == PlayerAnimation.up)attack.setPosition(x, y + AttackEntity.SIZE / 2 + 8);
            else if (animationState.getCurrent(0).getAnimation() == PlayerAnimation.down)attack.setPosition(x, y - AttackEntity.SIZE / 2 - 8);
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
    public void collision(Collisions collisions) {
        for (int i = 0; i < collisions.size(); i++) {
            var collision = collisions.get(i);
            if (collision.other.userData instanceof MonsterEntity) {
                destroy = true;
                gameScreen.stage.addAction(Actions.delay(.25f, Actions.run(() -> {
                    core.transition(new GameOverScreen(), new TransitionColorFade(Color.BLACK, Interpolation.circle), 2f);
                })));
            } else if (collision.other.userData instanceof TelepadEntity) {
                var telepad = (TelepadEntity) collision.other.userData;
                if (MathUtils.isZero(telepad.readyTimer)) {
                    gameScreen.stage.addAction(Actions.run(() -> {
                        var addEntities = new Array<Entity>();
                        for (var entity : entityController.entities) {
                            if (entity instanceof PlayerEntity || entity instanceof MonsterEntity) addEntities.add(entity);
                        }
                        core.transition(new GameScreen(addEntities, telepad.loadLevel), new TransitionSquish(Color.PINK, Interpolation.fastSlow), 1f);
                    }));
                }
                telepad.readyTimer = TelepadEntity.READY_DELAY;
            } else if (collision.other.userData instanceof  GoalEntity) {
                gameScreen.stage.addAction(Actions.run(() -> {
                    core.transition(new CompleteScreen(), new TransitionColorFade(Color.WHITE, Interpolation.circle), 2f);
                }));
            }
        }
    }
}
