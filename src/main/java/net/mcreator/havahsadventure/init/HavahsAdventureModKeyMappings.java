/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.havahsadventure.init;

import org.lwjgl.glfw.GLFW;

import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.havahsadventure.network.PegasusFlyUpMessage;

@EventBusSubscriber(Dist.CLIENT)
public class HavahsAdventureModKeyMappings {
	public static final KeyMapping PEGASUS_FLY_UP = new KeyMapping("key.havahs_adventure.pegasus_fly_up", GLFW.GLFW_KEY_SPACE, "key.categories.movement") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				PEGASUS_FLY_UP_LASTPRESS = System.currentTimeMillis();
			} else if (isDownOld != isDown && !isDown) {
				int dt = (int) (System.currentTimeMillis() - PEGASUS_FLY_UP_LASTPRESS);
				PacketDistributor.sendToServer(new PegasusFlyUpMessage(1, dt));
				PegasusFlyUpMessage.pressAction(Minecraft.getInstance().player, 1, dt);
			}
			isDownOld = isDown;
		}
	};
	private static long PEGASUS_FLY_UP_LASTPRESS = 0;

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(PEGASUS_FLY_UP);
	}

	@EventBusSubscriber(Dist.CLIENT)
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(ClientTickEvent.Post event) {
			if (Minecraft.getInstance().screen == null) {
				PEGASUS_FLY_UP.consumeClick();
			}
		}
	}
}