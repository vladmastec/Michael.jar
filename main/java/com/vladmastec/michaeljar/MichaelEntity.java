package com.vladmastec.michaeljar;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MichaelEntity extends Entity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private Player target;
    private double speed = 0.6;

    public MichaelEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide) {
            if (target == null || target.isRemoved()) {
                target = level().getNearestPlayer(this, 50.0);
            }
            if (target != null) {
                Vec3 toTarget = target.position().subtract(position());
                double dist = toTarget.length();
                if (dist > 0.1) {
                    Vec3 move = toTarget.normalize().scale(Math.min(speed, dist));
                    setPos(getX() + move.x, getY() + move.y, getZ() + move.z);
                }
                double dx = target.getX() - getX();
                double dz = target.getZ() - getZ();
                float yaw = (float) Math.toDegrees(Math.atan2(-dx, dz));
                setYRot(yaw);

                if (dist < 1.5) {
                    level().playSound(null, blockPosition(), ModSounds.JUMPSCARE.get(), SoundSource.HOSTILE, 1.5F, 1.0F);
                    discard();
                }
            } else if (tickCount > 200) {
                discard();
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {}

    @Override
    protected void addAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {}
}