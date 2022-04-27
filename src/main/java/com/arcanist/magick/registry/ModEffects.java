package com.arcanist.magick.registry;

import com.arcanist.magick.Magick;
import com.arcanist.magick.statuseffect.effects.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModEffects{

        private static final Map<StatusEffect, Identifier> STATUS_EFFECTS = new LinkedHashMap<>();

        public static final StatusEffect FEAR = create("fear", new FearStatusEffect());
        public static final StatusEffect GRAVITY = create("gravity", new GravityStatusEffect());
        public static final StatusEffect PHOTOSYNTHESIS = create("photosynthesis", new PhotosynthesisStatusEffect());
        public static final StatusEffect SPIDERCLIMB = create("spiderclimb", new SpiderClimbStatusEffect());
        public static final StatusEffect IMMORTAL = create("immortal", new ImmortalStatusEffect());
        public static final StatusEffect REMOVE_EFFECTS = create("remove_effects", new ClearEffectStatusEffect());
        public static final StatusEffect ORE_SENSE = create("ore_sense", new OreSenseStatusEffect());
        public static final StatusEffect RECALL = create("recall", new RecallStatusEffect());
        public static final StatusEffect RED_LIGHT = create("red_light", new RedLightStatusEffect());
        public static final StatusEffect WHITE_LIGHT = create("white_light", new WhiteLightStatusEffect());
        public static final StatusEffect LOVE = create("love", new LoveStatusEffect());
        public static final StatusEffect MANA = create("mana", new ManaStatusEffect());

    private static <T extends StatusEffect> T create(String name, T effect) {
        STATUS_EFFECTS.put(effect, new Identifier(Magick.MOD_ID, name));
        return effect;
    }

    public static void registerEffects() {
        STATUS_EFFECTS.keySet().forEach(effect -> Registry.register(Registry.STATUS_EFFECT, STATUS_EFFECTS.get(effect), effect));
    }
}