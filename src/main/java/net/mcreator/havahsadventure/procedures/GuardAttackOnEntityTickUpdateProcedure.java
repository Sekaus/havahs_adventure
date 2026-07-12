package net.mcreator.havahsadventure.procedures;

import net.minecraft.world.entity.Entity;

public class GuardAttackOnEntityTickUpdateProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.tickCount > 160) {
			if (!entity.level().isClientSide())
				entity.discard();
		}
	}
}