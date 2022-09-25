package com.arcanist.magick.registry;

import com.arcanist.magick.item.WarpPearlItem;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;


public class ModMiscRegistries {


    public static void registerFuel() {
        FuelRegistry.INSTANCE.add(ModItems.NETHER_DUST, 20);
        FuelRegistry.INSTANCE.add(ModItems.FIRE_PEARL_ITEM, 20000);
    }

    public static void registerCustomTrades() {

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC,1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(ModItems.PIECE_OF_PEARL, 4), 16,2,0.02f));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON,1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(ModItems.OBSIDIAN_DUST, 4), 8,2,0.02f));
        });

    }

    private static final Identifier SHIPWRECK_TREASURE = new Identifier("minecraft", "chests/shipwreck_treasure");
    private static final Identifier ABANDONED_MINESHAFT = new Identifier("minecraft", "chests/abandoned_mineshaft");
    private static final Identifier PILLAGER_OUTPOST = new Identifier("minecraft", "chests/pillager_outpost");
    private static final Identifier RUINED_PORTAL = new Identifier("minecraft", "chests/ruined_portal");
    private static final Identifier BASTION_OTHER = new Identifier("minecraft", "chests/bastion_other");
    private static final Identifier DESERT_PYRAMID = new Identifier("minecraft", "chests/desert_pyramid");


    public static void registerLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (SHIPWRECK_TREASURE.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5F))
                        .with(ItemEntry.builder(ModItems.WATER_PEARL_ITEM))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)).build());
                tableBuilder.pool(poolBuilder);
            }
            else
            if (ABANDONED_MINESHAFT.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5F))
                        .with(ItemEntry.builder(ModItems.LIGHT_PEARL_ITEM))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f)).build());
                tableBuilder.pool(poolBuilder);
            }
            else
            if (PILLAGER_OUTPOST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5F))
                        .with(ItemEntry.builder(ModItems.WEB_PEARL_ITEM))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 6.0f)).build());
                tableBuilder.pool(poolBuilder);

            }
            else
            if (RUINED_PORTAL.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5F))
                        .with(ItemEntry.builder(ModItems.FIRE_PEARL_ITEM))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 6.0f)).build());
                tableBuilder.pool(poolBuilder);
            }
            else
            if (BASTION_OTHER.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5F))
                        .with(ItemEntry.builder(ModItems.BOMB_PEARL_ITEM))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 6.0f)).build());
                tableBuilder.pool(poolBuilder);
            }
            else
            if (DESERT_PYRAMID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5F))
                        .with(ItemEntry.builder(ModItems.EARTH_PEARL_ITEM))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 6.0f)).build());
                tableBuilder.pool(poolBuilder);
            }


        });
    }
}
