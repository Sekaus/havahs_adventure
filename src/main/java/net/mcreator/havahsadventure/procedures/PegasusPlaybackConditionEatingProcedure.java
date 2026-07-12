package net.mcreator.havahsadventure.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.havahsadventure.entity.PegasusEntity;

public class PegasusPlaybackConditionEatingProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		String playingAnimation = "";
		if ((entity instanceof PegasusEntity _datEntS ? _datEntS.getEntityData().get(PegasusEntity.DATA_play_animation) : "").equals("eating")) {
			return true;
		}
		return false;
	}
}