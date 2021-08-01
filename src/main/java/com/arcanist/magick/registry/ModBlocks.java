package com.arcanist.magick.registry;

import com.arcanist.magick.Magick;
import com.arcanist.magick.block.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    //Are you wondering why the Material for the invisible reda nad white blocks is REPLACEABLE_PLANT instead of AIR?
    //Because AIR gets pushed by pistons unless you add .air() to the settings, but doing so makes the block not tick properly so the invisible blocks hang around forever.
    //is it a good solution? No. does it work? Yes. Am I happy about it? No. Could I fix it? Not without significantly more work for the same result.

    public static final WhiteBlock WHITE_BLOCK = new WhiteBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().luminance((state) -> {return 10;}).ticksRandomly());
    public static final RedBlock RED_BLOCK = new RedBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().ticksRandomly());
    public static final RedWhiteBlock RED_WHITE_BLOCK = new RedWhiteBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().luminance((state) -> {return 10;}).ticksRandomly());
    public static final LightOrb LIGHT_ORB = new LightOrb(FabricBlockSettings.of(Material.AIR));
    public static final TempWebBlock TEMP_WEB_BLOCK = new TempWebBlock(FabricBlockSettings.of(Material.COBWEB).breakByTool((FabricToolTags.SWORDS) , 0).breakByTool((FabricToolTags.SHEARS) , 0).noCollision().requiresTool().strength(4.0F).nonOpaque().ticksRandomly());


    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(Magick.MOD_ID, "light_orb"), LIGHT_ORB);
        Registry.register(Registry.BLOCK, new Identifier(Magick.MOD_ID, "temp_web_block"), TEMP_WEB_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Magick.MOD_ID, "red_block"), RED_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Magick.MOD_ID, "white_block"), WHITE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Magick.MOD_ID, "red_white_block"), RED_WHITE_BLOCK);
    }
}
