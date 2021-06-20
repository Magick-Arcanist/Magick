package com.arcanist.magick.statuseffect.effects;

import com.arcanist.magick.statuseffect.PearlEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;


public class OreSenseStatusEffect extends StatusEffect {

    public OreSenseStatusEffect() {
        super(
                StatusEffectType.BENEFICIAL,
                0x000000
        );
    }

    @Override
    public boolean canApplyUpdateEffect(int remainingTicks, int amplifier) {

        return remainingTicks % 30 == 0; // this means the effect isn't applied constantly so you don't hear a constant *DINGDINGDINGDING*
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        new PearlEffects().oreSight( amplifier, entity, entity.getX(),entity.getY(),entity.getZ(), entity.world);
    }
}
