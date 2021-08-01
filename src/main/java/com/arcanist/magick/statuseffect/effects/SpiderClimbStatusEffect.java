package com.arcanist.magick.statuseffect.effects;

import com.arcanist.magick.registry.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpiderClimbStatusEffect extends StatusEffect {

    public SpiderClimbStatusEffect() {
        super(
                StatusEffectType.BENEFICIAL, // whether beneficial or harmful for entities, honestly not sure what difference this makes from a practical standpoint
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
