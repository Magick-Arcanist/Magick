package com.arcanist.magick.block;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.math.random.Random;


public class TempWebBlock extends CobwebBlock {

    public TempWebBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
            world.removeBlock(pos, false);
    }

    @Override
    public void scheduledTick(BlockState state,ServerWorld world, BlockPos pos, Random random) {
            world.removeBlock(pos, false);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, 2000);
    }

}

