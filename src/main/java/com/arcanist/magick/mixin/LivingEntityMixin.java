package com.arcanist.magick.mixin;

import com.arcanist.magick.entitydata.EntityProperties;
import com.arcanist.magick.registry.ModEffects;
import com.arcanist.magick.statuseffect.effects.ImmortalStatusEffect;
import com.arcanist.magick.statuseffect.effects.SpiderClimbStatusEffect;
import com.arcanist.magick.util.DimensionalPosition;
import com.google.common.collect.Maps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements EntityProperties {

    public LivingEntityMixin(EntityType<?> type, World world, AttributeContainer attributes) {
        super(type, world);
        this.attributes = attributes;
    }

    private DimensionalPosition recallPosition = null;
    private static final TrackedData<Float> HEALTH;
    public final float getMaxHealth() {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH);
    }
    public double getAttributeValue(EntityAttribute attribute) {
        return this.getAttributes().getValue(attribute);
    }
    public AttributeContainer getAttributes() {
        return this.attributes;
    }
    private final AttributeContainer attributes;
    static {HEALTH = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.FLOAT);}

    public void setHealth(float health) {
        this.dataTracker.set(HEALTH, MathHelper.clamp(health, 0.0F, this.getMaxHealth()));
    }

    //Climbing
    @Inject(at = @At("HEAD"), method = "isClimbing", cancellable = true)
    public void SpiderClimbCheck(CallbackInfoReturnable<Boolean> info) {
        if (SpiderClimbStatusEffect.hasEffect(this) && this.horizontalCollision) {
            info.setReturnValue(true);
        }
    }

    //Undying
    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    public void ImmortalCheck(CallbackInfoReturnable<Boolean> info) {
        if (ImmortalStatusEffect.hasEffect(this)) {
            info.setReturnValue(true);
        }
    }

    //Recall read injection
    @Inject(at = @At("TAIL"), method = "readCustomDataFromNbt")
    public void readNbt(NbtCompound tag, CallbackInfo cb) {
        if (tag.contains("recallPosition")) {
            recallPosition = DimensionalPosition.fromTag(tag.getCompound("recallPosition"));
        }
    }
    //Recall write injection
    @Inject(at = @At("TAIL"), method = "writeCustomDataToNbt")
    public void writeNbt(NbtCompound tag, CallbackInfo cb) {
        if (recallPosition != null) {
            tag.put("recallPosition", recallPosition.toTag());
        }
    }

    //Recall clear data
    @Inject(at = @At("HEAD"), method = "clearStatusEffects", cancellable = true)
    public void clearRecallPosition(CallbackInfoReturnable<Boolean> info) {
        Entity entity = this;
        ((EntityProperties) entity).setRecallData(null);
    }

    @Override
    public DimensionalPosition getRecallPosition() {
        return recallPosition;
    }

    @Override
    public void setRecallData(DimensionalPosition pos) {
        recallPosition = pos;
    }

}
