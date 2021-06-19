package com.arcanist.magick.mixin;

import com.arcanist.magick.registry.ModPotions;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingRecipeRegistry.class)
public abstract class BrewRegistryMixin {

	@Shadow private static void registerPotionRecipe(Potion input, Item item, Potion output) {}
	
	@Inject(method = "registerDefaults", at = @At("HEAD"))
	private static void overrideDefaults(CallbackInfo cb) {
		registerPotionRecipe(Potions.LEAPING, Items.FERMENTED_SPIDER_EYE, Potions.SLOW_FALLING);
		registerPotionRecipe(Potions.LONG_LEAPING, Items.FERMENTED_SPIDER_EYE, Potions.LONG_SLOW_FALLING);
		registerPotionRecipe(Potions.STRONG_LEAPING, Items.FERMENTED_SPIDER_EYE, Potions.SLOW_FALLING);
		registerPotionRecipe(Potions.NIGHT_VISION, Items.FERMENTED_SPIDER_EYE, ModPotions.BLINDNESS);
		registerPotionRecipe(Potions.LONG_NIGHT_VISION, Items.FERMENTED_SPIDER_EYE, ModPotions.LONG_BLINDNESS);
	}
	
}
