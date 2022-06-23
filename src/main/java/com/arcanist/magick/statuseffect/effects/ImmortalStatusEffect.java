package com.arcanist.magick.statuseffect.effects;

import com.arcanist.magick.registry.ModEffects;
import com.arcanist.magick.statuseffect.ModStatusEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;


public class ImmortalStatusEffect extends ModStatusEffect {

    public ImmortalStatusEffect() {
        super(
                StatusEffectCategory.BENEFICIAL,
                0xa0a1a3);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
    }

    public static boolean hasEffect(Entity entity) {
        return ((LivingEntity) entity).hasStatusEffect(ModEffects.IMMORTAL);
    }
}
