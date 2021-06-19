package com.arcanist.magick.entity;



import com.arcanist.magick.client.MagickClient;
import com.arcanist.magick.entitydata.EntitySpawnPacket;
import com.arcanist.magick.registry.ModEntities;
import com.arcanist.magick.registry.ModItems;
import com.arcanist.magick.statuseffect.PearlEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class BombPearlEntity extends ThrownItemEntity {
    public BombPearlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
                super(entityType, world);
    }

    public BombPearlEntity(World world, LivingEntity owner) {

        super(ModEntities.BombPearlEntityType, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BOMB_PEARL_ITEM;
    }
    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, MagickClient.PacketID);
    }

    public Entity user = this.getOwner();

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        new PearlEffects().bombPearlEffect(this, this.getX(),this.getY(),this.getZ(), this.world, user);
        this.discard();
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        new PearlEffects().bombPearlEffect(this, this.getX(),this.getY(),this.getZ(), this.world, user);
    }

}
