package com.vladmastec.michaeljar;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MichaelRenderer extends GeoEntityRenderer<MichaelEntity> {

    public MichaelRenderer(EntityRendererProvider.Context context) {
        super(context, new GeoModel<MichaelEntity>() {
            @Override
            public ResourceLocation getModelResource(MichaelEntity object) {
                return new ResourceLocation(MichaelJar.MOD_ID, "geo/michael.geo.json");
            }

            @Override
            public ResourceLocation getTextureResource(MichaelEntity object) {
                return new ResourceLocation(MichaelJar.MOD_ID, "textures/jumpscare_texture.png");
            }

            @Override
            public ResourceLocation getAnimationResource(MichaelEntity animatable) {
                return new ResourceLocation(MichaelJar.MOD_ID, "animations/jumpscare.animation.json");
            }
        });
    }

    @Override
    public void render(MichaelEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            Vec3 toPlayer = player.position().subtract(entity.position());
            float yaw = (float) Math.toDegrees(Math.atan2(-toPlayer.x, toPlayer.z));
            poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        }
        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
        poseStack.popPose();
    }
}