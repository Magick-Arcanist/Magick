package com.arcanist.magick.mixin;

import com.arcanist.magick.registry.ModEffects;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EscapeDangerGoal.class)
public abstract class EscapeDangerGoalMixin {
	
	@Shadow protected  PathAwareEntity mob;
	protected double targetX;
	protected double targetY;
	protected double targetZ;



	@Inject(method = "canStart", at = @At(value = "HEAD"), cancellable = true)
	public void fearCheck(CallbackInfoReturnable<Boolean> info) {
		if (this.mob.getStatusEffect(ModEffects.FEAR) !=null) {
			info.setReturnValue(findTarget());
		}
	}

	@Shadow         protected boolean findTarget() {
		Vec3d vec3d = NoPenaltyTargeting.find(this.mob, 5, 4);
		if (vec3d == null) {
			return false;
		} else {
			this.targetX = vec3d.x;
			this.targetY = vec3d.y;
			this.targetZ = vec3d.z;
			return true;
		}
	}
	
}
