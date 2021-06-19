package com.arcanist.magick.entity;


import com.arcanist.magick.client.MagickClient;
import com.arcanist.magick.entitydata.EntitySpawnPacket;
import com.arcanist.magick.registry.ModEntities;
import com.arcanist.magick.registry.ModItems;
import com.arcanist.magick.statuseffect.PearlEffects;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class LightningPearlEntity extends ThrownItemEntity {
    public LightningPearlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
                super(entityType, world);
    }

    public LightningPearlEntity(World world, LivingEntity owner) {

        super(ModEntities.LightningPearlEntityType, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.LIGHTNING_PEARL_ITEM;
    }
    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, MagickClient.PacketID);
    }

    public Entity user = this.getOwner();

    protected void onCollision(HitResult hitResult) { // called on collision with a block
      super.onCollision(hitResult);
         {
             new PearlEffects().lightningPearlEffect(this, this.getX(),this.getY(),this.getZ(), this.world, user);
         }
                this.discard(); // kills the projectile
    }



}
