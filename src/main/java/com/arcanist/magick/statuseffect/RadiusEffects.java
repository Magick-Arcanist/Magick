package com.arcanist.magick.statuseffect;

import com.arcanist.magick.registry.ModBlocks;
import com.arcanist.magick.registry.ModEffects;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;

import java.util.Objects;
import java.util.Random;

import static net.minecraft.block.SweetBerryBushBlock.AGE;
import static net.minecraft.registry.tag.BlockTags.*;
import static net.minecraft.world.dimension.DimensionTypes.*;

public class RadiusEffects {


    public static float power(World entityWorld, Entity owner){
        if (owner != null && (((LivingEntity) owner).hasStatusEffect(ModEffects.MANA) )){
            float amplifier = ((Objects.requireNonNull(((LivingEntity) owner).getStatusEffect(ModEffects.MANA)).getAmplifier())+2);
            if (entityWorld.getDimensionKey().equals(THE_END)){
                return amplifier*2;
            }
            else return amplifier;
        }
        if (entityWorld.getDimensionKey().equals(THE_END)){
            assert owner != null;
            return 2;
        }
        else return 1;
    }

    public void airPearlEffect( Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        float radius = power(entityWorld, user)*2;

        DamageSource damageSource = user.getDamageSources().magic();

        //DamageSource damageSource = DamageSource. magic(entity, user);
        for(Entity entities : entityWorld.getOtherEntities(user, new Box(entityX-radius, entityY-radius, entityZ-radius, entityX+radius, entityY+radius, entityZ+radius))) {
            if(entities instanceof LivingEntity) {
                ((LivingEntity) entities).takeKnockback(radius/2,entityX - entities.getX(),entityZ - entities.getZ());
                entities.damage(damageSource, 0);
           }
            entity.playSound(SoundEvents.ENTITY_PHANTOM_FLAP, 4F, 2F);
        }
    }

    public void bombPearlEffect( Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user){
        float radius = power(entityWorld, user)+1F;
        if (!entity.world.isClient) {
            entity.world.createExplosion(user, entityX, entityY, entityZ, radius, false, World.ExplosionSourceType.MOB);
        }
    }

    public void earthPearlEffect( Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        float radius = power(entityWorld, user)+2;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos((int) entityX + x,(int)entityY + y,(int)entityZ + z);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius){
                    if (entityWorld.getBlockState(blockPos).isAir()||entityWorld.getFluidState(blockPos).isOf(Fluids.WATER)||entityWorld.getFluidState(blockPos).isOf(Fluids.LAVA)||entityWorld.getFluidState(blockPos).isOf(Fluids.FLOWING_WATER)||entityWorld.getFluidState(blockPos).isOf(Fluids.FLOWING_LAVA)||entityWorld.getBlockState(blockPos).isIn(REPLACEABLE_PLANTS)||entityWorld.getBlockState(blockPos).isOf(Blocks.SNOW)){
                        if ((entityWorld.getDimensionKey().equals(THE_NETHER))){
                           if (new Random().nextInt(5 - 1 + 1) + 1 == 1) {
                               entityWorld.setBlockState(blockPos, Blocks.BASALT.getDefaultState());
                           } else {
                               entityWorld.setBlockState(blockPos, Blocks.NETHERRACK.getDefaultState());
                           }
                       }
                        if ((entityWorld.getDimensionKey().equals(THE_END))){
                            if (new Random().nextInt(5 - 1 + 1) + 1 == 1) {
                                entityWorld.setBlockState(blockPos, Blocks.OBSIDIAN.getDefaultState());
                            } else {
                                entityWorld.setBlockState(blockPos, Blocks.END_STONE.getDefaultState());
                            }
                        }
                        else if (!(((entityWorld.getDimensionKey().equals(THE_END))) || (entityWorld.getDimensionKey().equals(THE_NETHER)))){
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
        }
        entity.playSound(SoundEvents.BLOCK_STONE_PLACE, 3F, 1F);
    }

    public void firePearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        float radius = power(entityWorld, user)*2;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos((int) entityX + x,(int) entityY + y,(int) entityZ + z);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius){
                        if (entityWorld.getBlockState(blockPos).isAir()||entityWorld.getBlockState(blockPos).isIn(REPLACEABLE_PLANTS)){
                            entity.world.setBlockState(blockPos, AbstractFireBlock.getState(entity.world, blockPos));
                        }
                    }
                }
            }
        }
        float radius2 = radius+2;
        for (int x = (int) -radius2 - 1; x <= radius2; x++) {
            for (int y = (int) -radius2 - 1; y <= radius2; y++) {
                for (int z = (int) -radius2 - 1; z <= radius2; z++) {
                    BlockPos blockPos = new BlockPos((int) entityX + x, (int) entityY + y, (int) entityZ + z);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius2) {
                        if (entityWorld.getBlockState(blockPos).isOf(Blocks.ICE)||entityWorld.getBlockState(blockPos).isOf(Blocks.PACKED_ICE)||entityWorld.getBlockState(blockPos).isOf(Blocks.FROSTED_ICE)) {
                            entityWorld.setBlockState(blockPos, Blocks.WATER.getDefaultState());
                        }
                        if (entityWorld.getBlockState(blockPos).isIn(SNOW)||entityWorld.getFluidState(blockPos).isOf(Fluids.FLOWING_WATER)||entityWorld.getFluidState(blockPos).isOf(Fluids.WATER)){
                            entityWorld.setBlockState(blockPos, Blocks.AIR.getDefaultState());
                        }
                    }
                }
            }
        }
        entity.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 2F, 1F);
    }

    public void icePearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        float radius = power(entityWorld, user);
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos((int) entityX + x, (int) entityY + y, (int) entityZ + z);
                    if ( Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        if (entityWorld.getBlockState(blockPos).isAir()||entityWorld.getBlockState(blockPos).isIn(REPLACEABLE_PLANTS)){
                            entityWorld.setBlockState(blockPos, Blocks.POWDER_SNOW.getDefaultState());
                        }
                    }
                }
            }
        }
        float radius2 = radius*2;
        for (int x = (int) -radius2 - 1; x <= radius2; x++) {
            for (int y = (int) -radius2 - 1; y <= radius2; y++) {
                for (int z = (int) -radius2 - 1; z <= radius2; z++) {
                    BlockPos blockPos = new BlockPos((int) entityX + x, (int) entityY + y, (int) entityZ + z);
                    if ( Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius2) {
                        if (entityWorld.getFluidState(blockPos).isOf(Fluids.WATER)) {
                            entityWorld.setBlockState(blockPos, Blocks.ICE.getDefaultState());
                        }
                        if (entityWorld.getFluidState(blockPos).isOf(Fluids.FLOWING_WATER)||entityWorld.getBlockState(blockPos).isOf(Blocks.SNOW)){
                            entityWorld.setBlockState(blockPos, Blocks.SNOW_BLOCK.getDefaultState());
                        }
                        if (entityWorld.getBlockState(blockPos).isIn(FIRE)) {
                            entityWorld.setBlockState(blockPos, Blocks.AIR.getDefaultState());
                        }
                        if (entityWorld.getFluidState(blockPos).isOf(Fluids.FLOWING_LAVA)) {
                            entityWorld.setBlockState(blockPos, Blocks.COBBLESTONE.getDefaultState());
                        }
                        else if (entityWorld.getBlockState(blockPos).isOf(Blocks.LAVA)) {
                            entityWorld.setBlockState(blockPos, Blocks.OBSIDIAN.getDefaultState());
                        }
                    }
                }
            }
        }
        entity.playSound(SoundEvents.BLOCK_SNOW_BREAK, 2F, 1F);
    }

    public void lightningPearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user)  {
        BlockPos blockPos = new BlockPos((int) entityX , (int) entityY , (int) entityZ );
        float power = power(entityWorld, user);
        if ((entity.world.isSkyVisible(blockPos) && ((entity.world.isThundering() || entity.world.isRaining() || power>1))) || ((entity.world.isThundering() || entity.world.isRaining()) && power>1) || (power>2)) {
            LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(entity.world);
            assert lightningEntity != null;
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
            entity.world.spawnEntity(lightningEntity);
            entity.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 1F, 1F);
        }
        else {
            entity.playSound(SoundEvents.BLOCK_DISPENSER_FAIL, 1F, 3F);
        }
    }

    public void lightPearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        BlockPos blockPos = new BlockPos((int) entityX , (int) entityY , (int) entityZ );
        if (entityWorld.getBlockState(blockPos).isAir()||entityWorld.getBlockState(blockPos).isIn(REPLACEABLE_PLANTS)||entityWorld.getFluidState(blockPos).isOf(Fluids.FLOWING_WATER)||entityWorld.getFluidState(blockPos).isOf(Fluids.WATER)){
            entityWorld.setBlockState(blockPos, ModBlocks.LIGHT_ORB.getDefaultState());
        }

        entity.playSound(SoundEvents.BLOCK_SHROOMLIGHT_STEP, 2F, 1F);
    }

    public void plantPearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        float radius = power(entityWorld, user)*4;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos((int) entityX + x, (int) entityY + y, (int) entityZ + z);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius){
                        if ((entityWorld.getBlockState(blockPos).getBlock() instanceof Fertilizable)) {
                            BlockState blockState = entityWorld.getBlockState(blockPos);
                            Fertilizable fertilizable = (Fertilizable) blockState.getBlock();
                            Block block =  blockState.getBlock();
                            if (fertilizable.isFertilizable(entityWorld, blockPos, blockState, entityWorld.isClient)) {
                                if (fertilizable.canGrow(entityWorld, entityWorld.random, blockPos, blockState)) {
                                    fertilizable.grow((ServerWorld) entityWorld, entityWorld.random, blockPos, blockState);
                                    }
                                entityWorld.syncWorldEvent(1505, blockPos, 0);
                            }
                            if (fertilizable instanceof CropBlock && ((CropBlock) fertilizable).isMature(blockState)){
                                CropBlock.dropStacks(blockState, entityWorld, blockPos, null, user, ((CropBlock) fertilizable).getPickStack(entityWorld, blockPos, blockState));
                                entityWorld.setBlockState(blockPos, ((CropBlock) fertilizable).getDefaultState(), Block.NOTIFY_LISTENERS);
                            }
                            if (block instanceof CaveVines && CaveVines.hasBerries(blockState)){
                                Block.dropStack(entityWorld, blockPos, new ItemStack(Items.GLOW_BERRIES, 1));
                            }
                            if (block instanceof SweetBerryBushBlock && blockState.get(AGE)>1 ){
                                    Block.dropStack(entityWorld, blockPos, new ItemStack(Items.SWEET_BERRIES, 1));
                            }
                        }
                    }
                }
            }
        }
        entity.playSound(SoundEvents.ITEM_CROP_PLANT, 2F, 1F);
    }

    public void vacPearlEffect( Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user ) {
        float radius = power(entityWorld, user)*2;
        DamageSource damageSource = user.getDamageSources().magic();
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos((int) entityX + x, (int) entityY + y, (int) entityZ + z);
                    if (entityWorld.getBlockState(blockPos).isIn(FIRE) && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        entityWorld.setBlockState(blockPos, Blocks.AIR.getDefaultState());
                    }
                }
            }
        }
        for(Entity entities : entityWorld.getOtherEntities(user, new Box(entityX-radius, entityY-radius, entityZ-radius, entityX+radius, entityY+radius, entityZ+radius))) {
            if(entities instanceof LivingEntity) {
                ((LivingEntity) entities).takeKnockback(radius/2, entities.getX() - entityX, entities.getZ() - entityZ);
                entities.damage(damageSource, 0);
                entities.extinguish();
            }
            entity.playSound(SoundEvents.ENTITY_PHANTOM_FLAP, 4F, 2F);
        }
    }

     public void warpPearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user ) {
        float damage = power(entityWorld, user)*5;
        float radius = 4;
         DamageSource damageSource = user.getDamageSources().magic();
        for(Entity entities : entityWorld.getOtherEntities(user, new Box(entityX-3, entityY-3, entityZ-3, entityX+3, entityY+3, entityZ+3))) {
            if(entities instanceof LivingEntity) {
                entities.damage(damageSource, damage);
            }
            redGlowEffect(1,entity, entityX, entityY, entityZ, entityWorld);
            entity.playSound(SoundEvents.ENTITY_ENDERMAN_AMBIENT, 1F, 2F);
        }
         for (int x = (int) -radius - 1; x <= radius; x++) {
             for (int y = (int) -radius - 1; y <= radius; y++) {
                 for (int z = (int) -radius - 1; z <= radius; z++) {
                     BlockPos blockPos = new BlockPos((int) entityX + x, (int) entityY + y, (int) entityZ + z);
                     if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                         if(entityWorld.getBlockState(blockPos).isAir())entityWorld.setBlockState(blockPos, ModBlocks.WARPING_BLOCK.getDefaultState());
                         if(entityWorld.getBlockState(blockPos).isOf(Blocks.NETHER_WART_BLOCK))entityWorld.setBlockState(blockPos, Blocks.WARPED_WART_BLOCK.getDefaultState());
                         if(entityWorld.getBlockState(blockPos).isOf(Blocks.CRIMSON_NYLIUM))entityWorld.setBlockState(blockPos, Blocks.WARPED_NYLIUM.getDefaultState());
                         if(entityWorld.getBlockState(blockPos).isOf(Blocks.CRIMSON_STEM))entityWorld.setBlockState(blockPos, Blocks.WARPED_STEM.getDefaultState());
                         if(entityWorld.getBlockState(blockPos).isOf(Blocks.CRIMSON_ROOTS))entityWorld.setBlockState(blockPos, Blocks.WARPED_ROOTS.getDefaultState());
                         if(entityWorld.getBlockState(blockPos).isOf(Blocks.CRIMSON_NYLIUM))entityWorld.setBlockState(blockPos, Blocks.WARPED_NYLIUM.getDefaultState());
                         if(entityWorld.getBlockState(blockPos).isOf(Blocks.CRIMSON_HYPHAE))entityWorld.setBlockState(blockPos, Blocks.WARPED_HYPHAE.getDefaultState());
                         if(entityWorld.getBlockState(blockPos).isOf(Blocks.CRIMSON_FUNGUS))entityWorld.setBlockState(blockPos, Blocks.WARPED_FUNGUS.getDefaultState());
                     }
                 }
             }
         }
    }

    public void waterPearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        float radius = power(entityWorld, user);
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos((int) entityX + x, (int) entityY + y, (int) entityZ + z);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        if (!entityWorld.getDimension().ultrawarm() && (entityWorld.getBlockState(blockPos).isAir() || entityWorld.getBlockState(blockPos).isIn(REPLACEABLE_PLANTS))) {
                        entityWorld.setBlockState(blockPos, Blocks.WATER.getDefaultState());
                        }
                        if (entityWorld.getFluidState(blockPos).isOf(Fluids.LAVA)) {
                            entityWorld.setBlockState(blockPos, Blocks.OBSIDIAN.getDefaultState());
                        }
                        if (entityWorld.getFluidState(blockPos).isOf(Fluids.FLOWING_LAVA)){
                            entityWorld.setBlockState(blockPos, Blocks.COBBLESTONE.getDefaultState());
                        }
                        if (entityWorld.getBlockState(blockPos).isOf(Blocks.FIRE)||(entityWorld.getFluidState(blockPos).isOf(Fluids.FLOWING_LAVA))) {
                            entityWorld.setBlockState(blockPos, Blocks.AIR.getDefaultState());
                        }
                    }
                }
            }
        }
        float radius2 = radius*4;
        for (int x = (int) -radius2 - 1; x <= radius2; x++) {
            for (int y = (int) -radius2 - 1; y <= radius2; y++) {
                for (int z = (int) -radius2 - 1; z <= radius2; z++) {
                    BlockPos blockPos = new BlockPos((int) entityX + x, (int) entityY + y, (int) entityZ + z);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius2) {
                        if (entityWorld.getFluidState(blockPos).isOf(Fluids.FLOWING_WATER)) {
                            entityWorld.setBlockState(blockPos, Blocks.WATER.getDefaultState());
                        }
                    }
                }
            }
        }
        entity.playSound(SoundEvents.AMBIENT_UNDERWATER_EXIT, 0.1F, 0F);
    }

    public void webPearlEffect(Entity entity, double entityX, double entityY, double entityZ, World entityWorld, Entity user) {
        float radius = power(entityWorld, user)*3;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos((int) entityX + x, (int) entityY + y, (int) entityZ + z);
                    if ( Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius){
                        if (entityWorld.getBlockState(blockPos).isAir()||entityWorld.getBlockState(blockPos).isIn(REPLACEABLE_PLANTS)||entityWorld.getBlockState(blockPos).isOf(Blocks.SNOW)) {
                        entityWorld.setBlockState(blockPos, ModBlocks.TEMP_WEB_BLOCK.getDefaultState());
                        }
                    }
                }
            }
        }
        entity.playSound(SoundEvents.ENTITY_SPIDER_AMBIENT, 0.4F, -1F);
    }

// enchantment and status effect radius'

    public void whiteGlowEffect(int amplifier, Entity entity, double entityX, double entityY, double entityZ, World entityWorld) {
        float radius = amplifier*3;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos((int) entityX + x, (int) entityY + y, (int) entityZ + z);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius){
                        if (entityWorld.getBlockState(blockPos).isOf(ModBlocks.RED_BLOCK)) {
                            entityWorld.setBlockState(blockPos, ModBlocks.RED_WHITE_BLOCK.getDefaultState());
                        }
                        else if (entityWorld.getBlockState(blockPos).isAir()) {
                            entityWorld.setBlockState(blockPos, ModBlocks.WHITE_BLOCK.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    public void redGlowEffect(int amplifier, Entity entity, double entityX, double entityY, double entityZ, World entityWorld) {
        float radius = amplifier*3;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos((int) entityX + x, (int) entityY + y, (int) entityZ + z);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius){
                        if (entityWorld.getBlockState(blockPos).isOf(ModBlocks.WHITE_BLOCK)) {
                            entityWorld.setBlockState(blockPos, ModBlocks.RED_WHITE_BLOCK.getDefaultState());
                        }
                        else if (entityWorld.getBlockState(blockPos).isAir()) {
                            entityWorld.setBlockState(blockPos, ModBlocks.RED_BLOCK.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    public void oreSight(int amplifier, double entityX, double entityY, double entityZ, World entityWorld) {
        float radius = amplifier + 3;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos((int) entityX + x, (int) entityY + y, (int) entityZ + z);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                        if (entityWorld.getBlockState(blockPos).isOf(Blocks.COAL_ORE) || (entityWorld.getBlockState(blockPos).isOf(Blocks.DEEPSLATE_COAL_ORE))){
                            entityWorld.playSound(null, blockPos, Registries.SOUND_EVENT.get(new Identifier("block.note_block.bass")), SoundCategory.BLOCKS, 0.8F,1f);
                        }
                        if (entityWorld.getBlockState(blockPos).isOf(Blocks.COPPER_ORE) || (entityWorld.getBlockState(blockPos).isOf(Blocks.DEEPSLATE_COPPER_ORE))){
                            entityWorld.playSound(null, blockPos, Registries.SOUND_EVENT.get(new Identifier("block.note_block.chime")), SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos).isOf(Blocks.LAPIS_ORE) || (entityWorld.getBlockState(blockPos).isOf(Blocks.DEEPSLATE_LAPIS_ORE))){
                            entityWorld.playSound(null, blockPos, Registries.SOUND_EVENT.get(new Identifier("block.note_block.flute")), SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos).isOf(Blocks.IRON_ORE) || (entityWorld.getBlockState(blockPos).isOf(Blocks.DEEPSLATE_IRON_ORE))){
                            entityWorld.playSound(null, blockPos, Registries.SOUND_EVENT.get(new Identifier("block.note_block.bit")), SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos).isOf(Blocks.REDSTONE_ORE) || (entityWorld.getBlockState(blockPos).isOf(Blocks.DEEPSLATE_REDSTONE_ORE))){
                            entityWorld.playSound(null, blockPos, Registries.SOUND_EVENT.get(new Identifier("block.note_block.xylophone")), SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos).isOf(Blocks.DIAMOND_ORE) || (entityWorld.getBlockState(blockPos).isOf(Blocks.DEEPSLATE_DIAMOND_ORE))){
                            entityWorld.playSound(null, blockPos, Registries.SOUND_EVENT.get(new Identifier("block.note_block.harp")), SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos).isOf(Blocks.GOLD_ORE) || (entityWorld.getBlockState(blockPos).isOf(Blocks.NETHER_GOLD_ORE)) || (entityWorld.getBlockState(blockPos).isOf(Blocks.DEEPSLATE_GOLD_ORE))){
                            entityWorld.playSound(null, blockPos, Registries.SOUND_EVENT.get(new Identifier("block.note_block.bell")), SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos).isOf(Blocks.EMERALD_ORE) || (entityWorld.getBlockState(blockPos).isOf(Blocks.DEEPSLATE_EMERALD_ORE))){
                            entityWorld.playSound(null, blockPos, Registries.SOUND_EVENT.get(new Identifier("block.note_block.didgeridoo")), SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos).isOf(Blocks.ANCIENT_DEBRIS)){
                            entityWorld.playSound(null, blockPos, Registries.SOUND_EVENT.get(new Identifier("block.note_block.banjo")), SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                        if (entityWorld.getBlockState(blockPos).isOf(Blocks.NETHER_QUARTZ_ORE)){
                            entityWorld.playSound(null, blockPos, Registries.SOUND_EVENT.get(new Identifier("block.note_block.snare")), SoundCategory.BLOCKS, 0.8F, 1f);
                        }
                    }
                }
            }
        }
    }

    public void loveEffect(int amplifier, LivingEntity user, double entityX, double entityY, double entityZ, World entityWorld) {
        float radius = amplifier + 3;
        PlayerEntity player = ((PlayerEntity) user);
        for(Entity entities : entityWorld.getOtherEntities(null, new Box(entityX-radius, entityY-radius, entityZ-radius, entityX+radius, entityY+radius, entityZ+radius))) {
            if(entities instanceof TameableEntity && !((TameableEntity) entities).isTamed()) {
                ((TameableEntity) entities).setOwner(player);
            }
        }
    }
}
