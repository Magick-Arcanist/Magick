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


public class PlantPearlEntity extends ThrownItemEntity {
    public PlantPearlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public PlantPearlEntity(World world, LivingEntity owner) {

        super(ModEntities.PLANT_PEARL_ENTITY, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.PLANT_PEARL_ITEM;
    }

    public Entity user = this.getOwner();

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, user), 0);
    }

    protected void onCollision(HitResult hitResult) { // called on collision with a block
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            new RadiusEffects().plantPearlEffect(this, this.getX(),this.getY(),this.getZ(), this.world, user);
            this.discard();
        }
    }

}