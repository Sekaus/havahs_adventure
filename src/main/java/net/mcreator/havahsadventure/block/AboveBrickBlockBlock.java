package net.mcreator.havahsadventure.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class AboveBrickBlockBlock extends Block {
	public AboveBrickBlockBlock() {
		super(BlockBehaviour.Properties.of().strength(3f, 100f));
	}
}