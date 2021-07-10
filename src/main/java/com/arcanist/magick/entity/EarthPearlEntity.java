package com.arcanist.magick.entity;


import com.arcanist.magick.MagickClient;
import com.arcanist.magick.entitydata.EntitySpawnPacket;
import com.arcanist.magick.registry.ModEntities;
import com.arcanist.magick.registry.ModItems;
import com.arcanist.magick.statuseffect.PearlEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;


public class EarthPearlEntity extends ThrownItemEntity {

    public EarthPearlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public EarthPearlEntity(World world, LivingEntity owner) {
        super(ModEntities.EarthPearlEntityType, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.EARTH_PEARL_ITEM;
    }

    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, MagickClient.PacketID);
    }

    public Entity user = this.getOwner();

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).takeKnockback(1.5F, this.getX() - entity.getX(), this.getZ() - entity.getZ());
            entity.damage(DamageSource.thrownProjectile(this, user), 0);
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            new PearlEffects().earthPearlEffect(this, this.getX(),this.getY(),this.getZ(), this.world, user);
            this.discard();
        }
    }

}