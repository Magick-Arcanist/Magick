package com.arcanist.magick.registry;

import com.arcanist.magick.Magick;
import com.arcanist.magick.statuseffect.ModStatusEffect;
import com.arcanist.magick.statuseffect.effects.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModEffects extends StatusEffects {

    private static final Map<StatusEffect, Identifier> STATUS_EFFECTS = new LinkedHashMap<>();

    public static final ModStatusEffect FEAR = create("fear", new FearStatusEffect());
    public static final ModStatusEffect GRAVITY = create("gravity", new GravityStatusEffect());
    public static final ModStatusEffect PHOTOSYNTHESIS = create("photosynthesis", new PhotosynthesisStatusEffect());
    public static final ModStatusEffect SPIDERCLIMB = create("spiderclimb", new SpiderClimbStatusEffect());
    public static final ModStatusEffect IMMORTAL = create("immortal", new ImmortalStatusEffect());
    public static final ModStatusEffect REMOVE_EFFECTS = create("remove_effects", new ClearEffectStatusEffect());
    public static final ModStatusEffect ORE_SENSE = create("ore_sense", new OreSenseStatusEffect());
    public static final ModStatusEffect RETURNING = create("returning", new ReturningStatusEffect());
    public static final ModStatusEffect RED_LIGHT = create("red_light", new RedLightStatusEffect());
    public static final ModStatusEffect WHITE_LIGHT = create("white_light", new WhiteLightStatusEffect());
    public static final ModStatusEffect LOVE = create("love", new LoveStatusEffect());
    public static final ModStatusEffect MANA = create("mana", new ManaStatusEffect());

    private static <T extends ModStatusEffect> T create(String name, T effect) {
        STATUS_EFFECTS.put(effect, new Identifier( Magick.MOD_ID, name));
        return effect;
    }

    public static void registerEffects() {
        STATUS_EFFECTS.keySet().forEach(effect -> Registry.register(Registries.STATUS_EFFECT, STATUS_EFFECTS.get(effect), effect));
    }
}