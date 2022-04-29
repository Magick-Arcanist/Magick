package com.arcanist.magick.statuseffect;

import com.arcanist.magick.registry.ModBlocks;
import com.arcanist.magick.registry.ModEffects;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Objects;
import java.util.Random;

public class RadiusEffects {

    public static float power(World entityWorld, Entity owner){
        if (owner != null && (((LivingEntity) owner).hasStatusEffect(ModEffects.MANA) )){
            float amplifier = ((Objects.requireNonNull(((LivingEntity) owner).getStatusEffect(ModEffects.MANA)).getAmplifier())+2);
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
        double radius = power(entityWorld, user)*4;
        DamageSource damageSource = DamageSource.magic(entity, user);
        for(Entity entities : entityWorld.getOtherEntities(null, new Box(entityX-radius, entityY-radius, entityZ-radius, entityX+radius, entityY+radius, entityZ+radius))) {
            if(entities instanceof LivingEntity) {
                ((LivingEntity) entities).takeKnockback(radius/4,entityX - entities.getX(),entityZ - entities.getZ());
                entities.damage(damageSource, 0);
           }
            entity.playSound(SoundEvents.ENTITY_PHANTOM_FLAP, 4F, 2F); // makes the pearl play a sound when the effect happens
        }
    }

    public void bombPearlEffect( Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user){
        DamageSource damageSource = DamageSource.explosion((LivingEntity) user);
        float radius = power(entityWorld, user);
        if (!entity.world.isClient) {
            entity.world.createExplosion(null, damageSource, null, entityX, entityY, entityZ, radius, false, Explosion.DestructionType.BREAK);
        }
    }

    public void earthPearlEffect( Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        double radius = power(entityWorld, user)+2;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x,entityY + y,entityZ + z);
                    if ((entityWorld.getBlockState(blockPos).isAir() || entityWorld.getBlockState(blockPos) == Blocks.WATER.getDefaultState() || entityWorld.getBlockState(blockPos) == Blocks.LAVA.getDefaultState()) && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                       if ((entityWorld.getDimension().isUltrawarm())){
                           if (new Random().nextInt(5 - 1 + 1) + 1 == 1) {
                               entityWorld.setBlockState(blockPos, Blocks.BASALT.getDefaultState());
                           } else {
                               entityWorld.setBlockState(blockPos, Blocks.NETHERRACK.getDefaultState());
                           }
                       }
                        if ((entityWorld.getDimension().hasEnderDragonFight())){
                            if (new Random().nextInt(5 - 1 + 1) + 1 == 1) {
                                entityWorld.setBlockState(blockPos, Blocks.OBSIDIAN.getDefaultState());
                            } else {
                                entityWorld.setBlockState(blockPos, Blocks.END_STONE.getDefaultState());
                            }
                        }
                        else if ((!entityWorld.getDimension().hasEnderDragonFight()) || !(entityWorld.getDimension().isUltrawarm())){
                            if (new Random().nextInt(5 - 1 + 1) + 1 == 1) {
                                entityWorld.setBlockState(blockPos, Blocks.STONE.getDefaultState());
                            } else {
                                entityWorld.setBlockState(blockPos, Blocks.DIRT.getDefaultState());
                            }
                        }
                    }
                }
            }
        }
        entity.playSound(SoundEvents.BLOCK_STONE_PLACE, 3F, 1F);
    }

    public void firePearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        double radius = power(entityWorld, user)*2;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x,entityY + y,entityZ + z);
                    if ((entityWorld.getBlockState(blockPos).isAir()) && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        entity.world.setBlockState(blockPos, AbstractFireBlock.getState(entity.world, blockPos));
                    }
                }
            }
        }
        double radius2 = power(entityWorld, user)*3;
        for (int x = (int) -radius2 - 1; x <= radius2; x++) {
            for (int y = (int) -radius2 - 1; y <= radius2; y++) {
                for (int z = (int) -radius2 - 1; z <= radius2; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x,entityY + y,entityZ + z);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius2 && entityWorld.getBlockState(blockPos) == Blocks.ICE.getDefaultState() || entityWorld.getBlockState(blockPos) == Blocks.PACKED_ICE.getDefaultState() || entityWorld.getBlockState(blockPos) == Blocks.FROSTED_ICE.getDefaultState()) {
                        entityWorld.setBlockState(blockPos, Blocks.WATER.getDefaultState());
                    }
                    else if(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius2 && entityWorld.getBlockState(blockPos) == Blocks.WATER.getDefaultState() || entityWorld.getBlockState(blockPos) == Blocks.POWDER_SNOW.getDefaultState() || entityWorld.getBlockState(blockPos) == Blocks.SNOW.getDefaultState() || entityWorld.getBlockState(blockPos) == Blocks.SNOW_BLOCK.getDefaultState()) {
                        entityWorld.setBlockState(blockPos, Blocks.AIR.getDefaultState());
                    }
                }
            }
        }
        entity.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 2F, 1F);
    }

    public void icePearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        double radius = power(entityWorld, user);
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x, entityY + y, entityZ + z);
                    if ( Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        if (entityWorld.getBlockState(blockPos).isAir()) {
                            entityWorld.setBlockState(blockPos, Blocks.POWDER_SNOW.getDefaultState());
                        }
                    }
                }
            }
        }
        double radius2 = power(entityWorld, user)+2;
        for (int x = (int) -radius2 - 1; x <= radius2; x++) {
            for (int y = (int) -radius2 - 1; y <= radius2; y++) {
                for (int z = (int) -radius2 - 1; z <= radius2; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x, entityY + y, entityZ + z);
                    if ( Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius2) {
                        if (entityWorld.getBlockState(blockPos)== Blocks.WATER.getDefaultState()) {
                            entityWorld.setBlockState(blockPos, Blocks.ICE.getDefaultState());
                        }else if (entityWorld.getBlockState(blockPos) == Blocks.LAVA.getDefaultState()) {
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
            assert lightningEntity != null;
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

    /*
    public void plantPearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        double radius = power(entityWorld, user)*3;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x,entityY + y,entityZ + z);
                    if (entityWorld.getBlockState(blockPos).isAir() && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        switch (new Random().nextInt(6 - 1 + 1) + 1) {
                            case 1 -> entityWorld.setBlockState(blockPos, Blocks.OAK_LEAVES.getDefaultState());
                            case 2 -> entityWorld.setBlockState(blockPos, Blocks.ACACIA_LEAVES.getDefaultState());
                            case 3 -> entityWorld.setBlockState(blockPos, Blocks.BIRCH_LEAVES.getDefaultState());
                            case 4 -> entityWorld.setBlockState(blockPos, Blocks.SPRUCE_LEAVES.getDefaultState());
                            case 5 -> entityWorld.setBlockState(blockPos, Blocks.JUNGLE_LEAVES.getDefaultState());
                            default -> entityWorld.setBlockState(blockPos, Blocks.DARK_OAK_LEAVES.getDefaultState());
                        }
                    }
                }
            }
        }
        entity.playSound(SoundEvents.ITEM_CROP_PLANT, 2F, 1F);
    }
     */

    public void plantPearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        double radius = power(entityWorld, user)*3;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x,entityY + y,entityZ + z);
                    if ((entityWorld.getBlockState(blockPos).getBlock() instanceof Fertilizable) && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        BlockState blockState = entityWorld.getBlockState(blockPos);
                        Fertilizable fertilizable = (Fertilizable)blockState.getBlock();
                        if (fertilizable.isFertilizable(entityWorld, blockPos, blockState, entityWorld.isClient)) {
                                if (fertilizable.canGrow(entityWorld, entityWorld.random, blockPos, blockState)) {
                                    fertilizable.grow((ServerWorld)entityWorld, entityWorld.random, blockPos, blockState);
                                }
                            entityWorld.syncWorldEvent(1505, blockPos, 0);
                        }

                    }
                }
            }
        }
        entity.playSound(SoundEvents.ITEM_CROP_PLANT, 2F, 1F);
    }

    public void vacPearlEffect( Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user ) {
        double radius = power(entityWorld, user)*4;
        DamageSource damageSource = DamageSource.magic(entity, user);
        for(Entity entities : entityWorld.getOtherEntities(null, new Box(entityX-radius, entityY-radius, entityZ-radius, entityX+radius, entityY+radius, entityZ+radius))) {
            if(entities instanceof LivingEntity) {
                ((LivingEntity) entities).takeKnockback(radius/4, entities.getX() - entityX, entities.getZ() - entityZ);
                entities.damage(damageSource, 0);
            }
            entity.playSound(SoundEvents.ENTITY_PHANTOM_FLAP, 4F, 2F);
        }
    }

     public void warpPearlEffect( Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user ) {
        float damage = power(entityWorld, user)*5;
         DamageSource damageSource = DamageSource.magic(entity, user);
        for(Entity entities : entityWorld.getOtherEntities(null, new Box(entityX-2, entityY-2, entityZ-2, entityX+2, entityY+2, entityZ+2))) {
            if(entities instanceof LivingEntity) {
                entities.damage(damageSource, damage);
            }
            entity.playSound(SoundEvents.ENTITY_ENDERMAN_AMBIENT, 2F, 2F);
        }
        redGlowEffect(1,entityX,entityY,entityZ,entityWorld);
    }

    public void waterPearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        double radius = power(entityWorld, user);
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x,entityY + y,entityZ + z);
                    if (!entityWorld.getDimension().isUltrawarm() && entityWorld.getBlockState(blockPos).isAir() && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        entityWorld.setBlockState(blockPos, Blocks.WATER.getDefaultState());
                    }
                    entity.playSound(SoundEvents.BLOCK_WATER_AMBIENT, 2F, 1F);
                }
            }
        }
    }

    public void webPearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        double radius = power(entityWorld, user)*3;
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

// enchantment and status effect radius'

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

    public void redGlowEffect(int amplifier, double entityX, double entityY, double entityZ, World entityWorld) {
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

    public void oreSight(int amplifier, double entityX, double entityY, double entityZ, World entityWorld) {
        double radius = amplifier + 3;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(entityX + x,entityY + y,entityZ + z);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        if (entityWorld.getBlockState(blockPos) == (Blocks.COAL_ORE.getDefaultState()) || (entityWorld.getBlockState(blockPos) == (Blocks.DEEPSLATE_COAL_ORE.getDefaultState()))){
                            entityWorld.playSound(null, blockPos, SoundEvents.BLOCK_NOTE_BLOCK_BASS, SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos) == (Blocks.COPPER_ORE.getDefaultState()) || (entityWorld.getBlockState(blockPos) == (Blocks.DEEPSLATE_COPPER_ORE.getDefaultState()))){
                            entityWorld.playSound(null, blockPos, SoundEvents.BLOCK_NOTE_BLOCK_CHIME, SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos) == (Blocks.LAPIS_ORE.getDefaultState()) || (entityWorld.getBlockState(blockPos) == (Blocks.DEEPSLATE_LAPIS_ORE.getDefaultState()))){
                            entityWorld.playSound(null, blockPos, SoundEvents.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos) == (Blocks.IRON_ORE.getDefaultState()) || (entityWorld.getBlockState(blockPos) == (Blocks.DEEPSLATE_IRON_ORE.getDefaultState()))){
                            entityWorld.playSound(null, blockPos, SoundEvents.BLOCK_NOTE_BLOCK_BIT, SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos) == (Blocks.REDSTONE_ORE.getDefaultState()) || (entityWorld.getBlockState(blockPos) == (Blocks.DEEPSLATE_REDSTONE_ORE.getDefaultState()))){
                            entityWorld.playSound(null, blockPos, SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE, SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos) == Blocks.DIAMOND_ORE.getDefaultState() || (entityWorld.getBlockState(blockPos) == (Blocks.DEEPSLATE_DIAMOND_ORE.getDefaultState()))){
                            entityWorld.playSound(null, blockPos, SoundEvents.BLOCK_NOTE_BLOCK_HARP, SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos) == (Blocks.GOLD_ORE.getDefaultState()) || (entityWorld.getBlockState(blockPos) == (Blocks.NETHER_GOLD_ORE.getDefaultState())) || (entityWorld.getBlockState(blockPos) == (Blocks.DEEPSLATE_GOLD_ORE.getDefaultState()))){
                            entityWorld.playSound(null, blockPos, SoundEvents.BLOCK_NOTE_BLOCK_BELL, SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos) == Blocks.EMERALD_ORE.getDefaultState() || (entityWorld.getBlockState(blockPos) == (Blocks.DEEPSLATE_EMERALD_ORE.getDefaultState()))){
                            entityWorld.playSound(null, blockPos, SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO, SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos) == Blocks.ANCIENT_DEBRIS.getDefaultState() ){
                            entityWorld.playSound(null, blockPos, SoundEvents.BLOCK_NOTE_BLOCK_BANJO, SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos) == Blocks.NETHER_QUARTZ_ORE.getDefaultState() ){
                            entityWorld.playSound(null, blockPos, SoundEvents.BLOCK_NOTE_BLOCK_SNARE, SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                    }
                }
            }
        }
    }

    public void fearEffect(int amplifier, LivingEntity user, double entityX, double entityY, double entityZ, World entityWorld) {
        double radius = amplifier + 3;
        DamageSource damageSource = DamageSource.mob(user);
        for(Entity entities : entityWorld.getOtherEntities(null, new Box(entityX-radius, entityY-radius, entityZ-radius, entityX+radius, entityY+radius, entityZ+radius))) {
            if(entities instanceof LivingEntity) {
                ((LivingEntity) entities).setAttacker(user);
            }
            if(entities instanceof VillagerEntity) {
                entities.damage(damageSource, 0);
            }
        }
    }

    public void loveEffect(int amplifier, LivingEntity user, double entityX, double entityY, double entityZ, World entityWorld) {
        double radius = amplifier + 3;
        PlayerEntity player = ((PlayerEntity) user);
        for(Entity entities : entityWorld.getOtherEntities(null, new Box(entityX-radius, entityY-radius, entityZ-radius, entityX+radius, entityY+radius, entityZ+radius))) {
            if(entities instanceof TameableEntity && !((TameableEntity) entities).isTamed()) {
                ((TameableEntity) entities).setOwner(player);
            }
        }
    }
}