package com.arcanist.magick.mixin;

import com.arcanist.magick.registry.ModEffects;
import com.arcanist.magick.statuseffect.effects.ImmortalStatusEffect;
import com.arcanist.magick.statuseffect.effects.LoveStatusEffect;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TemptGoal.class)
public abstract class TemptGoalMixin {
	@Shadow
	protected PlayerEntity closestPlayer;

	@Inject(method = "isTemptedBy", at = @At(value = "HEAD"), cancellable = true)
	public void loveCheck(CallbackInfoReturnable<Boolean> info) {
		if (closestPlayer != null && closestPlayer.hasStatusEffect(ModEffects.LOVE)) {
			info.setReturnValue(true);
		}
	}

}
