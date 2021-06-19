package com.arcanist.magick.statuseffect.effects;

import com.arcanist.magick.statuseffect.PearlEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;


public class WhiteLightStatusEffect extends StatusEffect {

    public WhiteLightStatusEffect() {
        super(
                StatusEffectType.NEUTRAL,
                0xffa6a6);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.world.isClient) {
            new PearlEffects().whiteGlowEffect( amplifier+1, entity, entity.getX(),entity.getY(),entity.getZ(), entity.world);
        }
    }

}
