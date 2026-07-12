package net.mcreator.havahsadventure.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.havahsadventure.init.HavahsAdventureModBlocks;
import net.mcreator.havahsadventure.HavahsAdventureMod;

import javax.annotation.Nullable;

@EventBusSubscriber
public class NearAbovePortalProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		execute(null, world, x, y, z);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
		boolean isPlaying = false;
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double atX = 0;
		double atY = 0;
		double atZ = 0;
		if (!isPlaying) {
			sx = -3;
			found = false;
			for (int index0 = 0; index0 < 6; index0++) {
				sy = -3;
				for (int index1 = 0; index1 < 6; index1++) {
					sz = -3;
					for (int index2 = 0; index2 < 6; index2++) {
						atX = x + sx;
						atY = y + sy;
						atZ = z + sz;
						if ((world.getBlockState(BlockPos.containing(atX, atY, atZ))).getBlock() == HavahsAdventureModBlocks.ABOVE_PORTAL.get()) {
							found = true;
							break;
						}
						sz = sz + 1;
					}
					sy = sy + 1;
				}
				sx = sx + 1;
			}
			if (found == true) {
				isPlaying = true;
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(atX, atY, atZ), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("havahs_adventure:above_portel.ambient")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(atX, atY, atZ, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("havahs_adventure:above_portel.ambient")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
			}
		} else {
			HavahsAdventureMod.queueServerWork(365, () -> {
			});
			isPlaying = false;
		}
	}
}