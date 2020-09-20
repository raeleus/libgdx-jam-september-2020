package com.ray3k.template;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.SkeletonData;

public class Resources {
    public static Skin skin_skin;

    public static SkeletonData spine_libgdx;

    public static AnimationStateData spine_libgdxAnimationData;

    public static SkeletonData spine_monster;

    public static AnimationStateData spine_monsterAnimationData;

    public static SkeletonData spine_player;

    public static AnimationStateData spine_playerAnimationData;

    public static SkeletonData spine_ray3k;

    public static AnimationStateData spine_ray3kAnimationData;

    public static SkeletonData spine_wall;

    public static AnimationStateData spine_wallAnimationData;

    public static TextureAtlas textures_textures;

    public static Sound sfx_click;

    public static Sound sfx_logoLibgdxChop;

    public static Sound sfx_logoLibgdxKikiki;

    public static Sound sfx_logoLibgdxLoser;

    public static Sound sfx_logoLibgdxMaskSound;

    public static Sound sfx_logoLibgdxScream;

    public static Sound sfx_logoLibgdxTitle;

    public static Sound sfx_logoRay3kTune;

    public static Sound sfx_logoRay3k;

    public static Music bgm_audioSample;

    public static Music bgm_menu;

    public static void loadResources(AssetManager assetManager) {
        skin_skin = assetManager.get("skin/skin.json");
        spine_libgdx = assetManager.get("spine/libgdx.json");
        spine_libgdxAnimationData = assetManager.get("spine/libgdx.json-animation");
        LibgdxAnimation.animation = spine_libgdx.findAnimation("animation");
        LibgdxAnimation.stand = spine_libgdx.findAnimation("stand");
        spine_monster = assetManager.get("spine/monster.json");
        spine_monsterAnimationData = assetManager.get("spine/monster.json-animation");
        MonsterAnimation.animation = spine_monster.findAnimation("animation");
        spine_player = assetManager.get("spine/player.json");
        spine_playerAnimationData = assetManager.get("spine/player.json-animation");
        PlayerAnimation.animation = spine_player.findAnimation("animation");
        spine_ray3k = assetManager.get("spine/ray3k.json");
        spine_ray3kAnimationData = assetManager.get("spine/ray3k.json-animation");
        Ray3kAnimation.animation = spine_ray3k.findAnimation("animation");
        Ray3kAnimation.stand = spine_ray3k.findAnimation("stand");
        spine_wall = assetManager.get("spine/wall.json");
        spine_wallAnimationData = assetManager.get("spine/wall.json-animation");
        WallAnimation.animation = spine_wall.findAnimation("animation");
        textures_textures = assetManager.get("textures/textures.atlas");
        sfx_click = assetManager.get("sfx/click.mp3");
        sfx_logoLibgdxChop = assetManager.get("sfx/logo/libgdx-chop.mp3");
        sfx_logoLibgdxKikiki = assetManager.get("sfx/logo/libgdx-kikiki.mp3");
        sfx_logoLibgdxLoser = assetManager.get("sfx/logo/libgdx-loser.mp3");
        sfx_logoLibgdxMaskSound = assetManager.get("sfx/logo/libgdx-mask-sound.mp3");
        sfx_logoLibgdxScream = assetManager.get("sfx/logo/libgdx-scream.mp3");
        sfx_logoLibgdxTitle = assetManager.get("sfx/logo/libgdx-title.mp3");
        sfx_logoRay3kTune = assetManager.get("sfx/logo/ray3k-tune.mp3");
        sfx_logoRay3k = assetManager.get("sfx/logo/ray3k.mp3");
        bgm_audioSample = assetManager.get("bgm/audio-sample.mp3");
        bgm_menu = assetManager.get("bgm/menu.mp3");
    }

    public static class LibgdxAnimation {
        public static Animation animation;

        public static Animation stand;
    }

    public static class MonsterAnimation {
        public static Animation animation;
    }

    public static class PlayerAnimation {
        public static Animation animation;
    }

    public static class Ray3kAnimation {
        public static Animation animation;

        public static Animation stand;
    }

    public static class WallAnimation {
        public static Animation animation;
    }
}