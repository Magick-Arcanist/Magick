package com.arcanist.magick.registry;

import com.arcanist.magick.Magick;
import com.arcanist.magick.entity.*;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {

    public static final EntityType<AirPearlEntity> AIR_PEARL_ENTITY;
    public static final EntityType<BombPearlEntity> BOMB_PEARL_ENTITY;
    public static final EntityType<EarthPearlEntity> EARTH_PEARL_ENTITY;
    public static final EntityType<FirePearlEntity> FIRE_PEARL_ENTITY;
    public static final EntityType<IcePearlEntity> ICE_PEARL_ENTITY;
    public static final EntityType<LightningPearlEntity> LIGHTNING_PEARL_ENTITY;
    public static final EntityType<LightPearlEntity> LIGHT_PEARL_ENTITY;
    public static final EntityType<PlantPearlEntity> PLANT_PEARL_ENTITY;
    public static final EntityType<VacPearlEntity> VAC_PEARL_ENTITY;
    public static final EntityType<WarpPearlEntity> WARP_PEARL_ENTITY;
    public static final EntityType<WaterPearlEntity> WATER_PEARL_ENTITY;
    public static final EntityType<WebPearlEntity> WEB_PEARL_ENTITY;


    static {
        AIR_PEARL_ENTITY = FabricEntityTypeBuilder.<AirPearlEntity>create(SpawnGroup.MISC, AirPearlEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();
        BOMB_PEARL_ENTITY = FabricEntityTypeBuilder.<BombPearlEntity>create(SpawnGroup.MISC, BombPearlEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();
        EARTH_PEARL_ENTITY = FabricEntityTypeBuilder.<EarthPearlEntity>create(SpawnGroup.MISC, EarthPearlEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();
        FIRE_PEARL_ENTITY = FabricEntityTypeBuilder.<FirePearlEntity>create(SpawnGroup.MISC, FirePearlEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();
        ICE_PEARL_ENTITY = FabricEntityTypeBuilder.<IcePearlEntity>create(SpawnGroup.MISC, IcePearlEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();
        LIGHTNING_PEARL_ENTITY = FabricEntityTypeBuilder.<LightningPearlEntity>create(SpawnGroup.MISC, LightningPearlEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();
        LIGHT_PEARL_ENTITY = FabricEntityTypeBuilder.<LightPearlEntity>create(SpawnGroup.MISC, LightPearlEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();
        PLANT_PEARL_ENTITY = FabricEntityTypeBuilder.<PlantPearlEntity>create(SpawnGroup.MISC, PlantPearlEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();
        VAC_PEARL_ENTITY = FabricEntityTypeBuilder.<VacPearlEntity>create(SpawnGroup.MISC, VacPearlEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();
        WARP_PEARL_ENTITY = FabricEntityTypeBuilder.<WarpPearlEntity>create(SpawnGroup.MISC, WarpPearlEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();
        WATER_PEARL_ENTITY = FabricEntityTypeBuilder.<WaterPearlEntity>create(SpawnGroup.MISC, WaterPearlEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();
        WEB_PEARL_ENTITY = FabricEntityTypeBuilder.<WebPearlEntity>create(SpawnGroup.MISC, WebPearlEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackable(64, 10).build();
    }

    public static void registerEntities() {
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Magick.MOD_ID, "air_pearl"), AIR_PEARL_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Magick.MOD_ID, "bomb_pearl"), BOMB_PEARL_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Magick.MOD_ID, "earth_pearl"), EARTH_PEARL_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Magick.MOD_ID, "fire_pearl"), FIRE_PEARL_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Magick.MOD_ID, "ice_pearl"), ICE_PEARL_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Magick.MOD_ID, "lightning_pearl"), LIGHTNING_PEARL_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Magick.MOD_ID, "light_pearl"), LIGHT_PEARL_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Magick.MOD_ID, "plant_pearl"), PLANT_PEARL_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Magick.MOD_ID, "vac_pearl"), VAC_PEARL_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Magick.MOD_ID, "warp_pearl"), WARP_PEARL_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Magick.MOD_ID, "water_pearl"), WATER_PEARL_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Magick.MOD_ID, "web_pearl"), WEB_PEARL_ENTITY);
    }

    public static void renderEntities() {
        EntityRendererRegistry.register(ModEntities.AIR_PEARL_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.EARTH_PEARL_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BOMB_PEARL_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.FIRE_PEARL_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.ICE_PEARL_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LIGHTNING_PEARL_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LIGHT_PEARL_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.PLANT_PEARL_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.VAC_PEARL_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.WARP_PEARL_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.WATER_PEARL_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.WEB_PEARL_ENTITY, FlyingItemEntityRenderer::new);
    }

/*
    public static final EntityType<WaterPearlEntity> WaterPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "water_pearl"),
            FabricEntityTypeBuilder.<WaterPearlEntity>create(SpawnGroup.MISC, WaterPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(64).trackedUpdateRate(10)
                    .build()
    );
    public static final EntityType<LightningPearlEntity> LightningPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "lightning_pearl"),
            FabricEntityTypeBuilder.<LightningPearlEntity>create(SpawnGroup.MISC, LightningPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(64).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<PlantPearlEntity> PlantPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "plant_pearl"),
            FabricEntityTypeBuilder.<PlantPearlEntity>create(SpawnGroup.MISC, PlantPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(64).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<WebPearlEntity> WebPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "web_pearl"),
            FabricEntityTypeBuilder.<WebPearlEntity>create(SpawnGroup.MISC, WebPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(64).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<IcePearlEntity> IcePearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "ice_pearl"),
            FabricEntityTypeBuilder.<IcePearlEntity>create(SpawnGroup.MISC, IcePearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(64).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<LightPearlEntity> LightPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "light_pearl"),
            FabricEntityTypeBuilder.<LightPearlEntity>create(SpawnGroup.MISC, LightPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(64).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<VacPearlEntity> VacPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "vac_pearl"),
            FabricEntityTypeBuilder.<VacPearlEntity>create(SpawnGroup.MISC, VacPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(64).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<WarpPearlEntity> WarpPearlEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Magick.MOD_ID, "warp_pearl"),
            FabricEntityTypeBuilder.<WarpPearlEntity>create(SpawnGroup.MISC, WarpPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(64).trackedUpdateRate(10)
                    .build()
    );

 */

}
