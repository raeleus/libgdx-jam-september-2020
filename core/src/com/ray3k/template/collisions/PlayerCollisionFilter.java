package com.ray3k.template.collisions;

import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.ray3k.template.entities.*;

public class PlayerCollisionFilter implements CollisionFilter {
    public static final PlayerCollisionFilter playerCollisionFilter = new PlayerCollisionFilter();
    
    @Override
    public Response filter(Item item, Item other) {
        if (other.userData instanceof MonsterEntity) {
            return Response.cross;
        } else {
            return Response.slide;
        }
    }
}