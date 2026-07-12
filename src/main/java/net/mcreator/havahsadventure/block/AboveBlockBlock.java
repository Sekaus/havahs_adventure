package net.mcreator.havahsadventure.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class AboveBlockBlock extends Block {
	public AboveBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(1.05f, 100f));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 8;
	}
}