package net.mcreator.havahsadventure.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.ParticleTypes;

public class LightCrystalBlockOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		for (int index0 = 0; index0 < 4; index0++) {
			world.addParticle(ParticleTypes.END_ROD, (x + Mth.nextDouble(RandomSource.create(), -1, 1) + 0.5), (y + Mth.nextDouble(RandomSource.create(), -1, 1.5) + 0.5), (z + Mth.nextDouble(RandomSource.create(), -1, 1) + 0.5), 0, (-0.1), 0);
		}
	}
}