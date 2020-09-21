package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class DecalEntity extends Entity {
    private AtlasSprite atlasSprite;
    
    public DecalEntity(String path, float x, float y) {
        atlasSprite = new AtlasSprite(textures_textures.findRegion(path));
        atlasSprite.setOriginCenter();
        depth = BACKGROUND_DEPTH;
        setPosition(x, y);
    }
    
    @Override
    public void create() {
    
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
    
    }
    
    @Override
    public void draw(float delta) {
        atlasSprite.setOriginBasedPosition(x, y);
        atlasSprite.draw(batch);
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
