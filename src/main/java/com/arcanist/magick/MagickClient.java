package com.arcanist.magick;

import com.arcanist.magick.registry.ModBlocks;
import com.arcanist.magick.registry.ModEntities;
import com.arcanist.magick.util.ReceivePacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.Identifier;

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
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TEMP_WEB_BLOCK, RenderLayer.getCutout());
          new ReceivePacket().receiveEntityPacket();
      }
}
