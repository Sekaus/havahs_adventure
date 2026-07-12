package net.mcreator.havahsadventure.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FallingBlock;

import com.mojang.serialization.MapCodec;

public class AboveSandBlock extends FallingBlock {
	public static final MapCodec<AboveSandBlock> CODEC = simpleCodec(properties -> new AboveSandBlock());

	public MapCodec<AboveSandBlock> codec() {
		return CODEC;
	}

	public AboveSandBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.SAND).strength(1f, 10f).instrument(NoteBlockInstrument.BASEDRUM));
	}
}