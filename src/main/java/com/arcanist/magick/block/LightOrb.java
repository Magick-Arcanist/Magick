package com.arcanist.magick.block;


import net.minecraft.block.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;


public class LightOrb extends Block {

    public LightOrb(Settings settings) {
        super(Settings.of(Material.DECORATION)
                .noCollision()
                .breakInstantly()
                .luminance((state) -> 15)
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

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(8) == 0) {
            world.addParticle(ParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0.0D, 0.0D, 0.0D);
        }
    }

}

