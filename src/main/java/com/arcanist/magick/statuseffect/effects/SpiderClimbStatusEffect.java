package com.arcanist.magick.statuseffect.effects;

import com.arcanist.magick.registry.ModEffects;
import com.arcanist.magick.statuseffect.ModStatusEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SpiderClimbStatusEffect extends ModStatusEffect {

    public SpiderClimbStatusEffect() {
        super(
                StatusEffectCategory.BENEFICIAL, // whether beneficial or harmful for entities, honestly not sure what difference this makes from a practical standpoint
                0xffaa00); // color in RGB
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    } // This method is called every tick to check weather it should apply the status effect or not

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // This method is called when it applies the status effect. We implement custom functionality here. in this case there is none because its called from the LivingEntityMixin
    }

    public static boolean hasEffect(Entity entity) {
        return ((LivingEntity) entity).hasStatusEffect(ModEffects.SPIDERCLIMB);
    }
}
