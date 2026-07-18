package net.mcreator.havahsadventure.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.Difficulty;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

import net.mcreator.havahsadventure.init.HavahsAdventureModEntities;
import net.mcreator.havahsadventure.entity.GuardEntity;

import java.util.Comparator;

public class GuardOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putDouble("eye_timer", (entity.getPersistentData().getDouble("eye_timer") + 1));
		if (entity.getPersistentData().getDouble("eye_timer") >= 20) {
			{
				final Vec3 _center = new Vec3(x, y, z);
				for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(12 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
					entity.getPersistentData().putDouble("eye_timer", 0);
					if (entityiterator instanceof Player && (getEntityGameType(entityiterator) == GameType.SURVIVAL || getEntityGameType(entityiterator) == GameType.SURVIVAL)) {
						if (entity instanceof GuardEntity _datEntSetI)
							_datEntSetI.getEntityData().set(GuardEntity.DATA_eye_state, 0);
						if (world.getDifficulty() == Difficulty.EASY) {
							if (Mth.nextInt(RandomSource.create(), 0, 2) == 1) {
								if (world instanceof ServerLevel _level) {
									Entity entityToSpawn = HavahsAdventureModEntities.GUARD_ATTACK.get().spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
									if (entityToSpawn != null) {
										entityToSpawn.setDeltaMovement(0, 0, 0);
									}
								}
							}
						} else if (world.getDifficulty() == Difficulty.NORMAL) {
							if (Mth.nextInt(RandomSource.create(), 0, 1) == 1) {
								if (world instanceof ServerLevel _level) {
									Entity entityToSpawn = HavahsAdventureModEntities.GUARD_ATTACK.get().spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
									if (entityToSpawn != null) {
										entityToSpawn.setDeltaMovement(0, 0, 0);
									}
								}
							}
						} else if (world.getDifficulty() == Difficulty.HARD) {
							if (world instanceof ServerLevel _level) {
								Entity entityToSpawn = HavahsAdventureModEntities.GUARD_ATTACK.get().spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
								if (entityToSpawn != null) {
									entityToSpawn.setDeltaMovement(0, 0, 0);
								}
							}
						}
					} else {
						if (entity instanceof GuardEntity _datEntSetI)
							_datEntSetI.getEntityData().set(GuardEntity.DATA_eye_state, 2);
					}
				}
			}
		}
	}

	private static GameType getEntityGameType(Entity entity) {
		if (entity instanceof ServerPlayer serverPlayer) {
			return serverPlayer.gameMode.getGameModeForPlayer();
		} else if (entity instanceof Player player && player.level().isClientSide()) {
			PlayerInfo playerInfo = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());
			if (playerInfo != null)
				return playerInfo.getGameMode();
		}
		return null;
	}
}