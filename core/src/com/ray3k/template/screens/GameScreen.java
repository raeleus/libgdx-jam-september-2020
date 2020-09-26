package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.crashinvaders.vfx.effects.ChainVfxEffect;
import com.ray3k.template.*;
import com.ray3k.template.OgmoReader.*;
import com.ray3k.template.Resources.*;
import com.ray3k.template.entities.*;
import com.ray3k.template.screens.DialogPause.*;
import space.earlygrey.shapedrawer.ShapeDrawer;

import static com.ray3k.template.Core.*;

public class GameScreen extends JamScreen {
    public static GameScreen gameScreen;
    public static final Color BG_COLOR = new Color(Color.WHITE);
    public Stage stage;
    public static ShapeDrawer shapeDrawer;
    public boolean paused;
    private ChainVfxEffect vfxEffect;
    private String levelName;
    private Array<Entity> addEntities;
    private PlayerEntity player;
    
    public GameScreen() {
        this(null, "test2");
    }
    
    public GameScreen(Array<Entity> addEntities, String levelName) {
        this.addEntities = addEntities == null ? new Array<>() : new Array<>(addEntities);
        this.levelName = levelName;
    }
    
    @Override
    public void show() {
        gameScreen = this;
    
        paused = false;
    
        stage = new Stage(new ScreenViewport(), batch);
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (!paused && keycode == Keys.ESCAPE) {
                    paused = true;
                
                    DialogPause dialogPause = new DialogPause(GameScreen.this);
                    dialogPause.show(stage);
                    dialogPause.addListener(new PauseListener() {
                        @Override
                        public void resume() {
                            paused = false;
                        }
                    
                        @Override
                        public void quit() {
                            core.transition(new MenuScreen());
                        }
                    });
                }
                return super.keyDown(event, keycode);
            }
        });
    
        shapeDrawer = new ShapeDrawer(batch, skin.getRegion("white"));
        shapeDrawer.setPixelSize(.5f);
    
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    
        camera = new OrthographicCamera();
        camera.zoom = .25f;
        viewport = new FitViewport(1024, 576, camera);
    
        entityController.clear();
        
        for (var entity : addEntities) {
            if (entity instanceof PlayerEntity) player = (PlayerEntity) entity;
            entityController.add(entity);
        }
        
        var ogmoReader = new OgmoReader();
        ogmoReader.addListener(new OgmoAdapter() {
            int levelWidth;
            int levelHeight;
        
            @Override
            public void level(String ogmoVersion, int width, int height, int offsetX, int offsetY,
                              ObjectMap<String, OgmoValue> valuesMap) {
                levelWidth = width;
                levelHeight = height;
            }
        
            @Override
            public void grid(int col, int row, int x, int y, int width, int height, int id) {
                switch (id) {
                    case 1:
                        var wallEntity = new WallEntity();
                        wallEntity.setPosition(x, y);
                        entityController.add(wallEntity);
                        break;
                }
            }
            
            @Override
            public void entity(String name, int id, int x, int y, int width, int height, boolean flippedX,
                               boolean flippedY, int originX, int originY, int rotation, Array<EntityNode> nodes,
                               ObjectMap<String, OgmoValue> valuesMap) {
                switch (name) {
                    case "player":
                        if (player == null) {
                            player = new PlayerEntity();
                            player.setPosition(x + 8, y - 8);
                            entityController.add(player);
                        }
                        break;
                    case "monster":
                        if (addEntities.size == 0) {
                            var monsterEntity = new MonsterEntity();
                            monsterEntity.setPosition(x + 8, y - 8);
                            entityController.add(monsterEntity);
                            switch (valuesMap.get("movement").asString()) {
                                case "east":
                                    monsterEntity.setMotion(MonsterEntity.MOVE_SPEED, 0);
                                    monsterEntity.animationState.setAnimation(0, MonsterAnimation.right, true);
                                    break;
                                case "west":
                                    monsterEntity.setMotion(MonsterEntity.MOVE_SPEED, 180);
                                    monsterEntity.animationState.setAnimation(0, MonsterAnimation.left, true);
                                    break;
                                case "north":
                                    monsterEntity.setMotion(MonsterEntity.MOVE_SPEED, 90);
                                    monsterEntity.animationState.setAnimation(0, MonsterAnimation.up, true);
                                    break;
                                case "south":
                                    monsterEntity.setMotion(MonsterEntity.MOVE_SPEED, 270);
                                    monsterEntity.animationState.setAnimation(0, MonsterAnimation.down, true);
                                    break;
                            }
                        }
                        break;
                    case "obstacle":
                        var obstacleEntity = new ObstacleEntity();
                        obstacleEntity.setPosition(x, y - 16);
                        entityController.add(obstacleEntity);
                        break;
                    case "telepad":
                        var telepadEntity = new TelepadEntity(valuesMap.get("load-level").asString());
                        telepadEntity.setPosition(x + 8, y - 8);
                        entityController.add(telepadEntity);
                        break;
                    case "switch":
                        var switchEntity = new SwitchEntity();
                        switchEntity.setPosition(x + 8, y -8);
                        entityController.add(switchEntity);
                        break;
                    case "goal":
                        var goalEntity = new GoalEntity();
                        goalEntity.setPosition(x + 8, y - 8);
                        entityController.add(goalEntity);
                        break;
                }
            }
        
            @Override
            public void decal(int centerX, int centerY, float scaleX, float scaleY, int rotation, String texture,
                              String folder) {
                var path = folder + "/" + texture;
                path = path.substring(0, path.length() - 4);
                if (textureAtlas.findRegion(path) != null) entityController.add(new DecalEntity(path, centerX, centerY));
            }
    
            @Override
            public void levelComplete() {
                var cameraEntity = new CameraEntity(viewport, camera, player);
                cameraEntity.boundaryLeft = 0;
                cameraEntity.boundaryRight = levelWidth;
                cameraEntity.boundaryBottom = 0;
                cameraEntity.boundaryTop = levelHeight;
                entityController.add(cameraEntity);
            }
        });
        ogmoReader.readFile(Gdx.files.internal("levels/" + levelName + ".json"));
    }
    
    @Override
    public void act(float delta) {
        if (!paused) {
            entityController.act(delta);
            vfxManager.update(delta);
        }
        stage.act(delta);
    }
    
    @Override
    public void draw(float delta) {
        batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
        
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        entityController.draw(paused ? 0 : delta);
        batch.end();
        
        vfxManager.endInputCapture();
        vfxManager.applyEffects();
        vfxManager.renderToScreen();
    
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        if (width + height != 0) {
            vfxManager.resize(width, height);
            viewport.update(width, height);
            
            stage.getViewport().update(width, height, true);
        }
    }
    
    @Override
    public void dispose() {
    }
    
    @Override
    public void hide() {
        super.hide();
        vfxManager.removeAllEffects();
        entityController.dispose();
    }
}
