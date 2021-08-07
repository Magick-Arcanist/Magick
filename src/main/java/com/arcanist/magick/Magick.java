package com.arcanist.magick;

import com.arcanist.magick.registry.ModBlocks;
import com.arcanist.magick.registry.ModEffects;
import com.arcanist.magick.registry.ModItems;
import com.arcanist.magick.registry.ModPotions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;


public class Magick implements ModInitializer {

    public static final String MOD_ID = "magick";

    public static final Identifier PacketID = new Identifier(Magick.MOD_ID, "spawn_packet");

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

    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        ModPotions.init();
        ModEffects.registerEffects();
    }

}
