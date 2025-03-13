package com.parkern.firstmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.entity.custom.TuskyEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class TuskyModel<T extends TuskyEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "tusky"), "main");
    private final ModelPart tusky;
    private final ModelPart upper_torso;
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public TuskyModel(ModelPart root) {
        this.tusky = root.getChild("tusky");
        this.upper_torso = this.tusky.getChild("upper_torso");
        this.body = this.upper_torso.getChild("body");
        this.left_arm = this.upper_torso.getChild("left_arm");
        this.right_arm = this.upper_torso.getChild("right_arm");
        this.left_leg = this.tusky.getChild("left_leg");
        this.right_leg = this.tusky.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition tusky = partdefinition.addOrReplaceChild("tusky", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 0.0F));

        PartDefinition upper_torso = tusky.addOrReplaceChild("upper_torso", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 0.0F));

        PartDefinition body = upper_torso.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -14.0F, -7.0F, 18.0F, 24.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(60, 66).addBox(-9.0F, 10.0F, -7.0F, 18.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(52, 0).addBox(-5.0F, -8.0F, -9.0F, 10.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(2.0F, -3.0F, -8.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -3.0F, -8.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));

        PartDefinition left_arm = upper_torso.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(24, 40).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 20.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(12.0F, -24.0F, 1.0F));

        PartDefinition right_arm = upper_torso.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 40).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 20.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-12.0F, -24.0F, 1.0F));

        PartDefinition left_leg = tusky.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(30, 66).addBox(-3.5F, -1.0F, -2.5F, 7.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(48, 53).addBox(-3.5F, 7.0F, -5.5F, 7.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 11.0F, -1.0F));

        PartDefinition right_leg = tusky.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 66).addBox(-3.5F, -1.0F, -2.5F, 7.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(48, 40).addBox(-3.5F, 7.0F, -5.5F, 7.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 11.0F, -1.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(TuskyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animateWalk(TuskyAnimations.ANIM_TUSKY_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        tusky.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return tusky;
    }
}