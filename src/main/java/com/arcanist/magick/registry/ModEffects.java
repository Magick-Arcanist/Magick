package com.arcanist.magick.registry;

import com.arcanist.magick.statuseffect.effects.*;
import com.mojang.datafixers.FunctionType;
import net.minecraft.client.texture.SpriteAtlasHolder;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEffects extends StatusEffects {

        public static final StatusEffect FEAR = new FearStatusEffect();
        public static final StatusEffect GRAVITY = new GravityStatusEffect();
        public static final StatusEffect PHOTOSYNTHESIS = new PhotosynthesisStatusEffect();
        public static final StatusEffect SPIDERCLIMB = new SpiderClimbStatusEffect();
        public static final StatusEffect IMMORTAL = new ImmortalStatusEffect();
        public static final StatusEffect REMOVE_EFFECTS = new ClearEffectStatusEffect();
        public static final StatusEffect ORE_SENSE = new OreSenseStatusEffect();
        public static final StatusEffect RECALL = new RecallStatusEffect();
        public static final StatusEffect RED_LIGHT = new RedLightStatusEffect();
        public static final StatusEffect WHITE_LIGHT = new WhiteLightStatusEffect();
        public static final StatusEffect LOVE = new LoveStatusEffect();
        public static final StatusEffect MANA = new ManaStatusEffect();

        public static void registerEffects() {
                Registry.register(Registry.STATUS_EFFECT, new Identifier("magick", "fear"), FEAR);
                Registry.register(Registry.STATUS_EFFECT, new Identifier("magick", "gravity"), GRAVITY);
                Registry.register(Registry.STATUS_EFFECT, new Identifier("magick", "photosynthesis"), PHOTOSYNTHESIS);
                Registry.register(Registry.STATUS_EFFECT, new Identifier("magick", "spiderclimb"), SPIDERCLIMB);
                Registry.register(Registry.STATUS_EFFECT, new Identifier("magick", "immortal"), IMMORTAL);
                Registry.register(Registry.STATUS_EFFECT, new Identifier("magick", "remove_effects"), REMOVE_EFFECTS);
                Registry.register(Registry.STATUS_EFFECT, new Identifier("magick", "ore_sense"), ORE_SENSE);
                Registry.register(Registry.STATUS_EFFECT, new Identifier("magick", "recall"), RECALL);
                Registry.register(Registry.STATUS_EFFECT, new Identifier("magick", "red_light"), RED_LIGHT);
                Registry.register(Registry.STATUS_EFFECT, new Identifier("magick", "white_light"), WHITE_LIGHT);
                Registry.register(Registry.STATUS_EFFECT, new Identifier("magick", "love"), LOVE);
                Registry.register(Registry.STATUS_EFFECT, new Identifier("magick", "mana"), MANA);
        }


}