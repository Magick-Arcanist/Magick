package com.arcanist.magick;

import com.arcanist.magick.Magick;
import com.arcanist.magick.entity.AirPearlEntity;
import com.arcanist.magick.entity.FirePearlEntity;
import com.arcanist.magick.entitydata.EntitySpawnPacket;
import com.arcanist.magick.registry.ModBlocks;
import com.arcanist.magick.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;


public class MagickClient implements ClientModInitializer {
    public static final Identifier PacketID = new Identifier(Magick.MOD_ID, "spawn_packet");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(ModEntities.AirPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ModEntities.BombPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ModEntities.EarthPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ModEntities.FirePearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ModEntities.IcePearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ModEntities.LightningPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ModEntities.LightPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ModEntities.PlantPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ModEntities.VacPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ModEntities.WarpPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ModEntities.WaterPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ModEntities.WebPearlEntityType, FlyingItemEntityRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TEMP_WEB_BLOCK, RenderLayer.getTranslucent());
        receiveEntityPacket();
    }

    public void receiveEntityPacket() {
        ClientSidePacketRegistry.INSTANCE.register(PacketID, (ctx, byteBuf) -> {
            EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
            UUID uuid = byteBuf.readUuid();
            int entityId = byteBuf.readVarInt();
            Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
            ctx.getTaskQueue().execute(() -> {
                if (MinecraftClient.getInstance().world == null)
                    throw new IllegalStateException("Tried to spawn entity in a null world!");
                Entity e = et.create(MinecraftClient.getInstance().world);
                if (e == null)
                    throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
                e.updateTrackedPosition(pos);
                e.setPos(pos.x, pos.y, pos.z);
                e.setId(entityId);
                e.setUuid(uuid);
                MinecraftClient.getInstance().world.addEntity(entityId, e);
            });
        });
    }
}
