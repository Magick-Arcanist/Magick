package com.arcanist.magick.statuseffect.effects;


import com.arcanist.magick.statuseffect.ModStatusEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;


public class GravityStatusEffect extends ModStatusEffect {

        public GravityStatusEffect() {
            super(
                    StatusEffectCategory.HARMFUL,
                    0x5400ad
            );

        }

        @Override
        public boolean canApplyUpdateEffect(int duration, int amplifier) {
            return true;
        }

        @Override
        public void applyUpdateEffect(LivingEntity entity, int amplifier) {
            if (!entity.isOnGround()) {
            entity.move(MovementType.SELF, new Vec3d(0, -1.0 * (amplifier + 1), 0));
            }
        }
}

