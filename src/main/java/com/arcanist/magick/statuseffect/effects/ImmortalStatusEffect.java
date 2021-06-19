package com.arcanist.magick.statuseffect.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class ImmortalStatusEffect extends StatusEffect {

    public ImmortalStatusEffect() {
        super(
                StatusEffectType.BENEFICIAL, // whether beneficial or harmful for entities
                0xa0a1a3); // color in RGB
    }

    // This method is called every tick to check weather it should apply the status effect or not
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    // This method is called when it applies the status effect. We implement custom functionality here.
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {

    }
}
