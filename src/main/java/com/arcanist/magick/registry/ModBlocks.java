package com.arcanist.magick.registry;

import com.arcanist.magick.Magick;
import com.arcanist.magick.block.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {


    public static final WhiteBlock WHITE_BLOCK = new WhiteBlock(FabricBlockSettings.of(Material.AIR));
    public static final RedBlock RED_BLOCK = new RedBlock(FabricBlockSettings.of(Material.AIR));
    public static final RedWhiteBlock RED_WHITE_BLOCK = new RedWhiteBlock(FabricBlockSettings.of(Material.AIR));
    public static final LightOrb LIGHT_ORB = new LightOrb(FabricBlockSettings.of(Material.DECORATION));
    public static final TempWebBlock TEMP_WEB_BLOCK = new TempWebBlock(FabricBlockSettings.of(Material.COBWEB).breakByTool(FabricToolTags.SWORDS , 0).noCollision().requiresTool().strength(4.0F).nonOpaque());


    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(Magick.MOD_ID, "light_orb"), LIGHT_ORB);
        Registry.register(Registry.BLOCK, new Identifier(Magick.MOD_ID, "temp_web_block"), TEMP_WEB_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Magick.MOD_ID, "red_block"), RED_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Magick.MOD_ID, "white_block"), WHITE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Magick.MOD_ID, "red_white_block"), RED_WHITE_BLOCK);
    }
}
