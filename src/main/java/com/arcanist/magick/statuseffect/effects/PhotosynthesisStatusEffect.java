package com.arcanist.magick.statuseffect.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;


    public class PhotosynthesisStatusEffect extends StatusEffect {

        public PhotosynthesisStatusEffect() {
            super(
                    StatusEffectType.BENEFICIAL,
                    0x21ff21
            );
        }

        @Override
        public boolean canApplyUpdateEffect(int remainingTicks, int amplifier) {

            return remainingTicks % 60 == 0;
        }

        @Override
        public void applyUpdateEffect(LivingEntity entity, int amplifier) {
            if (!entity.world.isClient && entity instanceof PlayerEntity && isBrightlight(entity)) {
                ((PlayerEntity) entity).getHungerManager().add(1 << amplifier, 0f);
            }
        }
        // torches wont activate photosynthesis but glowstone and lava will.
        private static boolean isBrightlight(LivingEntity entity) {
                float LightLevel = entity.getBrightnessAtEyes();
            return LightLevel > .65f;
        }


    }
