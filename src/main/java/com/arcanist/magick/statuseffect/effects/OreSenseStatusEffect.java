package com.arcanist.magick.statuseffect.effects;

import com.arcanist.magick.statuseffect.ModStatusEffect;
import com.arcanist.magick.statuseffect.RadiusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;



public class OreSenseStatusEffect extends ModStatusEffect {

    public OreSenseStatusEffect() {
        super(
                StatusEffectCategory.BENEFICIAL,
                0x000000
        );
    }

    @Override
    public boolean canApplyUpdateEffect(int remainingTicks, int amplifier) {

        return remainingTicks % 30 == 0; // this means the effect isn't applied constantly so you don't hear a constant *DINGDINGDINGDING*
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
            new RadiusEffects().oreSight(amplifier, entity.getX(), entity.getY(), entity.getZ(), entity.world);
    }
}
