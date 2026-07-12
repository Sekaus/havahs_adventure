/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.havahsadventure.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.havahsadventure.client.renderer.PegasusRenderer;
import net.mcreator.havahsadventure.client.renderer.GuardRenderer;
import net.mcreator.havahsadventure.client.renderer.GuardAttackRenderer;

@EventBusSubscriber(Dist.CLIENT)
public class HavahsAdventureModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(HavahsAdventureModEntities.PEGASUS.get(), PegasusRenderer::new);
		event.registerEntityRenderer(HavahsAdventureModEntities.GUARD.get(), GuardRenderer::new);
		event.registerEntityRenderer(HavahsAdventureModEntities.GUARD_ATTACK.get(), GuardAttackRenderer::new);
	}
}