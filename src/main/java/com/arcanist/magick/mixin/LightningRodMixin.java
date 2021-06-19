package com.arcanist.magick.mixin;

import com.arcanist.magick.entity.LightningPearlEntity;
import com.arcanist.magick.registry.ModEffects;
import com.arcanist.magick.registry.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.client.realms.Request;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightningRodBlock.class)
public abstract class LightningRodMixin {

	@Inject(at = @At("HEAD"), method = "onProjectileHit", cancellable = true)
	public void lightningPearl(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile, CallbackInfo cb) {
		if (world.isThundering() && projectile instanceof LightningPearlEntity) ;{
			BlockPos blockPos = hit.getBlockPos();
			if (world.isSkyVisible(blockPos)) {
				LightningEntity lightningEntity = (LightningEntity)EntityType.LIGHTNING_BOLT.create(world);
				lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos.up()));
				Entity entity = projectile.getOwner();
				lightningEntity.setChanneler(entity instanceof ServerPlayerEntity ? (ServerPlayerEntity)entity : null);
				world.spawnEntity(lightningEntity);

			}
		}
	}

}
