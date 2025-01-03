package com.parkern.firstmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.entity.custom.GrouseEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class GrouseModel<T extends GrouseEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "grouse"), "main");
    private final ModelPart grouse;
    private final ModelPart head;

    public GrouseModel(ModelPart root) {
        this.grouse = root.getChild("grouse");
        this.head = this.grouse.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition grouse = partdefinition.addOrReplaceChild("grouse", CubeListBuilder.create(), PartPose.offset(0.0F, 23.95F, 0.0F));

        PartDefinition torso = grouse.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 14).addBox(3.0F, -3.0F, -3.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(14, 14).addBox(-4.0F, -3.0F, -3.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition tail = torso.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 4.0F));

        PartDefinition tail_r1 = tail.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(28, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition head = grouse.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 24).addBox(-1.0F, -5.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(28, 10).addBox(-0.5F, -4.0F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 5).addBox(0.0F, -7.0F, -1.5F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, -3.5F));

        PartDefinition right_leg = grouse.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(22, 24).addBox(-1.5F, 1.0183F, -2.1905F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -3.0F, 0.0F));

        PartDefinition left_leg = grouse.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(10, 24).addBox(-1.5F, 1.0183F, -1.8095F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -3.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(GrouseEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(GrouseAnimations.ANIM_GROUSE_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, GrouseAnimations.ANIM_GROUSE_IDLE, ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch *  ((float)Math.PI / 180f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        grouse.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return grouse;
    }
}
