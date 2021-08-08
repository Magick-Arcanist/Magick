package com.arcanist.magick.registry;

import com.arcanist.magick.statuseffect.effects.*;
import com.mojang.serialization.Lifecycle;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class ModEffects extends StatusEffects{

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



//this code works in singleplayer but not on servers
 /*
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
*/

    // this works on servers but the textures don't load because it looks for them in minecraft/mob_effect instead of magick/mob_effect
    public static void registerEffects() {
        Registry.register(Registry.STATUS_EFFECT,34,  "fear", FEAR);
        Registry.register(Registry.STATUS_EFFECT, 35, "gravity", GRAVITY);
        Registry.register(Registry.STATUS_EFFECT, 36, "photosynthesis", PHOTOSYNTHESIS);
        Registry.register(Registry.STATUS_EFFECT, 37, "spiderclimb", SPIDERCLIMB);
        Registry.register(Registry.STATUS_EFFECT, 38, "immortal", IMMORTAL);
        Registry.register(Registry.STATUS_EFFECT,39, "remove_effects", REMOVE_EFFECTS);
        Registry.register(Registry.STATUS_EFFECT, 40, "ore_sense", ORE_SENSE);
        Registry.register(Registry.STATUS_EFFECT, 41, "recall", RECALL);
        Registry.register(Registry.STATUS_EFFECT,42, "red_light", RED_LIGHT);
        Registry.register(Registry.STATUS_EFFECT, 43, "white_light", WHITE_LIGHT);
        Registry.register(Registry.STATUS_EFFECT, 44, "love", LOVE);
        Registry.register(Registry.STATUS_EFFECT, 45, "mana", MANA);
    }


}