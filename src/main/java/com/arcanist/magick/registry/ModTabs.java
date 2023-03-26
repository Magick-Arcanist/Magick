package com.arcanist.magick.registry;

import com.arcanist.magick.Magick;
import com.arcanist.magick.block.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static com.arcanist.magick.Magick.MOD_ID;

public class ModTabs {

    public static final ItemGroup PEARLS_ITEM_GROUP = FabricItemGroup.builder(
                    new Identifier(MOD_ID, "pearls"))
            .icon(() -> new ItemStack(Items.ENDER_PEARL)).build();

    public static final ItemGroup DUST_ITEM_GROUP = FabricItemGroup.builder(
                    new Identifier(MOD_ID, "dust"))
            .icon(() -> new ItemStack(ModItems.DIAMOND_DUST)).build();

    public static void registerPearlsItemGroup(){
        ItemGroupEvents.modifyEntriesEvent(PEARLS_ITEM_GROUP).register(content -> {
            content.add(ModItems.PIECE_OF_PEARL);
            content.add(ModItems.AIR_PEARL_ITEM);
            content.add(ModItems.BOMB_PEARL_ITEM);
            content.add(ModItems.EARTH_PEARL_ITEM);
            content.add(Items.ENDER_PEARL);
            content.add(Items.ENDER_EYE);
            content.add(ModItems.FIRE_PEARL_ITEM);
            content.add(ModItems.ICE_PEARL_ITEM);
            content.add(ModItems.LIGHT_PEARL_ITEM);
            content.add(ModItems.LIGHTNING_PEARL_ITEM);
            content.add(ModItems.PLANT_PEARL_ITEM);
            content.add(ModItems.VAC_PEARL_ITEM);
            content.add(ModItems.WARP_PEARL_ITEM);
            content.add(ModItems.WATER_PEARL_ITEM);
            content.add(ModItems.WEB_PEARL_ITEM);
        });
    }

    public static void registerDustItemGroup(){
        ItemGroupEvents.modifyEntriesEvent(DUST_ITEM_GROUP).register(content -> {
            content.add(ModItems.BLACKSTONE_DUST);
            content.add(ModItems.CALCITE_DUST);
            content.add(ModItems.DIAMOND_DUST);
            content.add(ModItems.EMERALD_DUST);
            content.add(ModItems.END_DUST);
            content.add(Items.GLOWSTONE_DUST);
            content.add(ModItems.NETHER_DUST);
            content.add(ModItems.OBSIDIAN_DUST);
            content.add(ModItems.QUARTZ_DUST);
            content.add(Items.REDSTONE);
            content.add(ModItems.STONE_DUST);
        });
    }
}
