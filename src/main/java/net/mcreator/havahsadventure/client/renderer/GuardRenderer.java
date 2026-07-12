package net.mcreator.havahsadventure.client.renderer;

import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.Minecraft;

import net.mcreator.havahsadventure.procedures.GuardDisplayConditionEyeState2Procedure;
import net.mcreator.havahsadventure.procedures.GuardDisplayConditionEyeState1Procedure;
import net.mcreator.havahsadventure.procedures.GuardDisplayConditionEyeState0Procedure;
import net.mcreator.havahsadventure.entity.GuardEntity;
import net.mcreator.havahsadventure.client.model.animations.guard_bodyAnimation;
import net.mcreator.havahsadventure.client.model.Modelguard_eyes_2;
import net.mcreator.havahsadventure.client.model.Modelguard_eyes_1;
import net.mcreator.havahsadventure.client.model.Modelguard_eyes_0;
import net.mcreator.havahsadventure.client.model.Modelguard_core;
import net.mcreator.havahsadventure.client.model.Modelguard_body;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class GuardRenderer extends MobRenderer<GuardEntity, Modelguard_body<GuardEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("havahs_adventure:textures/entities/guard.png");

	public GuardRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelguard_body.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<GuardEntity, Modelguard_body<GuardEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = ResourceLocation.parse("havahs_adventure:textures/entities/guard.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, GuardEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(LAYER_TEXTURE));
				EntityModel model = new Modelguard_core(Minecraft.getInstance().getEntityModels().bakeLayer(Modelguard_core.LAYER_LOCATION));
				this.getParentModel().copyPropertiesTo(model);
				model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
				model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0));
			}
		});
		this.addLayer(new RenderLayer<GuardEntity, Modelguard_body<GuardEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = ResourceLocation.parse("havahs_adventure:textures/entities/guard.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, GuardEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (GuardDisplayConditionEyeState0Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(LAYER_TEXTURE));
					EntityModel model = new Modelguard_eyes_0(Minecraft.getInstance().getEntityModels().bakeLayer(Modelguard_eyes_0.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0));
				}
			}
		});
		this.addLayer(new RenderLayer<GuardEntity, Modelguard_body<GuardEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = ResourceLocation.parse("havahs_adventure:textures/entities/guard.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, GuardEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (GuardDisplayConditionEyeState1Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(LAYER_TEXTURE));
					EntityModel model = new Modelguard_eyes_1(Minecraft.getInstance().getEntityModels().bakeLayer(Modelguard_eyes_1.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0));
				}
			}
		});
		this.addLayer(new RenderLayer<GuardEntity, Modelguard_body<GuardEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = ResourceLocation.parse("havahs_adventure:textures/entities/guard.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, GuardEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (GuardDisplayConditionEyeState2Procedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(LAYER_TEXTURE));
					EntityModel model = new Modelguard_eyes_2(Minecraft.getInstance().getEntityModels().bakeLayer(Modelguard_eyes_2.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0));
				}
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(GuardEntity entity) {
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelguard_body<GuardEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<GuardEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(GuardEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, guard_bodyAnimation.spin, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(GuardEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}