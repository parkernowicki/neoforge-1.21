package com.parkern.firstmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.entity.custom.GrouseEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GrouseRenderer extends MobRenderer<GrouseEntity, GrouseModel<GrouseEntity>> {
    public GrouseRenderer(EntityRendererProvider.Context context) {
        super(context, new GrouseModel<>(context.bakeLayer(GrouseModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(GrouseEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "textures/entity/grouse/grouse.png");
    }

    @Override
    public void render(GrouseEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
