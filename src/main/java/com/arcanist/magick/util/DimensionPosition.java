package com.arcanist.magick.util;


import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class DimensionPosition {

    private final double x, y, z;
    private final Identifier world;

    public DimensionPosition(double x, double y, double z, Identifier world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public DimensionPosition(double x, double y, double z, World world) {
        this(x, y, z, world.getRegistryKey().getValue());
    }

    public DimensionPosition(Entity entity) {
        this(entity.getPos().x, entity.getPos().y, entity.getPos().z, entity.getEntityWorld());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Identifier getWorldId() {
        return world;
    }

    public World getWorld(MinecraftServer server) {
        return server.getWorld(RegistryKey.of(RegistryKeys.WORLD, world));
    }

    public static DimensionPosition fromTag(NbtCompound tag) {
        return new DimensionPosition(tag.getDouble("x"), tag.getDouble("y"), tag.getDouble("z"), new Identifier(tag.getString("world")));
    }

    public NbtCompound toTag() {
        NbtCompound tag = new NbtCompound();
        tag.putDouble("x", x);
        tag.putDouble("y", y);
        tag.putDouble("z", z);
        tag.putString("world", world.toString());
        return tag;
    }

    @Override
    public String toString() {
        return "DimensionPos[" + x + ", " + y + ", " + z + " @ " + world + "]";
    }
}