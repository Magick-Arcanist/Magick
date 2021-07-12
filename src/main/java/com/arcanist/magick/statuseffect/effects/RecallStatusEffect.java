package com.arcanist.magick.statuseffect.effects;

import com.arcanist.magick.entitydata.EntityProperties;
import com.arcanist.magick.util.DimensionalPosition;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.mob.EndermiteEntity;

public class RecallStatusEffect extends StatusEffect {

    public RecallStatusEffect() {
        super(
                StatusEffectType.NEUTRAL,
                0xc457ff);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer abstractEntityAttributeContainer, int i) {
        EntityProperties ep = (EntityProperties) entity;
        if (ep.getRecallPosition() == null) {
            ep.setRecallData(new DimensionalPosition(entity));
        }
        else {
            ep.getRecallPosition();
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int remainingTicks, int amplifier) {
        return remainingTicks == 1;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.world.isClient) {
            DimensionalPosition pos = ((EntityProperties) entity).getRecallPosition();
            try {
                if (pos != null) {
                    entity.stopRiding();
                    if (!pos.getWorldId().equals(entity.getEntityWorld().getRegistryKey().getValue())) {
                        EndermiteEntity endermiteEntity = EntityType.ENDERMITE.create(entity.world);
                        endermiteEntity.refreshPositionAndAngles(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(), entity.getPitch());
                        entity.world.spawnEntity(endermiteEntity);

                    } else {
                        entity.teleport(pos.getX(), pos.getY(), pos.getZ());
                    }
                }
            } finally {
                ((EntityProperties) entity).setRecallData(null);
            }
        }
    }


}

