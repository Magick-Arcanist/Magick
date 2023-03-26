package com.arcanist.magick.statuseffect.effects;

import com.arcanist.magick.statuseffect.ModStatusEffect;
import com.arcanist.magick.util.DimensionPosition;
import com.arcanist.magick.util.EntityReturnProperties;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.EndermiteEntity;


public class ReturningStatusEffect extends ModStatusEffect {

    public ReturningStatusEffect() {
        super(
                StatusEffectCategory.NEUTRAL,
                0xc457ff);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer abstractEntityAttributeContainer, int i) {
        EntityReturnProperties ep = (EntityReturnProperties) entity;
        if (ep.getReturnPos() == null) {
            ep.setReturnPos(new DimensionPosition(entity));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int remainingTicks, int amplifier) {
        return remainingTicks == 1;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.world.isClient) {
            DimensionPosition pos = ((EntityReturnProperties) entity).getReturnPos();
            try {
                if (pos != null) {
                    entity.stopRiding();
                    if (!pos.getWorldId().equals(entity.getEntityWorld().getRegistryKey().getValue())) {
                        EndermiteEntity endermiteEntity = EntityType.ENDERMITE.create(entity.world);
                        assert endermiteEntity != null;
                        endermiteEntity.refreshPositionAndAngles(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(), entity.getPitch());
                        entity.world.spawnEntity(endermiteEntity);
                        // entity.moveToWorld((ServerWorld) dp.getWorld((Objects.requireNonNull(entity.getServer()))));  // not working, see ServerPlayerEntityMixin
                        // entity.requestTeleport(dp.getX(), dp.getY(), dp.getZ());
                    }
                    else {
                        entity.requestTeleport(pos.getX(), pos.getY(), pos.getZ());
                    }
                }
            } finally {
               // entity.damage(DamageSource.FALL, 5.0F);
                entity.damage(entity.getDamageSources().fall(),5);
                ((EntityReturnProperties) entity).setReturnPos(null);
            }
        }
    }
}

