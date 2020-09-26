package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.dongbat.jbump.Collisions;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.collisions.AttackCollisionFilter.*;
import static com.ray3k.template.screens.GameScreen.*;

public class AttackEntity extends Entity {
    public static float SIZE = 32f;
    public static float LIFE = .2f;
    public float timer = LIFE;
    private Array<Entity> hitEntities = new Array<>();
    public float attackDirection;
    
    public AttackEntity(int attackDirection) {
        this.attackDirection = attackDirection;
    }
    
    @Override
    public void create() {
        setCollisionBox(-SIZE / 2, -SIZE / 2, SIZE, SIZE, attackCollisionFilter);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        timer -= delta;
        if (timer < 0) destroy = true;
    }
    
    @Override
    public void draw(float delta) {
        var rect = world.getRect(item);
        if (rect != null) {
            shapeDrawer.setColor(Color.RED);
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
            if (!hitEntities.contains((Entity) collision.other.userData, true)) {
                if (collision.other.userData instanceof MonsterEntity) {
                    var monster = (MonsterEntity) collision.other.userData;
                    
                    monster.setMotion(MonsterEntity.HURT_SPEED, attackDirection);
                    monster.hurtTimer = MonsterEntity.HURT_TIME;
                    
                    hitEntities.add(monster);
                } else if (collision.other.userData instanceof SwitchEntity) {
                    var switchEntity = (SwitchEntity) collision.other.userData;
                    switchEntity.flipState();
                    hitEntities.add(switchEntity);
                }
            }
        }
    }
}
