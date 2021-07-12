package com.arcanist.magick.block;


import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;


public class LightOrb extends Block {

    public LightOrb(Settings settings) {
        super(Settings.of(Material.DECORATION)
                .noCollision()
                .breakInstantly()
                .luminance((state) -> {return 15;})
                .sounds(BlockSoundGroup.SHROOMLIGHT)
        );
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.375f, 0.375f, 0.375f, 0.625f, 0.625f, 0.625f);
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.INVISIBLE;
    }

}

