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
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class VacPearlEntity extends ThrownItemEntity {
    public VacPearlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
                super(entityType, world);
    }

    public VacPearlEntity(World world, LivingEntity owner) {

        super(ModEntities.VacPearlEntityType, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.VAC_PEARL_ITEM;
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
            ((LivingEntity) entity).takeKnockback(power*0.2F, entity.getX() - this.getX(), entity.getZ() - this.getZ());
            entity.damage(DamageSource.thrownProjectile(this, user), 0);
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
        new PearlEffects().vacPearlEffect(this, this.getX(), this.getY(), this.getZ(), this.world, user);
        this.discard();
        }
    }
}
