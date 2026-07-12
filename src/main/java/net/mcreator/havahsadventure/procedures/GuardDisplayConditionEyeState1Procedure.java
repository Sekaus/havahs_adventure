package net.mcreator.havahsadventure.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.havahsadventure.entity.GuardEntity;

public class GuardDisplayConditionEyeState1Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof GuardEntity _datEntI ? _datEntI.getEntityData().get(GuardEntity.DATA_eye_state) : 0) == 1) {
			return true;
		}
		return false;
	}
}