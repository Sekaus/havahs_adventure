package net.mcreator.havahsadventure.procedures;

import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.Entity;

import net.mcreator.havahsadventure.entity.PegasusEntity;

import javax.annotation.Nullable;

@EventBusSubscriber
public class PegasusJumpingProcedure {
	@SubscribeEvent
	public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof PegasusEntity) {
			if (entity instanceof PegasusEntity _datEntSetL)
				_datEntSetL.getEntityData().set(PegasusEntity.DATA_is_jumping, true);
		}
	}
}