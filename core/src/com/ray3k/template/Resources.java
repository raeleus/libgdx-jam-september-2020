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

    public static SkeletonData spine_obstacle;

    public static AnimationStateData spine_obstacleAnimationData;

    public static SkeletonData spine_player;

    public static AnimationStateData spine_playerAnimationData;

    public static SkeletonData spine_ray3k;

    public static AnimationStateData spine_ray3kAnimationData;

    public static SkeletonData spine_switch;

    public static AnimationStateData spine_switchAnimationData;

    public static SkeletonData spine_telepad;

    public static AnimationStateData spine_telepadAnimationData;

    public static SkeletonData spine_wall;

    public static AnimationStateData spine_wallAnimationData;

    public static SkeletonData spine_zaida;

    public static AnimationStateData spine_zaidaAnimationData;

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

    public static Sound sfx_logoWoosh;

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
        MonsterAnimation.down = spine_monster.findAnimation("down");
        MonsterAnimation.left = spine_monster.findAnimation("left");
        MonsterAnimation.right = spine_monster.findAnimation("right");
        MonsterAnimation.stand = spine_monster.findAnimation("stand");
        MonsterAnimation.up = spine_monster.findAnimation("up");
        spine_obstacle = assetManager.get("spine/obstacle.json");
        spine_obstacleAnimationData = assetManager.get("spine/obstacle.json-animation");
        ObstacleAnimation.animation = spine_obstacle.findAnimation("animation");
        spine_player = assetManager.get("spine/player.json");
        spine_playerAnimationData = assetManager.get("spine/player.json-animation");
        PlayerAnimation.down = spine_player.findAnimation("down");
        PlayerAnimation.left = spine_player.findAnimation("left");
        PlayerAnimation.right = spine_player.findAnimation("right");
        PlayerAnimation.up = spine_player.findAnimation("up");
        spine_ray3k = assetManager.get("spine/ray3k.json");
        spine_ray3kAnimationData = assetManager.get("spine/ray3k.json-animation");
        Ray3kAnimation.animation = spine_ray3k.findAnimation("animation");
        Ray3kAnimation.stand = spine_ray3k.findAnimation("stand");
        spine_switch = assetManager.get("spine/switch.json");
        spine_switchAnimationData = assetManager.get("spine/switch.json-animation");
        SwitchAnimation.off = spine_switch.findAnimation("off");
        SwitchAnimation.on = spine_switch.findAnimation("on");
        spine_telepad = assetManager.get("spine/telepad.json");
        spine_telepadAnimationData = assetManager.get("spine/telepad.json-animation");
        TelepadAnimation.animation = spine_telepad.findAnimation("animation");
        spine_wall = assetManager.get("spine/wall.json");
        spine_wallAnimationData = assetManager.get("spine/wall.json-animation");
        WallAnimation.animation = spine_wall.findAnimation("animation");
        spine_zaida = assetManager.get("spine/zaida.json");
        spine_zaidaAnimationData = assetManager.get("spine/zaida.json-animation");
        ZaidaAnimation.animation = spine_zaida.findAnimation("animation");
        ZaidaAnimation.stand = spine_zaida.findAnimation("stand");
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
        sfx_logoWoosh = assetManager.get("sfx/logo/woosh.mp3");
        bgm_audioSample = assetManager.get("bgm/audio-sample.mp3");
        bgm_menu = assetManager.get("bgm/menu.mp3");
    }

    public static class LibgdxAnimation {
        public static Animation animation;

        public static Animation stand;
    }

    public static class MonsterAnimation {
        public static Animation down;

        public static Animation left;

        public static Animation right;

        public static Animation stand;

        public static Animation up;
    }

    public static class ObstacleAnimation {
        public static Animation animation;
    }

    public static class PlayerAnimation {
        public static Animation down;

        public static Animation left;

        public static Animation right;

        public static Animation up;
    }

    public static class Ray3kAnimation {
        public static Animation animation;

        public static Animation stand;
    }

    public static class SwitchAnimation {
        public static Animation off;

        public static Animation on;
    }

    public static class TelepadAnimation {
        public static Animation animation;
    }

    public static class WallAnimation {
        public static Animation animation;
    }

    public static class ZaidaAnimation {
        public static Animation animation;

        public static Animation stand;
    }
}
