package com.arcanist.magick.registry;


import com.arcanist.magick.Magick;
import com.arcanist.magick.item.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    //Pearls
    public static final Item FIRE_PEARL_ITEM = new FirePearlItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));
    public static final Item WATER_PEARL_ITEM = new WaterPearlItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));
    public static final Item LIGHTNING_PEARL_ITEM = new LightningPearlItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));
    public static final Item AIR_PEARL_ITEM = new AirPearlItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));
    public static final Item BOMB_PEARL_ITEM = new BombPearlItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));
    public static final Item EARTH_PEARL_ITEM = new EarthPearlItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));
    public static final Item PLANT_PEARL_ITEM = new PlantPearlItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));
    public static final Item WEB_PEARL_ITEM = new WebPearlItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));
    public static final Item ICE_PEARL_ITEM = new IcePearlItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));
    public static final Item LIGHT_PEARL_ITEM = new LightPearlItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));
    public static final Item VAC_PEARL_ITEM = new VacPearlItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));
    public static final Item WARP_PEARL_ITEM = new WarpPearlItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));

    //Dust items
    public static final Item EMERALD_DUST = new Item( new Item.Settings().group(ItemGroup.MISC));
    public static final Item DIAMOND_DUST = new Item( new Item.Settings().group(ItemGroup.MISC));
    public static final Item QUARTZ_DUST = new Item( new Item.Settings().group(ItemGroup.MISC));
    public static final Item OBSIDIAN_DUST = new Item( new Item.Settings().group(ItemGroup.MISC));
    public static final Item BLACKSTONE_DUST = new Item( new Item.Settings().group(ItemGroup.MISC));
    public static final Item NETHER_DUST = new Item( new Item.Settings().group(ItemGroup.MISC));
    public static final Item STONE_DUST = new Item( new Item.Settings().group(ItemGroup.MISC));
    public static final Item END_DUST = new Item( new Item.Settings().group(ItemGroup.MISC));
    public static final Item CALCITE_DUST = new Item( new Item.Settings().group(ItemGroup.MISC));

    public static final Item PIECE_OF_PEARL = new Item( new Item.Settings().group(ItemGroup.MISC));

    //Block items

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "calcite_dust"), CALCITE_DUST);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "emerald_dust"), EMERALD_DUST);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "diamond_dust"), DIAMOND_DUST);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "quartz_dust"), QUARTZ_DUST);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "obsidian_dust"), OBSIDIAN_DUST);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "blackstone_dust"), BLACKSTONE_DUST);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "nether_dust"), NETHER_DUST);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "stone_dust"), STONE_DUST);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "end_dust"), END_DUST);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "piece_of_pearl"), PIECE_OF_PEARL);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "air_pearl"), AIR_PEARL_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "bomb_pearl"), BOMB_PEARL_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "earth_pearl"), EARTH_PEARL_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "fire_pearl"), FIRE_PEARL_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "ice_pearl"), ICE_PEARL_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "lightning_pearl"), LIGHTNING_PEARL_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "light_pearl"), LIGHT_PEARL_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "plant_pearl"), PLANT_PEARL_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "vac_pearl"), VAC_PEARL_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "warp_pearl"), WARP_PEARL_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "water_pearl"), WATER_PEARL_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Magick.MOD_ID, "web_pearl"), WEB_PEARL_ITEM);
    }
}
