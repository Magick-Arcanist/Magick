package com.arcanist.magick.statuseffect.effects;

import com.arcanist.magick.statuseffect.ModStatusEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.Objects;

public class FearStatusEffect extends ModStatusEffect {

    private static final SoundEvent CREEPER = SoundEvents.ENTITY_CREEPER_PRIMED;
    private static final SoundEvent WARDEN = SoundEvents.ENTITY_WARDEN_NEARBY_CLOSER;
    private static final SoundEvent ZOMBIE = SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    private static final int SOUND_DELAY = 150; // Sound delay in ticks (10 seconds)

    private int soundTimer = 0;

    public FearStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0xedff24);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player && entity.world.isClient) {
            // Play sound randomly with delay
            if (soundTimer <= 0 && player.getRandom().nextFloat() < 0.1F) {
                SoundEvent sound = switch (player.getRandom().nextInt(3)) {
                    case 0 -> WARDEN;
                    case 1 -> CREEPER;
                    case 2 -> ZOMBIE;
                    default -> null;
                };
                if (sound != null) {
                    player.playSound(sound, 1, 1);
                    soundTimer = SOUND_DELAY;
                }
            } else {
                soundTimer--;
            }
        }
        // Make mobs run away from nearby entities
        if (entity instanceof MobEntity mob) {
            double speedMultiplier = 6.0; // Default speed multiplier for non-villagers/illagers
            if (mob instanceof VillagerEntity || mob instanceof IllagerEntity) {
                speedMultiplier = 2.0; // Speed multiplier for villagers/illagers
            }
            double baseSpeed = Objects.requireNonNull(mob.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)).getBaseValue();

            // Check for nearby entities and move away from them
            List<Entity> nearbyEntities = entity.world.getOtherEntities(entity, entity.getBoundingBox().expand(10.0, 6.0, 10.0));
            for (Entity nearbyEntity : nearbyEntities) {
                if (nearbyEntity instanceof LivingEntity) {
                    Vec3d awayFromEntity = entity.getPos().subtract(nearbyEntity.getPos()).normalize();
                    mob.getNavigation().startMovingTo(entity.getX() + awayFromEntity.x * 5.0,
                            entity.getY(),
                            entity.getZ() + awayFromEntity.z * 5.0,
                            baseSpeed * speedMultiplier);
                    return;
                }
            }
            // If no nearby entities, move randomly
            mob.getNavigation().startMovingTo(
                    mob.getX() + mob.getRandom().nextInt(20) - 10,
                    mob.getY() + mob.getRandom().nextInt(6) - 3,
                    mob.getZ() + mob.getRandom().nextInt(30) - 10,
                    baseSpeed * speedMultiplier);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}
