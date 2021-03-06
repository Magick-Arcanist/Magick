package com.arcanist.magick.statuseffect.effects;

import com.arcanist.magick.statuseffect.ModStatusEffect;
import com.arcanist.magick.statuseffect.RadiusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FearStatusEffect extends ModStatusEffect {

    public FearStatusEffect() {
        super(
                StatusEffectCategory.HARMFUL,
                0xedff24);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
            new RadiusEffects().fearEffect( amplifier+1, entity, entity.getX(),entity.getY(),entity.getZ(), entity.world);
    }
}
