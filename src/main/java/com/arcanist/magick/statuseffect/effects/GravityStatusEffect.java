package com.arcanist.magick.statuseffect.effects;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;


public class GravityStatusEffect extends StatusEffect {

        public GravityStatusEffect() {
            super(
                    StatusEffectType.HARMFUL,
                    0x5400ad// color in RGB
            );

        }

        @Override
        public boolean canApplyUpdateEffect(int duration, int amplifier) {
            // In our case, we just make it return true so that it applies the status effect every tick.
            return true;
        }

        @Override
        public void applyUpdateEffect(LivingEntity entity, int amplifier) {
            if (!entity.world.isClient && !entity.isOnGround()) {
                entity.move(MovementType.SELF, new Vec3d(0, -1.0 * (amplifier + 1), 0));
            }
            if (entity instanceof PlayerEntity) {
                entity.move(MovementType.PLAYER, new Vec3d(0, -0.3 * (amplifier + 1), 0) );
        }

    }
}

