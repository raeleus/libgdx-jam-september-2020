package com.ray3k.template.collisions;

import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.ray3k.template.entities.*;

public class MonsterCollisionFilter implements CollisionFilter {
    public static final MonsterCollisionFilter monsterCollisionFilter = new MonsterCollisionFilter();
    
    @Override
    public Response filter(Item item, Item other) {
        if (other.userData instanceof PlayerEntity) return Response.cross;
        else if (other.userData instanceof WallEntity) return Response.slide;
        else return null;
    }
}