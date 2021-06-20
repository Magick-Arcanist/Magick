package com.arcanist.magick.statuseffect.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;

public class ClearEffectStatusEffect extends StatusEffect {

    public ClearEffectStatusEffect() {
        super(
                StatusEffectType.HARMFUL,
                0xffffff);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.world.isClient) {
            entity.clearStatusEffects();
        }
        if (entity instanceof PlayerEntity) {
            entity.clearStatusEffects();
        }
    }
}
