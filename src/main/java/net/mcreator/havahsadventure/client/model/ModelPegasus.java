package net.mcreator.havahsadventure.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelPegasus<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("havahs_adventure", "model_pegasus"), "main");
	public final ModelPart root;
	public final ModelPart head;
	public final ModelPart tail_bone;
	public final ModelPart joint;
	public final ModelPart joint2;
	public final ModelPart joint3;
	public final ModelPart joint4;
	public final ModelPart right_wing;
	public final ModelPart bone;
	public final ModelPart left_wing;
	public final ModelPart bone2;

	public ModelPegasus(ModelPart root) {
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.tail_bone = this.root.getChild("tail_bone");
		this.joint = this.root.getChild("joint");
		this.joint2 = this.root.getChild("joint2");
		this.joint3 = this.root.getChild("joint3");
		this.joint4 = this.root.getChild("joint4");
		this.right_wing = this.root.getChild("right_wing");
		this.bone = this.right_wing.getChild("bone");
		this.left_wing = this.root.getChild("left_wing");
		this.bone2 = this.left_wing.getChild("bone2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -6.0F, -10.0F, 36.0F, 16.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.0F, 11.0F, 0.0F, 1.5708F, 0.0F));
		PartDefinition head = root.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(64, 93).addBox(-3.0F, -36.0F, 6.0F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(112, 27).addBox(-3.0F, -36.0F, -8.0F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(28.0F, 3.0F, 0.0F));
		PartDefinition nick_r1 = head.addOrReplaceChild("nick_r1", CubeListBuilder.create().texOffs(0, 118).addBox(-5.0F, -14.0F, -5.0F, 10.0F, 28.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.8164F, -7.7591F, 0.0F, 0.0F, 0.0F, 0.3927F));
		PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(0, 93).addBox(-10.0F, -6.5F, -6.0F, 20.0F, 13.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.2342F, -25.233F, 0.0F, 0.0F, 0.0F, 0.0436F));
		PartDefinition tail_bone = root.addOrReplaceChild("tail_bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition tail_r1 = tail_bone.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(64, 108).addBox(-13.5F, -5.0F, -3.0F, 27.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-11.5763F, 6.8731F, 0.0F, 0.0F, 0.0F, -0.9163F));
		PartDefinition joint = root.addOrReplaceChild("joint", CubeListBuilder.create().texOffs(112, 0).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 21.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(25.0F, 10.0F, 6.0F));
		PartDefinition joint2 = root.addOrReplaceChild("joint2", CubeListBuilder.create().texOffs(40, 118).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 21.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(25.0F, 10.0F, -6.0F));
		PartDefinition joint3 = root.addOrReplaceChild("joint3", CubeListBuilder.create().texOffs(88, 124).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 21.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 10.0F, 6.0F));
		PartDefinition joint4 = root.addOrReplaceChild("joint4", CubeListBuilder.create().texOffs(64, 124).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 21.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 10.0F, -6.0F));
		PartDefinition right_wing = root.addOrReplaceChild("right_wing", CubeListBuilder.create(), PartPose.offset(11.0F, -3.0F, 9.0F));
		PartDefinition cube_r1 = right_wing.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-1, 69).addBox(-10.0F, -0.5F, -11.5F, 20.0F, 1.0F, 23.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -9.0076F, 5.6002F, 0.8727F, 0.0F, 0.0F));
		PartDefinition bone = right_wing.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, -17.1544F, 13.0F, 0.8727F, 0.0F, 0.0F));
		PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(81, 60).addBox(-9.0F, -0.5F, -11.5F, 18.0F, 1.0F, 23.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.5826F, -10.3577F, -2.8798F, 0.0F, 0.0F));
		PartDefinition left_wing = root.addOrReplaceChild("left_wing", CubeListBuilder.create(), PartPose.offsetAndRotation(7.0F, -3.0F, -9.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r3 = left_wing.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(79, 36).addBox(-10.0F, -0.5F, -11.5F, 20.0F, 1.0F, 23.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -9.0076F, 5.6002F, 0.8727F, 0.0F, 0.0F));
		PartDefinition bone2 = left_wing.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, -17.0F, 13.0F, 0.8727F, 0.0F, 0.0F));
		PartDefinition cube_r4 = bone2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(81, 84).addBox(-9.0F, -0.5F, -11.5F, 18.0F, 1.0F, 23.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.4282F, -10.3577F, -2.8798F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}
}