package com.arcanist.magick.statuseffect.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.passive.AnimalEntity;

public class LoveStatusEffect extends StatusEffect {

    public LoveStatusEffect() {
        super(
                StatusEffectType.BENEFICIAL,
                0xff546b);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof AnimalEntity) {
            ((AnimalEntity) entity).setLoveTicks(20);
            //There are other parts to this effect in the LivingEntityMixin

        }
    }
}
