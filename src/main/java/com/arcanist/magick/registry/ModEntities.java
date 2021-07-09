package com.arcanist.magick.registry;

import com.arcanist.magick.Magick;
import com.arcanist.magick.entity.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {

    public static final EntityType<FirePearlEntity> FirePearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "fire_pearl"),
            FabricEntityTypeBuilder.<FirePearlEntity>create(SpawnGroup.MISC, FirePearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // makes the size of the projectile
                    .trackRangeBlocks(4).trackedUpdateRate(10) // necessary for all thrown projectiles
                    .build() // This makes the whole thing work
    );

    public static final EntityType<WaterPearlEntity> WaterPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "water_pearl"),
            FabricEntityTypeBuilder.<WaterPearlEntity>create(SpawnGroup.MISC, WaterPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<LightningPearlEntity> LightningPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "lightning_pearl"),
            FabricEntityTypeBuilder.<LightningPearlEntity>create(SpawnGroup.MISC, LightningPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<AirPearlEntity> AirPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "air_pearl"),
            FabricEntityTypeBuilder.<AirPearlEntity>create(SpawnGroup.MISC, AirPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<BombPearlEntity> BombPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "bomb_pearl"),
            FabricEntityTypeBuilder.<BombPearlEntity>create(SpawnGroup.MISC, BombPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );


    public static final EntityType<EarthPearlEntity> EarthPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "earth_pearl"),
            FabricEntityTypeBuilder.<EarthPearlEntity>create(SpawnGroup.MISC, EarthPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<PlantPearlEntity> PlantPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "plant_pearl"),
            FabricEntityTypeBuilder.<PlantPearlEntity>create(SpawnGroup.MISC, PlantPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<WebPearlEntity> WebPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "web_pearl"),
            FabricEntityTypeBuilder.<WebPearlEntity>create(SpawnGroup.MISC, WebPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<IcePearlEntity> IcePearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "ice_pearl"),
            FabricEntityTypeBuilder.<IcePearlEntity>create(SpawnGroup.MISC, IcePearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<LightPearlEntity> LightPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "light_pearl"),
            FabricEntityTypeBuilder.<LightPearlEntity>create(SpawnGroup.MISC, LightPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<VacPearlEntity> VacPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "vac_pearl"),
            FabricEntityTypeBuilder.<VacPearlEntity>create(SpawnGroup.MISC, VacPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<WarpPearlEntity> WarpPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "warp_pearl"),
            FabricEntityTypeBuilder.<WarpPearlEntity>create(SpawnGroup.MISC, WarpPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );


}
