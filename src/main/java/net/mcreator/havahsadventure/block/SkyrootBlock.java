package net.mcreator.havahsadventure.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.Containers;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.havahsadventure.procedures.SkyrootOnTickUpdateProcedure;
import net.mcreator.havahsadventure.procedures.SkyrootOnBlockRightclickedProcedure;
import net.mcreator.havahsadventure.procedures.SkyrootNeighbourBlockChangesProcedure;
import net.mcreator.havahsadventure.block.entity.SkyrootBlockEntity;

import com.google.common.collect.ImmutableMap;

public class SkyrootBlock extends Block implements SimpleWaterloggedBlock, EntityBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final EnumProperty<PartsProperty> PARTS = EnumProperty.create("parts", PartsProperty.class);
	private final ImmutableMap<BlockState, VoxelShape> shapes = this.makeShapes();

	public SkyrootBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.ROOTS).strength(1f, 10f).noOcclusion().randomTicks().isRedstoneConductor((bs, br, bp) -> false).instrument(NoteBlockInstrument.HAT));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PARTS, PartsProperty.STEM_0).setValue(WATERLOGGED, false));
	}

	private ImmutableMap<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			if (state.getValue(PARTS) == PartsProperty.STEM_0) {
				return switch (state.getValue(FACING)) {
					default -> box(7, 0, 7, 9, 8, 9);
					case NORTH -> box(7, 0, 7, 9, 8, 9);
					case EAST -> box(7, 0, 7, 9, 8, 9);
					case WEST -> box(7, 0, 7, 9, 8, 9);
				};
			} else if (state.getValue(PARTS) == PartsProperty.STEM_1) {
				return switch (state.getValue(FACING)) {
					default -> box(7, 0, 7, 9, 16, 9);
					case NORTH -> box(7, 0, 7, 9, 16, 9);
					case EAST -> box(7, 0, 7, 9, 16, 9);
					case WEST -> box(7, 0, 7, 9, 16, 9);
				};
			} else if (state.getValue(PARTS) == PartsProperty.STEM_2) {
				return switch (state.getValue(FACING)) {
					default -> box(6, 0, 6, 10, 16, 10);
					case NORTH -> box(6, 0, 6, 10, 16, 10);
					case EAST -> box(6, 0, 6, 10, 16, 10);
					case WEST -> box(6, 0, 6, 10, 16, 10);
				};
			} else if (state.getValue(PARTS) == PartsProperty.STEM_3) {
				return switch (state.getValue(FACING)) {
					default -> box(5, 0, 5, 11, 16, 11);
					case NORTH -> box(5, 0, 5, 11, 16, 11);
					case EAST -> box(5, 0, 5, 11, 16, 11);
					case WEST -> box(5, 0, 5, 11, 16, 11);
				};
			} else if (state.getValue(PARTS) == PartsProperty.STEM_4) {
				return switch (state.getValue(FACING)) {
					default -> box(4, 0, 4, 12, 16, 12);
					case NORTH -> box(4, 0, 4, 12, 16, 12);
					case EAST -> box(4, 0, 4, 12, 16, 12);
					case WEST -> box(4, 0, 4, 12, 16, 12);
				};
			} else if (state.getValue(PARTS) == PartsProperty.STEM_5) {
				return switch (state.getValue(FACING)) {
					default -> box(3, 0, 3, 13, 16, 13);
					case NORTH -> box(3, 0, 3, 13, 16, 13);
					case EAST -> box(3, 0, 3, 13, 16, 13);
					case WEST -> box(3, 0, 3, 13, 16, 13);
				};
			} else if (state.getValue(PARTS) == PartsProperty.STEM_6) {
				return switch (state.getValue(FACING)) {
					default -> box(2, 0, 2, 14, 16, 14);
					case NORTH -> box(2, 0, 2, 14, 16, 14);
					case EAST -> box(2, 0, 2, 14, 16, 14);
					case WEST -> box(2, 0, 2, 14, 16, 14);
				};
			} else if (state.getValue(PARTS) == PartsProperty.STEM_TURN_0_A) {
				return switch (state.getValue(FACING)) {
					default -> Shapes.or(box(0, 0, 6, 4, 16, 10), box(4, 12, 6, 10, 16, 10));
					case NORTH -> Shapes.or(box(12, 0, 6, 16, 16, 10), box(6, 12, 6, 12, 16, 10));
					case EAST -> Shapes.or(box(6, 0, 12, 10, 16, 16), box(6, 12, 6, 10, 16, 12));
					case WEST -> Shapes.or(box(6, 0, 0, 10, 16, 4), box(6, 12, 4, 10, 16, 10));
				};
			} else if (state.getValue(PARTS) == PartsProperty.STEM_TURN_0_B) {
				return switch (state.getValue(FACING)) {
					default -> Shapes.or(box(6, 0, 6, 10, 16, 10), box(0, 12, 6, 6, 16, 10));
					case NORTH -> Shapes.or(box(6, 0, 6, 10, 16, 10), box(10, 12, 6, 16, 16, 10));
					case EAST -> Shapes.or(box(6, 0, 6, 10, 16, 10), box(6, 12, 10, 10, 16, 16));
					case WEST -> Shapes.or(box(6, 0, 6, 10, 16, 10), box(6, 12, 0, 10, 16, 6));
				};
			} else if (state.getValue(PARTS) == PartsProperty.STEM_TURN_1_A) {
				return switch (state.getValue(FACING)) {
					default -> Shapes.or(box(-4, 0, 4, 4, 16, 12), box(4, 8, 4, 12, 16, 12));
					case NORTH -> Shapes.or(box(12, 0, 4, 20, 16, 12), box(4, 8, 4, 12, 16, 12));
					case EAST -> Shapes.or(box(4, 0, 12, 12, 16, 20), box(4, 8, 4, 12, 16, 12));
					case WEST -> Shapes.or(box(4, 0, -4, 12, 16, 4), box(4, 8, 4, 12, 16, 12));
				};
			} else if (state.getValue(PARTS) == PartsProperty.STEM_TURN_1_B) {
				return switch (state.getValue(FACING)) {
					default -> Shapes.or(box(4, 0, 4, 12, 16, 12), box(-4, 8, 4, 4, 16, 12));
					case NORTH -> Shapes.or(box(4, 0, 4, 12, 16, 12), box(12, 8, 4, 20, 16, 12));
					case EAST -> Shapes.or(box(4, 0, 4, 12, 16, 12), box(4, 8, 12, 12, 16, 20));
					case WEST -> Shapes.or(box(4, 0, 4, 12, 16, 12), box(4, 8, -4, 12, 16, 4));
				};
			} else if (state.getValue(PARTS) == PartsProperty.STEM_WITH_FRUIT) {
				return switch (state.getValue(FACING)) {
					default -> box(1, 0, 1, 15, 16, 15);
					case NORTH -> box(1, 0, 1, 15, 16, 15);
					case EAST -> box(1, 0, 1, 15, 16, 15);
					case WEST -> box(1, 0, 1, 15, 16, 15);
				};
			}
			return switch (state.getValue(FACING)) {
				default -> box(7, 0, 7, 9, 8, 9);
				case NORTH -> box(7, 0, 7, 9, 8, 9);
				case EAST -> box(7, 0, 7, 9, 8, 9);
				case WEST -> box(7, 0, 7, 9, 8, 9);
			};
		});
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return shapes.get(state);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return state.getFluidState().isEmpty();
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return propagatesSkylightDown(state, worldIn, pos) ? 0 : 1;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, PARTS, WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(PARTS, PartsProperty.STEM_0).setValue(WATERLOGGED, flag);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		SkyrootNeighbourBlockChangesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void randomTick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.randomTick(blockstate, world, pos, random);
		SkyrootOnTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		SkyrootOnBlockRightclickedProcedure.execute(world, x, y, z, blockstate, entity);
		return InteractionResult.SUCCESS;
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SkyrootBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity != null && blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof SkyrootBlockEntity be) {
				Containers.dropContents(world, pos, be);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
		BlockEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof SkyrootBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}

	public enum PartsProperty implements StringRepresentable {
		STEM_0("stem_0"), STEM_1("stem_1"), STEM_2("stem_2"), STEM_3("stem_3"), STEM_4("stem_4"), STEM_5("stem_5"), STEM_6("stem_6"), STEM_TURN_0_A("stem_turn_0_a"), STEM_TURN_0_B("stem_turn_0_b"), STEM_TURN_1_A("stem_turn_1_a"), STEM_TURN_1_B(
				"stem_turn_1_b"), STEM_WITH_FRUIT("stem_with_fruit");

		private final String name;

		private PartsProperty(String name) {
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}
}