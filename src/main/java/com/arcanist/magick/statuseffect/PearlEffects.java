package com.arcanist.magick.statuseffect;

import com.arcanist.magick.registry.ModBlocks;
import com.arcanist.magick.registry.ModEffects;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.*;

public class PearlEffects {

    public static float power(World entityWorld, Entity owner){
        if (owner != null && (((LivingEntity) owner).hasStatusEffect(ModEffects.MANA) )){
            float amplifier = ((((LivingEntity) owner).getStatusEffect(ModEffects.MANA).getAmplifier())+2);
            if (entityWorld.getDimension().hasEnderDragonFight()){
                return amplifier*1.5F;
            }
            else return amplifier;
        }
        if (entityWorld.getDimension().hasEnderDragonFight()){
            return 1.5F;
        }
        else return 1;
    }

    public void airPearlEffect( Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        double radius = power(entityWorld, user)*3;
        for(Entity entities : entityWorld.getOtherEntities(null, new Box(entityX-radius, entityY-radius, entityZ-radius, entityX+radius, entityY+radius, entityZ+radius))) {
            if(entities instanceof LivingEntity) {
                ((LivingEntity) entities).takeKnockback(radius*0.2F,entityX - entities.getX(),entityZ - entities.getZ());
           }
            entity.playSound(SoundEvents.ENTITY_PHANTOM_FLAP, 4F, 2F); // makes the pearl play a sound when the effect happens
        }
    }


    public void bombPearlEffect( Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user){
       if (!entity.world.isClient) {
            entity.world.createExplosion(null, entityX, entityY, entityZ, power(entityWorld, user), false, Explosion.DestructionType.BREAK);
        }
    }

    public void earthPearlEffect( Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        double radius = power(entityWorld, user)+1;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x,entityY + y,entityZ + z);
                    if ((entityWorld.getDimension().isUltrawarm()) &&(entityWorld.getBlockState(blockPos).isAir() || entityWorld.getBlockState(blockPos) == Blocks.WATER.getDefaultState() || entityWorld.getBlockState(blockPos) == Blocks.LAVA.getDefaultState()) && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        switch (new Random().nextInt(4 - 1 + 1) + 1) {
                            case 1:
                                entityWorld.setBlockState(blockPos, Blocks.BASALT.getDefaultState());
                                break;
                            default:
                                entityWorld.setBlockState(blockPos, Blocks.NETHERRACK.getDefaultState());
                                break;
                        }
                    }
                    else if ((entityWorld.getDimension().hasEnderDragonFight()) &&(entityWorld.getBlockState(blockPos).isAir() || entityWorld.getBlockState(blockPos) == Blocks.WATER.getDefaultState() || entityWorld.getBlockState(blockPos) == Blocks.LAVA.getDefaultState()) && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        switch (new Random().nextInt(4 - 1 + 1) + 1) {
                            case 1:
                                entityWorld.setBlockState(blockPos, Blocks.OBSIDIAN.getDefaultState());
                                break;
                            default:
                                entityWorld.setBlockState(blockPos, Blocks.END_STONE.getDefaultState());
                                break;
                        }
                    }
                    else if ((entityWorld.getBlockState(blockPos).isAir() || entityWorld.getBlockState(blockPos) == Blocks.WATER.getDefaultState() || entityWorld.getBlockState(blockPos) == Blocks.LAVA.getDefaultState()) && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        switch (new Random().nextInt(4 - 1 + 1) + 1) {
                            case 1:
                                entityWorld.setBlockState(blockPos, Blocks.STONE.getDefaultState());
                                break;
                            default:
                                entityWorld.setBlockState(blockPos, Blocks.DIRT.getDefaultState());
                                break;
                        }
                    }
                }
            }
        }
        entity.playSound(SoundEvents.BLOCK_STONE_PLACE, 3F, 1F);
    }

    public void firePearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        double radius = power(entityWorld, user)+1;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x,entityY + y,entityZ + z);
                    if (entityWorld.getBlockState(blockPos).isAir() && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        entity.world.setBlockState(blockPos, AbstractFireBlock.getState(entity.world, blockPos));
                    }
                }
            }
        }
        entity.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 2F, 1F);
    }

    public void icePearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        double radius = power(entityWorld, user)+1;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x, entityY + y, entityZ + z);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        if (entityWorld.getBlockState(blockPos).isAir()) {
                            entityWorld.setBlockState(blockPos, Blocks.POWDER_SNOW.getDefaultState());
                        } else if (entityWorld.getBlockState(blockPos) == Blocks.WATER.getDefaultState()) {
                            entityWorld.setBlockState(blockPos, Blocks.ICE.getDefaultState());
                        } else if (entityWorld.getBlockState(blockPos) == Blocks.LAVA.getDefaultState()) {
                            entityWorld.setBlockState(blockPos, Blocks.OBSIDIAN.getDefaultState());
                        }
                    }
                }
            }
        }
        entity.playSound(SoundEvents.BLOCK_SNOW_BREAK, 2F, 1F);
    }

    public void lightningPearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user)  {
        BlockPos blockPos = new BlockPos(entityX, entityY, entityZ);
        if (((entity.world.isThundering()) || (power(entityWorld, user)>1.5)) && entity.world.isSkyVisible(blockPos)) {
            LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(entity.world);
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
            entity.world.spawnEntity(lightningEntity);
        }
        else {entity.playSound(SoundEvents.BLOCK_DISPENSER_FAIL, 1F, 3F);}
    }

    public void lightPearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        BlockPos blockPos = new BlockPos(entityX ,entityY ,entityZ );
        if (entityWorld.getBlockState(blockPos).isAir()) {
            entityWorld.setBlockState(blockPos, ModBlocks.LIGHT_ORB.getDefaultState());
            entity.playSound(SoundEvents.BLOCK_SHROOMLIGHT_STEP, 2F, 1F);
        }
    }

    public void plantPearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        double radius = power(entityWorld, user)*2;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x,entityY + y,entityZ + z);
                    if (entityWorld.getBlockState(blockPos).isAir() && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        switch (new Random().nextInt(6 - 1 + 1) + 1) {
                            case 1:
                                entityWorld.setBlockState(blockPos, Blocks.OAK_LEAVES.getDefaultState());
                                break;
                            case 2:
                                entityWorld.setBlockState(blockPos, Blocks.ACACIA_LEAVES.getDefaultState());
                                break;
                            case 3:
                                entityWorld.setBlockState(blockPos, Blocks.BIRCH_LEAVES.getDefaultState());
                                break;
                            case 4:
                                entityWorld.setBlockState(blockPos, Blocks.SPRUCE_LEAVES.getDefaultState());
                                break;
                            case 5:
                                entityWorld.setBlockState(blockPos, Blocks.JUNGLE_LEAVES.getDefaultState());
                                break;
                            default:
                                entityWorld.setBlockState(blockPos, Blocks.DARK_OAK_LEAVES.getDefaultState());
                                break;
                        }
                    }
                }
            }
        }
        entity.playSound(SoundEvents.ITEM_CROP_PLANT, 2F, 1F);
    }

    public void vacPearlEffect( Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user ) {
        double radius = power(entityWorld, user)*3;
        for(Entity entities : entityWorld.getOtherEntities(null, new Box(entityX-radius, entityY-radius, entityZ-radius, entityX+radius, entityY+radius, entityZ+radius))) {
            if(entities instanceof LivingEntity) {
                ((LivingEntity) entities).takeKnockback(radius*0.2F, entities.getX() - entityX, entities.getZ() - entityZ);
            }
            entity.playSound(SoundEvents.ENTITY_PHANTOM_FLAP, 4F, 2F);
        }
    }

     public void warpPearlEffect( Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user ) {
        float radius = power(entityWorld, user);
        for(Entity entities : entityWorld.getOtherEntities(null, new Box(entityX-radius, entityY-radius, entityZ-radius, entityX+radius, entityY+radius, entityZ+radius))) {
            if(entities instanceof LivingEntity) {
                entities.damage(DamageSource.MAGIC, radius*3);
                entity.playSound(SoundEvents.ENTITY_ENDERMAN_AMBIENT, 2F, 2F);
            }

        }
    }

    public void waterPearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        double radius = power(entityWorld, user);
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x,entityY + y,entityZ + z);
                    if (!entityWorld.getDimension().isUltrawarm() && entityWorld.getBlockState(blockPos).isAir() && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        entityWorld.setBlockState(blockPos, Blocks.WATER.getDefaultState());
                        entity.playSound(SoundEvents.BLOCK_WATER_AMBIENT, 2F, 1F);
                    }
                    else entity.playSound(SoundEvents.BLOCK_WATER_AMBIENT, 2F, 1F);
                }
            }
        }
    }

    public void webPearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        double radius = power(entityWorld, user)*2;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x,entityY + y,entityZ + z);
                    if (entityWorld.getBlockState(blockPos).isAir() && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        entityWorld.setBlockState(blockPos, ModBlocks.TEMP_WEB_BLOCK.getDefaultState());
                        entity.playSound(SoundEvents.ENTITY_SPIDER_AMBIENT, 1F, -1F);
                    }
                }
            }
        }
    }



    // other radius based effects below

    public void whiteGlowEffect(int amplifier, Entity entity, double entityX, double entityY, double entityZ, World entityWorld) {
        double radius = amplifier*3;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x,entityY + y,entityZ + z);
                    if (entityWorld.getBlockState(blockPos).isAir() && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        entityWorld.setBlockState(blockPos, ModBlocks.WHITE_BLOCK.getDefaultState());
                    }
                    else if (entityWorld.getBlockState(blockPos) == ModBlocks.RED_BLOCK.getDefaultState()) {
                        entityWorld.setBlockState(blockPos, ModBlocks.RED_WHITE_BLOCK.getDefaultState());
                    }
                }
            }
        }
    }

    public void redGlowEffect(int amplifier, Entity entity, double entityX, double entityY, double entityZ, World entityWorld) {
        double radius = amplifier*3;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x,entityY + y,entityZ + z);
                    if (entityWorld.getBlockState(blockPos).isAir()) {
                        entityWorld.setBlockState(blockPos, ModBlocks.RED_BLOCK.getDefaultState());
                    } else if (entityWorld.getBlockState(blockPos) == ModBlocks.WHITE_BLOCK.getDefaultState()) {
                        entityWorld.setBlockState(blockPos, ModBlocks.RED_WHITE_BLOCK.getDefaultState());
                    }
                }
            }
        }
    }

    public void oreSight(int amplifier, Entity entity, double entityX, double entityY, double entityZ, World entityWorld) {
        double radius = amplifier + 3;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x,entityY + y,entityZ + z);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        if (entityWorld.getBlockState(blockPos) == (Blocks.GOLD_ORE.getDefaultState()) || (entityWorld.getBlockState(blockPos) == (Blocks.NETHER_GOLD_ORE.getDefaultState())) || (entityWorld.getBlockState(blockPos) == (Blocks.DEEPSLATE_GOLD_ORE.getDefaultState()))){
                            entityWorld.playSound(null, blockPos, SoundEvents.BLOCK_NOTE_BLOCK_BELL, SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos) == Blocks.DIAMOND_ORE.getDefaultState() || (entityWorld.getBlockState(blockPos) == (Blocks.DEEPSLATE_DIAMOND_ORE.getDefaultState()))){
                            entityWorld.playSound(null, blockPos, SoundEvents.BLOCK_NOTE_BLOCK_HARP, SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos) == Blocks.EMERALD_ORE.getDefaultState() || (entityWorld.getBlockState(blockPos) == (Blocks.DEEPSLATE_EMERALD_ORE.getDefaultState()))){
                            entityWorld.playSound(null, blockPos, SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO, SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos) == Blocks.ANCIENT_DEBRIS.getDefaultState() ){
                            entityWorld.playSound(null, blockPos, SoundEvents.BLOCK_NOTE_BLOCK_BANJO, SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                    }
                }
            }
        }
    }

}
