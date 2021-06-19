package com.arcanist.magick;

import com.arcanist.magick.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;


public class Magick implements ModInitializer {

    public static final String MOD_ID = "magick";

    //Creates Magick TAB
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
     new Identifier(MOD_ID, "general"))
     .icon(() -> new ItemStack(Items.ENDER_PEARL))
      .appendItems(stacks -> {
          stacks.add(new ItemStack(ModItems.PIECE_OF_PEARL));
          stacks.add(new ItemStack(ModItems.AIR_PEARL_ITEM));
          stacks.add(new ItemStack(ModItems.BOMB_PEARL_ITEM));
          stacks.add(new ItemStack(ModItems.EARTH_PEARL_ITEM));
          stacks.add(new ItemStack(Items.ENDER_PEARL));
          stacks.add(new ItemStack(Items.ENDER_EYE));
          stacks.add(new ItemStack(ModItems.FIRE_PEARL_ITEM));
          stacks.add(new ItemStack(ModItems.ICE_PEARL_ITEM));
          stacks.add(new ItemStack(ModItems.LIGHTNING_PEARL_ITEM));
          stacks.add(new ItemStack(ModItems.LIGHT_PEARL_ITEM));
          stacks.add(new ItemStack(ModItems.PLANT_PEARL_ITEM));
          stacks.add(new ItemStack(ModItems.VAC_PEARL_ITEM));
          stacks.add(new ItemStack(ModItems.WARP_PEARL_ITEM));
          stacks.add(new ItemStack(ModItems.WATER_PEARL_ITEM));
          stacks.add(new ItemStack(ModItems.WEB_PEARL_ITEM));
      }).build();


    // tells minecraft to initialise ModItems and ModBlocks
    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        ModPotions.init();
        ModEffects.registerEffects();

    }

}
