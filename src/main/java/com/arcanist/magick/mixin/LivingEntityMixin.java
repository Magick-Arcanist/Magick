package com.arcanist.magick.mixin;

import com.arcanist.magick.registry.ModEffects;
import com.arcanist.magick.util.EntityReturnProperties;
import com.arcanist.magick.statuseffect.effects.ImmortalStatusEffect;
import com.arcanist.magick.statuseffect.effects.SpiderClimbStatusEffect;
import com.arcanist.magick.util.DimensionPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements EntityReturnProperties {

    @Shadow public abstract boolean removeStatusEffect(StatusEffect type);

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    public LivingEntityMixin(EntityType<?> type, World world, AttributeContainer attributes) {
        super(type, world);
        this.attributes = attributes;
    }

    private DimensionPosition recallPosition = null;
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
    static {HEALTH = DataTracker.registerData(LivingEntityMixin.class, TrackedDataHandlerRegistry.FLOAT);}

    @Inject(at = @At("HEAD"), method = "isClimbing", cancellable = true)
    public void SpiderClimbCheck(CallbackInfoReturnable<Boolean> info) {
        double entityX = this.getX();
        double entityY = this.getY();
        double entityZ = this.getZ();
        World entityWorld = this.getEntityWorld();

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos blockPos = new BlockPos(MathHelper.floor(entityX) + x, MathHelper.floor(entityY), MathHelper.floor(entityZ) + z);
                if (SpiderClimbStatusEffect.hasEffect(this) && entityWorld.getBlockState(blockPos).isSolidBlock(entityWorld, blockPos)) {
                    info.setReturnValue(true);
                }
            }
        }
    }

    //Undying
    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    public void ImmortalCheck(CallbackInfoReturnable<Boolean> info) {
        if (ImmortalStatusEffect.hasEffect(this)) {
            info.setReturnValue(true);
        }
    }

    //Returning read injection
    @Inject(at = @At("TAIL"), method = "readCustomDataFromNbt")
    public void readNbt(NbtCompound tag, CallbackInfo cb) {
        if (tag.contains("recallPosition")) {
            recallPosition = DimensionPosition.fromTag(tag.getCompound("recallPosition"));
        }
    }
    //Returning write injection
    @Inject(at = @At("TAIL"), method = "writeCustomDataToNbt")
    public void writeNbt(NbtCompound tag, CallbackInfo cb) {
        if (recallPosition != null) {
            tag.put("recallPosition", recallPosition.toTag());
        }
    }

    //Returning clear data
    @Inject(at = @At("HEAD"), method = "clearStatusEffects")
    public void clearReturningPosition(CallbackInfoReturnable<Boolean> info) {
        Entity entity = this;
        ((EntityReturnProperties) entity).setReturnPos(null);
    }

    @Override
    public DimensionPosition getReturnPos() {
        return recallPosition;
    }

    @Override
    public void setReturnPos(DimensionPosition pos) {
        recallPosition = pos;
    }


    //Make opposite effects counter each other
    @Inject(at = @At("TAIL"), method = "tickStatusEffects")
    public void counterEffects(CallbackInfo ci){
        if (this.hasStatusEffect(ModEffects.GRAVITY) & this.hasStatusEffect(StatusEffects.LEVITATION)){
            this.removeStatusEffect(ModEffects.GRAVITY); this.removeStatusEffect((StatusEffects.LEVITATION));
        }
        if (this.hasStatusEffect(StatusEffects.SPEED) & this.hasStatusEffect(StatusEffects.SLOWNESS)){
                this.removeStatusEffect((StatusEffects.SPEED)); this.removeStatusEffect(StatusEffects.SLOWNESS);
        }
        if (this.hasStatusEffect(StatusEffects.HASTE) & this.hasStatusEffect(StatusEffects.MINING_FATIGUE)){
                this.removeStatusEffect((StatusEffects.HASTE)); this.removeStatusEffect(StatusEffects.MINING_FATIGUE);
        }
        if (this.hasStatusEffect(StatusEffects.LUCK) & this.hasStatusEffect(StatusEffects.UNLUCK)){
                this.removeStatusEffect((StatusEffects.LUCK)); this.removeStatusEffect(StatusEffects.UNLUCK);
        }
        if (this.hasStatusEffect(StatusEffects.NIGHT_VISION) & this.hasStatusEffect(StatusEffects.BLINDNESS)){
                this.removeStatusEffect((StatusEffects.NIGHT_VISION)); this.removeStatusEffect(StatusEffects.BLINDNESS);
        }
        if (this.hasStatusEffect(StatusEffects.STRENGTH) & this.hasStatusEffect(StatusEffects.WEAKNESS)){
                this.removeStatusEffect((StatusEffects.STRENGTH)); this.removeStatusEffect(StatusEffects.WEAKNESS);
        }
        if (this.hasStatusEffect(StatusEffects.GLOWING) & this.hasStatusEffect(StatusEffects.INVISIBILITY)){
                this.removeStatusEffect((StatusEffects.GLOWING)); this.removeStatusEffect(StatusEffects.INVISIBILITY);
        }
        if (this.hasStatusEffect(ModEffects.FEAR) & this.hasStatusEffect(ModEffects.LOVE)) {
                this.removeStatusEffect((ModEffects.FEAR)); this.removeStatusEffect(ModEffects.LOVE);
        }
    }
}
