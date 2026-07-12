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

import net.mcreator.havahsadventure.procedures.PegasusHasASaddleOnProcedure;
import net.mcreator.havahsadventure.procedures.IsWalkingProcedure;
import net.mcreator.havahsadventure.entity.PegasusEntity;
import net.mcreator.havahsadventure.client.model.animations.PegasusAnimation;
import net.mcreator.havahsadventure.client.model.Modelpegasus_saddle;
import net.mcreator.havahsadventure.client.model.ModelPegasus;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class PegasusRenderer extends MobRenderer<PegasusEntity, ModelPegasus<PegasusEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("havahs_adventure:textures/entities/pegasus.png");

	public PegasusRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(ModelPegasus.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<PegasusEntity, ModelPegasus<PegasusEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = ResourceLocation.parse("havahs_adventure:textures/entities/pegasus.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, PegasusEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (PegasusHasASaddleOnProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					EntityModel model = new Modelpegasus_saddle(Minecraft.getInstance().getEntityModels().bakeLayer(Modelpegasus_saddle.LAYER_LOCATION));
					this.getParentModel().copyPropertiesTo(model);
					model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0));
				}
			}
		});
	}

	@Override
	protected void scale(PegasusEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(0.6f, 0.6f, 0.6f);
		poseStack.scale(entity.getAgeScale(), entity.getAgeScale(), entity.getAgeScale());
	}

	@Override
	public ResourceLocation getTextureLocation(PegasusEntity entity) {
		return entityTexture;
	}

	private static final class AnimatedModel extends ModelPegasus<PegasusEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<PegasusEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(PegasusEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				if (IsWalkingProcedure.execute(entity))
					this.animateWalk(PegasusAnimation.walking, limbSwing, limbSwingAmount, 4f, 1f);
				this.animate(entity.animationState1, PegasusAnimation.start_flying, ageInTicks, 1f);
				this.animate(entity.animationState2, PegasusAnimation.flying, ageInTicks, 1f);
				this.animate(entity.animationState3, PegasusAnimation.landing, ageInTicks, 1f);
				this.animate(entity.animationState4, PegasusAnimation.eating, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(PegasusEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}