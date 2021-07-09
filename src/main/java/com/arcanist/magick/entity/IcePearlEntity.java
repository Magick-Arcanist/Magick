package com.arcanist.magick.entity;


import com.arcanist.magick.MagickClient;
import com.arcanist.magick.entitydata.EntitySpawnPacket;
import com.arcanist.magick.registry.ModEntities;
import com.arcanist.magick.registry.ModItems;
import com.arcanist.magick.statuseffect.PearlEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;


public class IcePearlEntity extends ThrownItemEntity {
    public IcePearlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public IcePearlEntity(World world, LivingEntity owner) {

        super(ModEntities.IcePearlEntityType, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.ICE_PEARL_ITEM;
    }

    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, MagickClient.PacketID);
    }

    public Entity user = this.getOwner();

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int i = entity instanceof BlazeEntity ? 8 : 0;
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), (float) i + 1);
        if (entity instanceof LivingEntity) {
            entity.extinguish();
            entity.playSound(SoundEvents.ENTITY_GENERIC_SPLASH, 1F, 1F);
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            new PearlEffects().icePearlEffect(this, this.getX(), this.getY(), this.getZ(), this.world, user);
            this.discard();
        }
    }

}
