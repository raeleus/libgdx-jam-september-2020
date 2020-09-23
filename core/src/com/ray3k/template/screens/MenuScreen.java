package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.crashinvaders.vfx.effects.ChainVfxEffect;
import com.crashinvaders.vfx.effects.WaterDistortionEffect;
import com.ray3k.template.*;
import com.ray3k.template.vfx.*;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class MenuScreen extends JamScreen {
    private Stage stage;
    private final static Color BG_COLOR = new Color(Color.BLACK);
    private GlitchEffect vfxEffect;
    
    @Override
    public void show() {
        super.show();
    
        final Music bgm = bgm_menu;
        if (!bgm.isPlaying()) {
            bgm.play();
            bgm.setVolume(core.bgm);
            bgm.setLooping(true);
        }
        
        stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);
    
        sceneBuilder.build(stage, skin, Gdx.files.internal("menus/main.json"));
        TextButton textButton = stage.getRoot().findActor("play");
        textButton.addListener(sndChangeListener);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(null);
                core.transition(new GameScreen());
            }
        });
    
        textButton = stage.getRoot().findActor("options");
        textButton.addListener(sndChangeListener);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(null);
                core.transition(new OptionsScreen());
            }
        });
    
        textButton = stage.getRoot().findActor("credits");
        textButton.addListener(sndChangeListener);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(null);
                core.transition(new CreditsScreen());
            }
        });
        
        vfxEffect = new GlitchEffect();
        vfxEffect.setAmount(0);
        vfxEffect.rebind();
        vfxManager.addEffect(vfxEffect);
        
        stage.addAction(forever(sequence(delay(10f), new TemporalAction(.25f) {
            @Override
            protected void update(float percent) {
                vfxEffect.setAmount(percent * .2f);
                vfxEffect.rebind();
            }
        }, new TemporalAction(.25f) {
            @Override
            protected void update(float percent) {
                vfxEffect.setAmount((1 - percent) * .2f);
                vfxEffect.rebind();
            }
        })));
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
        vfxManager.update(delta);
    }
    
    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
        vfxManager.endInputCapture();
        vfxManager.applyEffects();
        vfxManager.renderToScreen();
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        vfxManager.resize(width, height);
    }
    
    @Override
    public void hide() {
        super.hide();
        vfxManager.removeAllEffects();
        vfxEffect.dispose();
    }
}
