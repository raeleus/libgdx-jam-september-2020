package com.ray3k.template.collisions;

import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;

public class NullCollisionFilter implements CollisionFilter {
    public static final NullCollisionFilter nullCollisionFilter = new NullCollisionFilter();
    
    @Override
    public Response filter(Item item, Item other) {
        return null;
    }
}
