package com.arcanist.magick;

import com.arcanist.magick.registry.*;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Magick implements ModInitializer {

    public static final String MOD_ID = "magick";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        ModPotions.init();
        ModEffects.registerEffects();
        ModEntities.registerEntities();
        ModMiscRegistries.registerFuel();
        ModMiscRegistries.registerCustomTrades();
        ModMiscRegistries.registerLootTables();
        ModTabs.registerPearlsItemGroup();
        ModTabs.registerDustItemGroup();
    }

}
