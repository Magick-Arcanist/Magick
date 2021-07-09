package com.arcanist.magick.entity;


import com.arcanist.magick.MagickClient;
import com.arcanist.magick.entitydata.EntitySpawnPacket;
import com.arcanist.magick.registry.ModEntities;
import com.arcanist.magick.registry.ModItems;
import com.arcanist.magick.statuseffect.PearlEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;


public class LightPearlEntity extends ThrownItemEntity {


    public LightPearlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public LightPearlEntity(World world, LivingEntity owner) {

        super(ModEntities.LightPearlEntityType, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.LIGHT_PEARL_ITEM;
    }

    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, MagickClient.PacketID);
    }

    public Entity user = this.getOwner();

    protected void onCollision(HitResult hitResult) { // called on collision with a block
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            new PearlEffects().lightPearlEffect(this, this.getX(), this.getY(), this.getZ(), this.world, user);
            this.discard();
        }
    }
}