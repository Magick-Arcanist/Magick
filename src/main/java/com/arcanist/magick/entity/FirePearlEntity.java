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
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;


public class FirePearlEntity extends ThrownItemEntity {
    public FirePearlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
                super(entityType, world);
    }

    public FirePearlEntity(World world, LivingEntity owner) {

        super(ModEntities.FirePearlEntityType, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.FIRE_PEARL_ITEM;
    }
    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, MagickClient.PacketID);
    }

    public Entity user = this.getOwner();

    public float damage = PearlEffects.power(this.world, user);

    protected void onEntityHit(EntityHitResult entityHitResult) { // called on entity hit.
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int i = entity instanceof SnowGolemEntity ? 6 : 0;
        entity.damage(DamageSource.thrownProjectile(this, user), (float) i + damage);
        if (entity instanceof LivingEntity) {
            entity.setOnFireFor(200);
        }
    }

    protected void onCollision(HitResult hitResult) { // called on collision with a block
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            new PearlEffects().firePearlEffect(this, this.getX(),this.getY(),this.getZ(), this.world, user);
            this.discard(); }
    }

}
