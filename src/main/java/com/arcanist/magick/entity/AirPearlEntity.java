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

    public static void wait(int ms, int ns)
    {
        try
        {
            Thread.sleep(ms, ns);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public Entity user = this.getOwner();

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        new PearlEffects().airPearlEffect(this, this.getX(), this.getY(), this.getZ(), this.world, user);{
            wait(20,0);
            { // *FIX* without the wait the box deletes before affecting the player, but this waits the whole thread.
                this.discard();}
        }
    }
}
