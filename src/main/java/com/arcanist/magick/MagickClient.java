package com.arcanist.magick;


import com.arcanist.magick.registry.ModBlocks;
import com.arcanist.magick.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class MagickClient implements ClientModInitializer {

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.AirPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BombPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.EarthPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.FirePearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.IcePearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LightningPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LightPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.PlantPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.VacPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.WarpPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.WaterPearlEntityType, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.WebPearlEntityType, FlyingItemEntityRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TEMP_WEB_BLOCK, RenderLayer.getCutout());
      }
}
