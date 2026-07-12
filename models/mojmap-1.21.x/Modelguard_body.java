// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelguard_body<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "guard_body"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart ring;
	private final ModelPart ring2;
	private final ModelPart ring3;

	public Modelguard_body(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.ring = root.getChild("ring");
		this.ring2 = root.getChild("ring2");
		this.ring3 = root.getChild("ring3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(),
				PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(104, 26)
				.addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -18.0F, 0.0F));

		PartDefinition ring = partdefinition.addOrReplaceChild("ring",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-12.0F, -1.0F, -12.0F, 2.0F, 2.0F, 24.0F, new CubeDeformation(0.0F)).texOffs(0, 26)
						.addBox(10.0F, -1.0F, -12.0F, 2.0F, 2.0F, 24.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition cube_r1 = ring.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(52, 104).addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 20.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 0.0F, -11.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r2 = ring.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(104, 50).addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 20.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 0.0F, 11.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition ring2 = partdefinition.addOrReplaceChild("ring2",
				CubeListBuilder.create().texOffs(0, 52)
						.addBox(-15.0F, -1.0F, -12.0F, 2.0F, 2.0F, 24.0F, new CubeDeformation(0.0F)).texOffs(52, 0)
						.addBox(13.0F, -1.0F, -12.0F, 2.0F, 2.0F, 24.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition cube_r3 = ring2.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(52, 26).addBox(-1.0F, -1.0F, -12.0F, 2.0F, 2.0F, 24.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 14.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r4 = ring2.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(52, 52).addBox(-1.0F, -1.0F, -12.0F, 2.0F, 2.0F, 24.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -14.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition ring3 = partdefinition.addOrReplaceChild("ring3",
				CubeListBuilder.create().texOffs(0, 78)
						.addBox(-15.0F, -1.0F, -12.0F, 2.0F, 2.0F, 24.0F, new CubeDeformation(0.0F)).texOffs(52, 78)
						.addBox(13.0F, -1.0F, -12.0F, 2.0F, 2.0F, 24.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition cube_r5 = ring3.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(104, 0).addBox(-1.0F, -1.0F, -12.0F, 2.0F, 2.0F, 24.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 14.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r6 = ring3.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(0, 104).addBox(-1.0F, -1.0F, -12.0F, 2.0F, 2.0F, 24.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -14.0F, 0.0F, -1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ring.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ring2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ring3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}