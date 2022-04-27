package com.arcanist.magick.mixin;

import com.arcanist.magick.registry.ModEffects;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TemptGoal.class)
public abstract class TemptGoalMixin {

	@Inject(method = "isTemptedBy", at = @At(value = "HEAD"), cancellable = true)
	public void lovecheck(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
		if (entity != null && entity.getStatusEffect(ModEffects.LOVE) != null) {
			cir.setReturnValue(true);
		}
	}

}
