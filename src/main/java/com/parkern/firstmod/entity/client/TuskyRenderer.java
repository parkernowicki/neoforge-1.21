package com.parkern.firstmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.entity.custom.TuskyEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TuskyRenderer extends MobRenderer<TuskyEntity, TuskyModel<TuskyEntity>> {
    public TuskyRenderer(EntityRendererProvider.Context context) {
        super(context, new TuskyModel<>(context.bakeLayer(TuskyModel.LAYER_LOCATION)), 1.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(TuskyEntity tuskyEntity) {
        return ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "textures/entity/tusky/tusky.png");
    }

    @Override
    public void render(TuskyEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
