package net.mcreator.havahsadventure.block;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.util.RandomSource;
import net.minecraft.core.BlockPos;

import net.mcreator.havahsadventure.procedures.LightCrystalBlockOnTickUpdateProcedure;

public class LightCrystalBlockBlock extends Block {
	public LightCrystalBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.AMETHYST).strength(2f, 100f).lightLevel(blockstate -> 15));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 8;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		super.animateTick(blockstate, world, pos, random);
		LightCrystalBlockOnTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}