package com.arcanist.magick.statuseffect.effects;

import com.arcanist.magick.statuseffect.PearlEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class FearStatusEffect extends StatusEffect {

    public FearStatusEffect() {
        super(
                StatusEffectType.HARMFUL,
                0xedff24);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
            new PearlEffects().fearEffect( amplifier+1, entity, entity.getX(),entity.getY(),entity.getZ(), entity.world);
    }
}
