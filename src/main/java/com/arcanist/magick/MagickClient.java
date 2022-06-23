package com.arcanist.magick;



import com.arcanist.magick.registry.ModBlocks;
import com.arcanist.magick.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class MagickClient implements ClientModInitializer {

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        ModEntities.renderEntities();
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TEMP_WEB_BLOCK, RenderLayer.getCutout());
      }
}
