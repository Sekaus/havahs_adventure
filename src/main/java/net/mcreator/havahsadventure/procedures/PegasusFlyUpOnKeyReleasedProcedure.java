package net.mcreator.havahsadventure.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.havahsadventure.entity.PegasusEntity;

public class PegasusFlyUpOnKeyReleasedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity ridingEntity = null;
		Vec3 lookAngle = Vec3.ZERO;
		if (entity.isPassenger()) {
			ridingEntity = entity.getVehicle();
			if (ridingEntity instanceof PegasusEntity) {
				lookAngle = entity.getLookAngle();
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.ender_dragon.flap")), SoundSource.AMBIENT, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.ender_dragon.flap")), SoundSource.AMBIENT, 1, 1, false);
					}
				}
				ridingEntity.setDeltaMovement(new Vec3((lookAngle.x() * 2), 1, (lookAngle.z() * 2)));
			}
		}
	}
}