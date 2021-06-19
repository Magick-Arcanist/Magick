package com.arcanist.magick.statuseffect.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;

public class ClearEffectStatusEffect extends StatusEffect {

    public ClearEffectStatusEffect() {
        super(
                StatusEffectType.HARMFUL, // whether beneficial or harmful for entities
                0xffffff); // color in RGB
    }

    // This method is called every tick to check weather it should apply the status effect or not
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the status effect every tick.
        return true;
    }

    // This method is called when it applies the status effect. We implement custom functionality here.
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
