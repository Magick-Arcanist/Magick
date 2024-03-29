package com.arcanist.magick.entity;

import com.arcanist.magick.registry.ModEntities;
import com.arcanist.magick.registry.ModItems;
import com.arcanist.magick.statuseffect.RadiusEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class AirPearlEntity extends ThrownItemEntity {
    public AirPearlEntity(EntityType<? extends AirPearlEntity> entityType, World world) {
        super(entityType, world);
    }

    public AirPearlEntity(World world, LivingEntity owner) {
        super(ModEntities.AIR_PEARL_ENTITY, owner, world);
    }

    protected Item getDefaultItem() {
        return ModItems.AIR_PEARL_ITEM;
    }

    public Entity user = this.getOwner();

    public float power = RadiusEffects.power(this.world, user);

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if(entity instanceof LivingEntity) {
            ((LivingEntity) entity).takeKnockback(power/4, this.getX() - entity.getX(), this.getZ() - entity.getZ());
            entity.damage(this.getDamageSources().thrown(this, user), 0);
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
        new RadiusEffects().airPearlEffect(this, this.getX(), this.getY(), this.getZ(), this.world, user);
            {
                this.discard();
            }
        }
    }
}
