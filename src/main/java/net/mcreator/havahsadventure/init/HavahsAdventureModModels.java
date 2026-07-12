/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.havahsadventure.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.havahsadventure.client.model.*;

@EventBusSubscriber(Dist.CLIENT)
public class HavahsAdventureModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelPegasus.LAYER_LOCATION, ModelPegasus::createBodyLayer);
		event.registerLayerDefinition(Modelpegasus_saddle.LAYER_LOCATION, Modelpegasus_saddle::createBodyLayer);
		event.registerLayerDefinition(Modelguard_body.LAYER_LOCATION, Modelguard_body::createBodyLayer);
		event.registerLayerDefinition(Modelguard_eyes_1.LAYER_LOCATION, Modelguard_eyes_1::createBodyLayer);
		event.registerLayerDefinition(Modelguard_attack.LAYER_LOCATION, Modelguard_attack::createBodyLayer);
		event.registerLayerDefinition(Modelguard_core.LAYER_LOCATION, Modelguard_core::createBodyLayer);
		event.registerLayerDefinition(Modelguard_eyes_0.LAYER_LOCATION, Modelguard_eyes_0::createBodyLayer);
		event.registerLayerDefinition(Modelguard_eyes_2.LAYER_LOCATION, Modelguard_eyes_2::createBodyLayer);
	}
}