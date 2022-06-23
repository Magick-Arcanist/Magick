package com.arcanist.magick.mixin;

import com.arcanist.magick.statuseffect.effects.SpiderClimbStatusEffect;
import net.minecraft.block.BlockState;
import net.minecraft.block.CobwebBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CobwebBlock.class)
public abstract class CobwebMixin {

    @Inject(at = @At("TAIL"), method = "onEntityCollision", cancellable = true)
    public void SpiderClimbCheck(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
      try{ if (SpiderClimbStatusEffect.hasEffect(entity)){
            entity.slowMovement(state, new Vec3d(0.D, 0.0D, 0.0D));
        }
    } catch (Exception ignored) {
      }
    }
}
