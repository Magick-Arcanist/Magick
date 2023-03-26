package com.arcanist.magick.registry;

import com.arcanist.magick.Magick;
import com.arcanist.magick.mixin.BrewingRecipeRegistryAccessor;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public final class ModPotions {

    private ModPotions() {}

    private static final Collection<RecipeToInit> RECIPES = new ArrayList<>();

    public static final Potion LEVITATION = register("levitation", new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 160)), ModItems.END_DUST, Potions.AWKWARD);
    public static final Potion LONG_LEVITATION = register("long_levitation", new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 400)));
    public static final Potion STRONG_LEVITATION = register("strong_levitation", new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 100, 2)));

    public static final Potion BLINDNESS = register("blindness", new Potion(new StatusEffectInstance(StatusEffects.BLINDNESS, 400)), Items.INK_SAC, Potions.AWKWARD);
    public static final Potion LONG_BLINDNESS = register("long_blindness", new Potion(new StatusEffectInstance(StatusEffects.BLINDNESS, 800)));

    public static final Potion HASTE = register("haste", new Potion(new StatusEffectInstance(StatusEffects.HASTE, 1800)), ModItems.DIAMOND_DUST, Potions.AWKWARD);
    public static final Potion LONG_HASTE = register("long_haste", new Potion(new StatusEffectInstance(StatusEffects.HASTE, 4800)));
    public static final Potion STRONG_HASTE = register("strong_haste", new Potion(new StatusEffectInstance(StatusEffects.HASTE, 900, 2)));

    public static final Potion MINING_FATIGUE = register("mining_fatigue", new Potion(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 1800)), ModItems.OBSIDIAN_DUST, Potions.AWKWARD);
    public static final Potion LONG_MINING_FATIGUE = register("long_mining_fatigue", new Potion(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 4800)));
    public static final Potion STRONG_MINING_FATIGUE = register("strong_mining_fatigue", new Potion(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 900, 2)));

    public static final Potion UNLUCK = register("unluck", new Potion(new StatusEffectInstance(StatusEffects.UNLUCK, 6000)), ModItems.NETHER_DUST, Potions.AWKWARD);
    public static final Potion LONG_UNLUCK = register("long_unluck", new Potion(new StatusEffectInstance(StatusEffects.UNLUCK, 18000)));
    public static final Potion LONG_LUCK = register("long_luck", new Potion(new StatusEffectInstance(StatusEffects.LUCK, 18000)));

    public static final Potion WITHER = register("wither", new Potion(new StatusEffectInstance(StatusEffects.WITHER, 900)), Items.WITHER_ROSE, Potions.AWKWARD);
    public static final Potion LONG_WITHER = register("long_wither", new Potion(new StatusEffectInstance(StatusEffects.WITHER, 1800)));
    public static final Potion STRONG_WITHER = register("strong_wither", new Potion(new StatusEffectInstance(StatusEffects.WITHER, 400, 1)));

    public static final Potion HEALTH_BOOST = register("health_boost", new Potion(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 3600,1)), Items.GOLDEN_APPLE, Potions.AWKWARD);
    public static final Potion LONG_HEALTH_BOOST = register("long_health_boost", new Potion(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 9600,1)));
    public static final Potion STRONG_HEALTH_BOOST = register("strong_health_boost", new Potion(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 1800, 3)));

    public static final Potion RESISTANCE = register("resistance", new Potion(new StatusEffectInstance(StatusEffects.RESISTANCE, 3600)), Items.NAUTILUS_SHELL, Potions.AWKWARD);
    public static final Potion LONG_RESISTANCE = register("long_resistance", new Potion(new StatusEffectInstance(StatusEffects.RESISTANCE, 9600)));
    public static final Potion STRONG_RESISTANCE = register("strong_resistance", new Potion(new StatusEffectInstance(StatusEffects.RESISTANCE, 1800, 1)));

    public static final Potion NAUSEA = register("nausea", new Potion(new StatusEffectInstance(StatusEffects.NAUSEA, 900) ,new StatusEffectInstance(StatusEffects.HUNGER, 900)), Items.POISONOUS_POTATO, Potions.AWKWARD);
    public static final Potion LONG_NAUSEA = register("long_nausea", new Potion(new StatusEffectInstance(StatusEffects.NAUSEA, 1800), new StatusEffectInstance(StatusEffects.HUNGER, 1800)));

    public static final Potion GLOWING= register("glowing", new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 1800)), Items.GLOW_INK_SAC, Potions.AWKWARD);
    public static final Potion LONG_GLOWING= register("long_glowing", new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 4800)));

    public static final Potion LIGHT= register("white_light", new Potion(new StatusEffectInstance(ModEffects.WHITE_LIGHT, 1800)), Items.GLOWSTONE_DUST, Potions.AWKWARD);
    public static final Potion LONG_LIGHT= register("long_white_light", new Potion(new StatusEffectInstance(ModEffects.WHITE_LIGHT, 4800)));
    public static final Potion STRONG_LIGHT= register("strong_white_light", new Potion(new StatusEffectInstance(ModEffects.WHITE_LIGHT, 900, 1)));

    public static final Potion RED_LIGHT = register("red_light", new Potion(new StatusEffectInstance(ModEffects.RED_LIGHT, 1800)), Items.REDSTONE, Potions.AWKWARD);
    public static final Potion LONG_RED_LIGHT = register("long_red_light", new Potion(new StatusEffectInstance(ModEffects.RED_LIGHT, 4800)));
    public static final Potion STRONG_RED_LIGHT = register("strong_red_light", new Potion(new StatusEffectInstance(ModEffects.RED_LIGHT, 900, 1)));

    public static final Potion FEAR = register("fear", new Potion(new StatusEffectInstance(ModEffects.FEAR, 400)), Items.SWEET_BERRIES, Potions.AWKWARD);
    public static final Potion LONG_FEAR = register("long_fear", new Potion(new StatusEffectInstance(ModEffects.FEAR, 800)));

    public static final Potion GRAVITY = register("gravity", new Potion(new StatusEffectInstance(ModEffects.GRAVITY, 160)), ModItems.STONE_DUST, Potions.AWKWARD);
    public static final Potion LONG_GRAVITY = register("long_gravity", new Potion(new StatusEffectInstance(ModEffects.GRAVITY, 400)));

    public static final Potion PHOTOSYNTHESIS = register("photosynthesis", new Potion(new StatusEffectInstance(ModEffects.PHOTOSYNTHESIS, 3600 )), Items.SUNFLOWER, Potions.AWKWARD);
    public static final Potion LONG_PHOTOSYNTHESIS = register("long_photosynthesis", new Potion(new StatusEffectInstance(ModEffects.PHOTOSYNTHESIS, 9600 )));
    public static final Potion STRONG_PHOTOSYNTHESIS = register("strong_photosynthesis", new Potion(new StatusEffectInstance(ModEffects.PHOTOSYNTHESIS, 1800, 1 )));

    public static final Potion SPIDERCLIMB = register("spiderclimb", new Potion(new StatusEffectInstance(ModEffects.SPIDERCLIMB, 3600 )), Items.COBWEB, Potions.AWKWARD);
    public static final Potion LONG_SPIDERCLIMB = register("long_spiderclimb", new Potion(new StatusEffectInstance(ModEffects.SPIDERCLIMB, 9600 )));

    public static final Potion IMMORTAL = register("immortal", new Potion(new StatusEffectInstance(ModEffects.IMMORTAL, 160 )), Items.ENCHANTED_GOLDEN_APPLE, Potions.AWKWARD);

    public static final Potion REMOVE_EFFECTS = register("remove_effects", new Potion(new StatusEffectInstance(ModEffects.REMOVE_EFFECTS, 100 )), Items.PAPER, Potions.AWKWARD);

    public static final Potion ORE_SENSE = register("ore_sense", new Potion(new StatusEffectInstance(ModEffects.ORE_SENSE, 3600 )), ModItems.BLACKSTONE_DUST, Potions.AWKWARD);
    public static final Potion LONG_ORE_SENSE= register("long_ore_sense", new Potion(new StatusEffectInstance(ModEffects.ORE_SENSE, 9600 )));
    public static final Potion STRONG_ORE_SENSE = register("strong_ore_sense", new Potion(new StatusEffectInstance(ModEffects.ORE_SENSE, 1800, 1 )));

    public static final Potion RETURNING = register("returning", new Potion(new StatusEffectInstance(ModEffects.RETURNING, 400)), Items.CHORUS_FRUIT, Potions.AWKWARD);
    public static final Potion LONG_RETURNING = register("long_returning", new Potion(new StatusEffectInstance(ModEffects.RETURNING, 9600)));

    public static final Potion LOVE = register("love", new Potion(new StatusEffectInstance(ModEffects.LOVE, 400 )), Items.WHEAT, Potions.AWKWARD);
    public static final Potion LONG_LOVE = register("long_love", new Potion(new StatusEffectInstance(ModEffects.LOVE, 800 )));

    public static final Potion MANA  = register("mana", new Potion(new StatusEffectInstance(ModEffects.MANA, 1800 )), Items.LAPIS_LAZULI, Potions.AWKWARD);
    public static final Potion LONG_MANA = register("long_mana", new Potion(new StatusEffectInstance(ModEffects.MANA, 4800 )));
    public static final Potion STRONG_MANA = register("strong_mana", new Potion(new StatusEffectInstance(ModEffects.MANA, 900, 1 )));


    //mapPotions(POTION BASE, INGREDIENT, RESULT);
    public static void init() { RECIPES.forEach(RecipeToInit::init);

        //make luck brewable and inverting turns it into unluck and back
        mapPotions(Potions.AWKWARD, ModItems.EMERALD_DUST, Potions.LUCK);
        mapPotions(UNLUCK, Items.FERMENTED_SPIDER_EYE, Potions.LUCK);
        mapPotions(Potions.LUCK, Items.FERMENTED_SPIDER_EYE, UNLUCK);
        mapPotions(LONG_UNLUCK, Items.FERMENTED_SPIDER_EYE, LONG_LUCK);
        mapPotions(LONG_LUCK, Items.FERMENTED_SPIDER_EYE, LONG_UNLUCK);

        mapPotions(FEAR, Items.FERMENTED_SPIDER_EYE, LOVE);
        mapPotions(LOVE, Items.FERMENTED_SPIDER_EYE, FEAR);
        mapPotions(LONG_FEAR, Items.FERMENTED_SPIDER_EYE, LONG_LOVE);
        mapPotions(LONG_LOVE, Items.FERMENTED_SPIDER_EYE, LONG_FEAR);

        mapPotions(Potions.SLOW_FALLING, Items.FERMENTED_SPIDER_EYE, Potions.LEAPING);
        mapPotions(Potions.LONG_SLOW_FALLING, Items.FERMENTED_SPIDER_EYE, Potions.LONG_LEAPING);

        mapPotions(Potions.AWKWARD, Items.AMETHYST_SHARD, Potions.HARMING);
        mapPotions(Potions.HARMING, Items.FERMENTED_SPIDER_EYE, Potions.HEALING);
        mapPotions(Potions.STRONG_HARMING, Items.FERMENTED_SPIDER_EYE, Potions.STRONG_HEALING);

        mapPotions(Potions.AWKWARD, Items.CLAY_BALL, Potions.SLOWNESS);
        mapPotions(Potions.SLOWNESS, Items.FERMENTED_SPIDER_EYE, Potions.SWIFTNESS);
        mapPotions(Potions.LONG_SLOWNESS, Items.FERMENTED_SPIDER_EYE, Potions.LONG_SWIFTNESS);
        mapPotions(Potions.STRONG_SLOWNESS, Items.FERMENTED_SPIDER_EYE, Potions.STRONG_SWIFTNESS);

        mapPotions(Potions.AWKWARD, Items.SALMON, Potions.WATER_BREATHING);
        mapPotions(Potions.AWKWARD, Items.COD, Potions.WATER_BREATHING);
        mapPotions(Potions.AWKWARD, Items.TROPICAL_FISH, Potions.WATER_BREATHING);

        mapPotions(Potions.AWKWARD, ModItems.CALCITE_DUST, Potions.INVISIBILITY);
        mapPotions(Potions.INVISIBILITY, Items.FERMENTED_SPIDER_EYE, GLOWING);
        mapPotions(Potions.LONG_INVISIBILITY, Items.FERMENTED_SPIDER_EYE, LONG_GLOWING);
        mapPotions(GLOWING, Items.FERMENTED_SPIDER_EYE, Potions.INVISIBILITY);
        mapPotions(LONG_GLOWING, Items.FERMENTED_SPIDER_EYE, Potions.LONG_INVISIBILITY);

        mapPotions(GRAVITY, Items.FERMENTED_SPIDER_EYE, LEVITATION);
        mapPotions(LONG_GRAVITY, Items.FERMENTED_SPIDER_EYE, LONG_LEVITATION);
        mapPotions(LEVITATION, Items.FERMENTED_SPIDER_EYE, GRAVITY);
        mapPotions(LONG_LEVITATION, Items.FERMENTED_SPIDER_EYE, LONG_GRAVITY);
        mapPotions(STRONG_LEVITATION, Items.FERMENTED_SPIDER_EYE, GRAVITY);

        mapPotions(HASTE, Items.FERMENTED_SPIDER_EYE, MINING_FATIGUE);
        mapPotions(LONG_HASTE, Items.FERMENTED_SPIDER_EYE, LONG_MINING_FATIGUE);
        mapPotions(STRONG_HASTE, Items.FERMENTED_SPIDER_EYE, STRONG_MINING_FATIGUE);
        mapPotions(MINING_FATIGUE, Items.FERMENTED_SPIDER_EYE, HASTE);
        mapPotions(LONG_MINING_FATIGUE, Items.FERMENTED_SPIDER_EYE, LONG_HASTE);
        mapPotions(STRONG_MINING_FATIGUE, Items.FERMENTED_SPIDER_EYE, STRONG_HASTE);

        mapPotions(RED_LIGHT, Items.FERMENTED_SPIDER_EYE, LIGHT);
        mapPotions(LONG_RED_LIGHT, Items.FERMENTED_SPIDER_EYE, LONG_LIGHT);
        mapPotions(STRONG_RED_LIGHT, Items.FERMENTED_SPIDER_EYE, STRONG_LIGHT);
        mapPotions(LIGHT, Items.FERMENTED_SPIDER_EYE, RED_LIGHT);
        mapPotions(LONG_LIGHT, Items.FERMENTED_SPIDER_EYE, LONG_RED_LIGHT);
        mapPotions(STRONG_LIGHT, Items.FERMENTED_SPIDER_EYE, STRONG_RED_LIGHT);

        mapPotions(PHOTOSYNTHESIS, Items.FERMENTED_SPIDER_EYE, ORE_SENSE);
        mapPotions(LONG_PHOTOSYNTHESIS, Items.FERMENTED_SPIDER_EYE, LONG_ORE_SENSE);
        mapPotions(STRONG_PHOTOSYNTHESIS, Items.FERMENTED_SPIDER_EYE, STRONG_ORE_SENSE);
        mapPotions(ORE_SENSE, Items.FERMENTED_SPIDER_EYE, PHOTOSYNTHESIS);
        mapPotions(LONG_ORE_SENSE, Items.FERMENTED_SPIDER_EYE, LONG_PHOTOSYNTHESIS);
        mapPotions(STRONG_ORE_SENSE, Items.FERMENTED_SPIDER_EYE, STRONG_PHOTOSYNTHESIS);

        mapPotions(Potions.AWKWARD, Items.FERMENTED_SPIDER_EYE, Potions.WEAKNESS);
        mapPotions(Potions.WEAKNESS, Items.FERMENTED_SPIDER_EYE, Potions.STRENGTH);
        mapPotions(Potions.LONG_WEAKNESS, Items.FERMENTED_SPIDER_EYE, Potions.LONG_STRENGTH);
    }

    private static Potion register(String id, Potion potion) {
        return Registry.register(Registries.POTION, new Identifier(Magick.MOD_ID, id), potion);
    }

    private static Potion register(String id, Potion potion, Item ingredient, Potion from) {
        RECIPES.add(new RecipeToInit(from, ingredient, potion));
        return Registry.register(Registries.POTION, new Identifier(Magick.MOD_ID, id), potion);
    }

    private static void mapPotions(Potion in, Item ingredient, Potion result) {
        Identifier potionInId = Registries.POTION.getId(in);
        Identifier potionOutId = Registries.POTION.getId(result);
        Optional<Potion> inLong = Registries.POTION.getOrEmpty(new Identifier(potionInId.getNamespace(), "long_" + potionInId.getPath()));
        Optional<Potion> inStrong = Registries.POTION.getOrEmpty(new Identifier(potionInId.getNamespace(), "strong_" + potionInId.getPath()));
        Optional<Potion> outLong = Registries.POTION.getOrEmpty(new Identifier(potionOutId.getNamespace(), "long_" + potionOutId.getPath()));
        Optional<Potion> outStrong = Registries.POTION.getOrEmpty(new Identifier(potionOutId.getNamespace(), "strong_" + potionOutId.getPath()));
        if(outLong.isPresent() && inLong.isPresent()) {
            BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(inLong.get(), ingredient, outLong.get());
        }
        if(outStrong.isPresent() && inStrong.isPresent()) {
            BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(inStrong.get(), ingredient, outStrong.get());
        }
        BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(in, ingredient, result);
    }

    private static void variantRecipes(Potion potion) {
        Identifier id = Registries.POTION.getId(potion);
        Optional<Potion> lengthy = Registries.POTION.getOrEmpty(new Identifier(id.getNamespace(), "long_" + id.getPath()));
        Optional<Potion> strong = Registries.POTION.getOrEmpty(new Identifier(id.getNamespace(), "strong_" + id.getPath()));
        lengthy.ifPresent(value -> BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(potion, Items.REDSTONE, value));
        strong.ifPresent(value -> BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(potion, Items.GLOWSTONE_DUST, value));
    }

    private static class RecipeToInit {

        private final Potion in;
        private final Item ingredient;
        private final Potion result;

        private RecipeToInit(Potion in, Item ingredient, Potion result) {
            this.in = in;
            this.ingredient = ingredient;
            this.result = result;
        }

        public void init() {
            mapPotions(in, ingredient, result);
            variantRecipes(result);
        }
    }
}
