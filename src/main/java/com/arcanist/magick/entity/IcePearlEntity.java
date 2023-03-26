package com.arcanist.magick.entity;


import com.arcanist.magick.registry.ModEntities;
import com.arcanist.magick.registry.ModItems;
import com.arcanist.magick.statuseffect.RadiusEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;


public class IcePearlEntity extends ThrownItemEntity {
    public IcePearlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public IcePearlEntity(World world, LivingEntity owner) {

        super(ModEntities.ICE_PEARL_ENTITY, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.ICE_PEARL_ITEM;
    }

    public Entity user = this.getOwner();

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        float i = entity instanceof BlazeEntity ? 8 : 0;
        entity.damage(this.getDamageSources().thrown(this, user),i + 1);
        if (entity instanceof LivingEntity) {
            entity.extinguish();
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            new RadiusEffects().icePearlEffect(this, this.getX(), this.getY(), this.getZ(), this.world, user);
            this.discard();
        }
    }

}
