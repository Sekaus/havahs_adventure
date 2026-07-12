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

public class PegasusOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof PegasusEntity _datEntL0 && _datEntL0.getEntityData().get(PegasusEntity.DATA_is_jumping)) == false) {
			if (entity.isVehicle()) {
				if (!(entity instanceof PegasusEntity _datEntL2 && _datEntL2.getEntityData().get(PegasusEntity.DATA_flying))) {
					if (entity instanceof PegasusEntity _datEntSetS)
						_datEntSetS.getEntityData().set(PegasusEntity.DATA_play_animation, "start_flying");
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.ender_dragon.flap")), SoundSource.AMBIENT, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.ender_dragon.flap")), SoundSource.AMBIENT, 1, 1, false);
						}
					}
					entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), 0.5, (entity.getDeltaMovement().z())));
					if (entity instanceof PegasusEntity _datEntSetL)
						_datEntSetL.getEntityData().set(PegasusEntity.DATA_flying, true);
				}
			} else {
				if (entity instanceof PegasusEntity _datEntSetL)
					_datEntSetL.getEntityData().set(PegasusEntity.DATA_flying, false);
				if (entity instanceof PegasusEntity _datEntSetS)
					_datEntSetS.getEntityData().set(PegasusEntity.DATA_play_animation, "landing");
			}
		} else {
			if (entity instanceof PegasusEntity _datEntSetL)
				_datEntSetL.getEntityData().set(PegasusEntity.DATA_is_jumping, false);
			if (entity instanceof PegasusEntity _datEntSetS)
				_datEntSetS.getEntityData().set(PegasusEntity.DATA_play_animation, "start_flying");
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.ender_dragon.flap")), SoundSource.AMBIENT, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.ender_dragon.flap")), SoundSource.AMBIENT, 1, 1, false);
				}
			}
			entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), 0.5, (entity.getDeltaMovement().z())));
		}
	}
}