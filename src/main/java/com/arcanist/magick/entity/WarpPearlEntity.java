package com.arcanist.magick.entity;


import com.arcanist.magick.registry.ModEntities;
import com.arcanist.magick.registry.ModItems;
import com.arcanist.magick.statuseffect.RadiusEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class WarpPearlEntity extends ThrownItemEntity {
    public WarpPearlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
                super(entityType, world);
    }

    public WarpPearlEntity(World world, LivingEntity owner) {

        super(ModEntities.WARP_PEARL_ENTITY, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.WARP_PEARL_ITEM;
    }

    public Entity user = this.getOwner();


    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, user), RadiusEffects.power(this.world, user)*7);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            new RadiusEffects().warpPearlEffect(this, this.getX(), this.getY(), this.getZ(), this.world, user);
            this.discard();
        }
    }
}
