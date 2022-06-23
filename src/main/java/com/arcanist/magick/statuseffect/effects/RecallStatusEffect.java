package com.arcanist.magick.statuseffect.effects;

import com.arcanist.magick.statuseffect.ModStatusEffect;
import com.arcanist.magick.util.EntityRecallProperties;
import com.arcanist.magick.util.DimensionPosition;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectCategory;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.Objects;

public class RecallStatusEffect extends ModStatusEffect {

    public RecallStatusEffect() {
        super(
                StatusEffectCategory.NEUTRAL,
                0xc457ff);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer abstractEntityAttributeContainer, int i) {
        EntityRecallProperties ep = (EntityRecallProperties) entity;
        if (ep.getRecallPos() == null) {
            ep.setRecallPos(new DimensionPosition(entity));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int remainingTicks, int amplifier) {
        return remainingTicks == 1;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.world.isClient) {
            DimensionPosition dp = ((EntityRecallProperties) entity).getRecallPos();
            try {
                if (dp != null) {
                    entity.stopRiding();
                    if (!dp.getWorldId().equals(entity.getEntityWorld().getRegistryKey().getValue())) {
                            entity.moveToWorld((ServerWorld) dp.getWorld(Objects.requireNonNull(entity.getServer())));
                    }
                    entity.requestTeleport(dp.getX(), dp.getY(), dp.getZ());
                }
            } finally {
                ((EntityRecallProperties) entity).setRecallPos(null);
                entity.damage(DamageSource.FALL, 5.0F);
            }
        }
    }
}

