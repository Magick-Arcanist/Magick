package com.arcanist.magick.statuseffect.effects;

import com.arcanist.magick.statuseffect.RadiusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;



public class RedLightStatusEffect extends StatusEffect {

    public RedLightStatusEffect() {
        super(
                StatusEffectCategory.NEUTRAL,
                0xff0000);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.world.isClient) {
            new RadiusEffects().redGlowEffect( amplifier+1, entity, entity.getX(),entity.getY(),entity.getZ(), entity.world);
        }
    }

}
