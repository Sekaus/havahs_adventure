package net.mcreator.havahsadventure.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.havahsadventure.entity.GuardAttackEntity;
import net.mcreator.havahsadventure.client.model.Modelguard_attack;

public class GuardAttackRenderer extends MobRenderer<GuardAttackEntity, Modelguard_attack<GuardAttackEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("havahs_adventure:textures/entities/guard_attack.png");

	public GuardAttackRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelguard_attack<GuardAttackEntity>(context.bakeLayer(Modelguard_attack.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(GuardAttackEntity entity) {
		return entityTexture;
	}
}