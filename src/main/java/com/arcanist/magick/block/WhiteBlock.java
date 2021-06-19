package com.arcanist.magick.block;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import java.util.Random;


public class WhiteBlock extends Block {

    public WhiteBlock(Settings settings) {
        super(Settings.of(Material.AIR)
                .noCollision()
                .luminance((state) -> {return 10;})
        );
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(!world.isClient) {
            world.removeBlock(pos, true);
        }
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        worldIn.getBlockTickScheduler().schedule(pos, this, 2);
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.INVISIBLE;
    }

    
}

