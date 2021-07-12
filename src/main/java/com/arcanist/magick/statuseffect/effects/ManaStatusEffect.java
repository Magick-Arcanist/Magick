package com.arcanist.magick.statuseffect.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;


public class ManaStatusEffect extends StatusEffect {

    public ManaStatusEffect() {
        super(
                StatusEffectType.BENEFICIAL,
                0x5100DC
        );
    }

    @Override
    public boolean canApplyUpdateEffect(int remainingTicks, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
    }


}
