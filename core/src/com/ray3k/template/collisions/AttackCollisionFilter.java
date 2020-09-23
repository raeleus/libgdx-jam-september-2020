package com.ray3k.template.collisions;

import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.ray3k.template.entities.*;

public class AttackCollisionFilter implements CollisionFilter {
    public static final AttackCollisionFilter attackCollisionFilter = new AttackCollisionFilter();
    
    @Override
    public Response filter(Item item, Item other) {
        if (other.userData instanceof MonsterEntity || other.userData instanceof SwitchEntity) {
            return Response.cross;
        } else return null;
    }
}
