package com.arcanist.magick.entity;


import com.arcanist.magick.client.MagickClient;
import com.arcanist.magick.entitydata.EntitySpawnPacket;
import com.arcanist.magick.registry.ModEntities;
import com.arcanist.magick.registry.ModItems;
import com.arcanist.magick.statuseffect.PearlEffects;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.*;

public class AirPearlEntity extends ThrownItemEntity {

    public AirPearlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public AirPearlEntity(World world, LivingEntity owner) {
        super(ModEntities.AirPearlEntityType, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.AIR_PEARL_ITEM;
    }

    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, MagickClient.PacketID);
    }

    public Entity user = this.getOwner();

    public float power = PearlEffects.power(this.world, user)*3;

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if(entity instanceof LivingEntity) {
            ((LivingEntity) entity).takeKnockback(power*0.2F, this.getX() - entity.getX(), this.getZ() - entity.getZ());
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        new PearlEffects().airPearlEffect(this, this.getX(), this.getY(), this.getZ(), this.world, user);{
             // *FIX* without the wait the box deletes before affecting the player, but this waits the whole thread.
                this.discard();
        }
    }
}
