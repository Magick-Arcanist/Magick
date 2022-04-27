package com.arcanist.magick.item;

import com.arcanist.magick.entity.PlantPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PlantPearlItem extends Item {
    public PlantPearlItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 1F);

		user.getItemCooldownManager().set(this, 20);

        if (!world.isClient) {
            PlantPearlEntity plantPearlEntity = new PlantPearlEntity(world, user);
            plantPearlEntity .setItem(itemStack);
            plantPearlEntity .setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 0F);
            world.spawnEntity(plantPearlEntity );
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

}
