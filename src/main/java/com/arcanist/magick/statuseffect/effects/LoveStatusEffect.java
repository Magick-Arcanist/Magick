package com.arcanist.magick.statuseffect.effects;

import com.arcanist.magick.registry.ModEffects;
import com.arcanist.magick.statuseffect.RadiusEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;

public class LoveStatusEffect extends StatusEffect {

    public LoveStatusEffect() {
        super(
                StatusEffectCategory.BENEFICIAL,
                0xff546b);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {return true;}

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
            if (entity instanceof AnimalEntity) {
                ((AnimalEntity) entity).setLoveTicks(80);
            }
            if (entity instanceof PlayerEntity) {
                new RadiusEffects().loveEffect(amplifier + 1, entity, entity.getX(), entity.getY(), entity.getZ(), entity.world);
            }
    }
    public static boolean hasEffect(Entity entity) {
        return ((LivingEntity) entity).hasStatusEffect(ModEffects.LOVE);
    }
}
