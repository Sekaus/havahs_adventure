package net.mcreator.havahsadventure.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class AboveBrickSlabBlockBlock extends SlabBlock {
	public AboveBrickSlabBlockBlock() {
		super(BlockBehaviour.Properties.of().strength(3f, 100f));
	}
}