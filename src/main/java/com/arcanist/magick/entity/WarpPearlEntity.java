package com.arcanist.magick.entity;


import com.arcanist.magick.client.MagickClient;
import com.arcanist.magick.entitydata.EntitySpawnPacket;
import com.arcanist.magick.registry.ModEntities;
import com.arcanist.magick.registry.ModItems;
import com.arcanist.magick.statuseffect.PearlEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class WarpPearlEntity extends ThrownItemEntity {
    public WarpPearlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
                super(entityType, world);
    }

    public WarpPearlEntity(World world, LivingEntity owner) {

        super(ModEntities.WarpPearlEntityType, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.WARP_PEARL_ITEM;
    }
    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, MagickClient.PacketID);
    }

    public Entity user = this.getOwner();

    public float damage = PearlEffects.power(this.world, user);

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(DamageSource.MAGIC, damage);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        {
          new PearlEffects().warpPearlEffect( this, this.getX(),this.getY(),this.getZ(), this.world, user);
            }
            this.discard();
    }
}
