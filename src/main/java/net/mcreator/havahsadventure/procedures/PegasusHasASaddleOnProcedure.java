package net.mcreator.havahsadventure.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.havahsadventure.entity.PegasusEntity;

public class PegasusHasASaddleOnProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (entity instanceof PegasusEntity _datEntL0 && _datEntL0.getEntityData().get(PegasusEntity.DATA_has_a_saddle_on)) {
			return true;
		}
		return false;
	}
}