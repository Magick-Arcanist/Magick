package com.arcanist.magick.statuseffect.effects;

import com.arcanist.magick.statuseffect.PearlEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class LoveStatusEffect extends StatusEffect {

    public LoveStatusEffect() {
        super(
                StatusEffectType.BENEFICIAL,
                0xff546b);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof AnimalEntity) {
            ((AnimalEntity) entity).setLoveTicks(40);
        }
        if (entity instanceof PlayerEntity){
            new PearlEffects().loveEffect( amplifier+1, entity, entity.getX(),entity.getY(),entity.getZ(), entity.world);
        }
    }
}
