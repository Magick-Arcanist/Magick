package com.arcanist.magick.mixin;

import com.arcanist.magick.util.EntityReturnProperties;
import com.mojang.authlib.GameProfile;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.DifficultyS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusEffectS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldEventS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.WorldProperties;
import net.minecraft.world.biome.source.BiomeAccess;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile, @Nullable PlayerPublicKey publicKey) {
        super(world, pos, yaw, gameProfile, publicKey);
    }

    // This class is not currently used because it does not work on servers when travelling from the Nether to the Overworld
/*
    @Shadow
    private boolean inTeleportationState;
    @Shadow
    public ServerPlayNetworkHandler networkHandler;
    @Final
    @Shadow
    public ServerPlayerInteractionManager interactionManager;
    @Final
    @Shadow
    public MinecraftServer server;
    @Shadow
    private float syncedHealth;
    @Shadow
    private int syncedExperience;
    @Shadow
    private int syncedFoodLevel;
    @Shadow
    public abstract ServerWorld getWorld();
    @Shadow
    protected abstract void worldChanged(ServerWorld origin);
    @Shadow
    public abstract void setWorld(ServerWorld world);

    @Inject(method = "moveToWorld", at = @At("HEAD"), cancellable = true)
    public void moveToWorld(ServerWorld destination, CallbackInfoReturnable<Entity> ci) {
        this.inTeleportationState = true;
        ServerWorld startingWorld = this.getWorld();
        RegistryKey<World> registryKey = startingWorld.getRegistryKey();
        EntityReturnProperties ep = (EntityReturnProperties) this;
           if (!(ep.getReturnPos() == null) && (registryKey == World.END && destination.getRegistryKey() == World.OVERWORLD)) {
               WorldProperties worldProperties = destination.getLevelProperties();
               this.networkHandler.sendPacket(new PlayerRespawnS2CPacket(destination.getDimensionKey(), destination.getRegistryKey(), BiomeAccess.hashSeed(destination.getSeed()), this.interactionManager.getGameMode(), this.interactionManager.getPreviousGameMode(), destination.isDebugWorld(), destination.isFlat(), true, this.getLastDeathPos()));
               this.networkHandler.sendPacket(new DifficultyS2CPacket(worldProperties.getDifficulty(), worldProperties.isDifficultyLocked()));
               PlayerManager playerManager = this.server.getPlayerManager();
               playerManager.sendCommandTree((ServerPlayerEntity) (Object) this);
               startingWorld.removePlayer((ServerPlayerEntity) (Object) this, RemovalReason.CHANGED_DIMENSION);
               this.unsetRemoved();
               TeleportTarget teleportTarget = this.getTeleportTarget(destination);
               if (teleportTarget != null) {
                   startingWorld.getProfiler().push("moving");
                   startingWorld.getProfiler().pop();
                   startingWorld.getProfiler().push("placing");
                   this.setWorld(destination);
                   destination.onPlayerChangeDimension((ServerPlayerEntity) (Object) this);
                   this.setRotation(teleportTarget.yaw, teleportTarget.pitch);
                   this.refreshPositionAfterTeleport(teleportTarget.position.x, teleportTarget.position.y, teleportTarget.position.z);
                   startingWorld.getProfiler().pop();
                   this.worldChanged(startingWorld);
                   this.networkHandler.sendPacket(new PlayerAbilitiesS2CPacket(this.getAbilities()));
                   playerManager.sendWorldInfo((ServerPlayerEntity) (Object) this, destination);
                   playerManager.sendPlayerStatus((ServerPlayerEntity) (Object) this);
                   for (StatusEffectInstance statusEffectInstance : this.getStatusEffects()) {
                       this.networkHandler.sendPacket(new EntityStatusEffectS2CPacket(this.getId(), statusEffectInstance));
                   }
                   this.networkHandler.sendPacket(new WorldEventS2CPacket(1032, BlockPos.ORIGIN, 0, false));
                   this.syncedExperience = -1;
                   this.syncedHealth = -1.0F;
                   this.syncedFoodLevel = -1;
               }
               ci.setReturnValue((ServerPlayerEntity) (Object) this);
           }
    }

 */
}