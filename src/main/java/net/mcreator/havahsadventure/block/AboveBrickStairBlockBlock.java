package net.mcreator.havahsadventure.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.Blocks;

public class AboveBrickStairBlockBlock extends StairBlock {
	public AboveBrickStairBlockBlock() {
		super(Blocks.AIR.defaultBlockState(), BlockBehaviour.Properties.of().strength(3f, 100f));
	}

	@Override
	public float getExplosionResistance() {
		return 100f;
	}
}