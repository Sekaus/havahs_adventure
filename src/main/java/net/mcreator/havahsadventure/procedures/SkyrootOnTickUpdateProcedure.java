package net.mcreator.havahsadventure.procedures;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;

import net.mcreator.havahsadventure.init.HavahsAdventureModBlocks;

import java.util.Random;

public class SkyrootOnTickUpdateProcedure extends net.mcreator.havahsadventure.Utils {

    private static final EnumProperty<?> PARTS_PROPERTY =
        (EnumProperty<?>) HavahsAdventureModBlocks.SKYROOT.get()
            .getStateDefinition()
            .getProperty("parts");

    public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
        if (world.isClientSide()) return;

        BlockPos currentPos = BlockPos.containing(x, y, z);
        
        BlockEntity be = world.getBlockEntity(currentPos);

        // Initialize NBT
        initializeNBT(be, currentPos.getY());

        int rootY = getBlockNBTInt(be, "rootY");
        int maxHeight = getBlockNBTInt(be, "maxHeight");
        int seed = getBlockNBTInt(be, "growthSeed");
        boolean hasFruit = getBlockNBTBoolean(be, "hasFruit");
        int currentY = currentPos.getY();

        // Fruit state
        if (currentY == rootY && !hasFruit) {
            // Use 'blockstate' (from method args) and 'currentPos' (defined above)
            var currentProperty = blockstate.getValue((EnumProperty) PARTS_PROPERTY);
            
            // Check if it's the final stem stage
            if ("stem_6".equals(currentProperty.toString())) {
			    CompoundTag data = be.getPersistentData();
			    data.putBoolean("hasFruit", true);
			    be.setChanged();
			
			    BlockState nextState = blockstate;
			    var partValue = PARTS_PROPERTY.getValue("stem_with_fruit");
			
			    if (partValue.isPresent()) {
			        nextState = nextState.setValue(
			            (EnumProperty) PARTS_PROPERTY,
			            (Enum) partValue.get()
			        );
			    }
			
			    if (nextState != blockstate) {
			        world.setBlock(currentPos, nextState, 3);
			    }
			
			    return;
			}
        }

        // Only run growth and visual updates from the TOP block and if there is no fruit on
        if (world.getBlockState(currentPos.above()).getBlock() == HavahsAdventureModBlocks.SKYROOT.get() && hasFruit)
            return;
		
        // Growth Logic
        if (currentY < rootY + maxHeight - 1) {
            BlockPos abovePos = currentPos.above();

            // Check BOTH:
            // 1. Top space is empty
            // 2. Future wide turn parts have enough room
            if (world.isEmptyBlock(abovePos)
                    && canGrow(world, currentPos, rootY, currentY)) {

                world.setBlock(
                    abovePos,
                    HavahsAdventureModBlocks.SKYROOT.get().defaultBlockState(),
                    3
                );

                transferNBT(world, currentPos, abovePos);

                currentY++;
            } 
            else
                return;
	    }

        // Visual Pass
        MutableBlockPos scanPos = new MutableBlockPos(currentPos.getX(), currentY, currentPos.getZ());

        for (int yLevel = currentY; yLevel >= rootY; yLevel--) {

            scanPos.set(currentPos.getX(), yLevel, currentPos.getZ());

            BlockState scanState = world.getBlockState(scanPos);

            if (scanState.getBlock() == HavahsAdventureModBlocks.SKYROOT.get()) {
				updateBlockVisual(world, scanPos, scanState, currentY, seed);
            }
        }
    }

    /**
     * Prevent growth if any future wide turn block would collide.
     */
    private static boolean canGrow(
        LevelAccessor world,
        BlockPos topPos,
        int rootY,
        int currentTopY
    ) {

        // Simulate one block of future growth
        int projectedTopY = currentTopY + 1;

        for (int yLevel = rootY; yLevel <= projectedTopY; yLevel++) {

            int futureIndex = projectedTopY - yLevel;

            String futurePart = getPartForIndex(futureIndex);

            // Only turn segments need extra space
            if (isWidePart(futurePart)) {

                BlockPos checkPos =
                    new BlockPos(topPos.getX(), yLevel, topPos.getZ());

                // Check only the 4 horizontal neighbors
                for (Direction dir : new Direction[] {
                        Direction.NORTH,
                        Direction.SOUTH,
                        Direction.EAST,
                        Direction.WEST
                }) {

                    BlockPos sidePos = checkPos.relative(dir);

                    // Ignore other skyroot blocks
                    BlockState sideState = world.getBlockState(sidePos);

                    if (!world.isEmptyBlock(sidePos)
                            && sideState.getBlock() != HavahsAdventureModBlocks.SKYROOT.get()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static boolean isWidePart(String part) {
        return "stem_turn_0_a".equals(part)
            || "stem_turn_0_b".equals(part)
            || "stem_turn_1_a".equals(part)
            || "stem_turn_1_b".equals(part);
    }

    private static String getPartForIndex(int index) {
        return switch (index) {
            case 0 -> "stem_0";
            case 1 -> "stem_1";
            case 2 -> "stem_turn_0_a";
            case 3 -> "stem_turn_0_b";
            case 4 -> "stem_2";
            case 5 -> "stem_3";
            case 6 -> "stem_turn_1_a";
            case 7 -> "stem_turn_1_b";
            case 8 -> "stem_4";
            case 9 -> "stem_5";
            default -> "stem_6";
        };
    }

    private static void updateBlockVisual(
        LevelAccessor world,
        BlockPos pos,
        BlockState state,
        int topY,
        int seed
    ) {

        if (PARTS_PROPERTY == null || !state.hasProperty(PARTS_PROPERTY)) {
            return;
        }

        // Distance from top
        int index = topY - pos.getY();
        
		BlockEntity be = world.getBlockEntity(pos);
		
        String targetPart = getPartForIndex(index);

		if (index == 10 && getBlockNBTBoolean(be, "hasFruit")) {
		    targetPart = "stem_with_fruit";
		}

        // Tiered hashing
        int hash0 = seed;
        int hash1 = (seed * 31) ^ (seed >> 5);
        int hash2 = (seed * 17) ^ (seed >> 3);

        Rotation rot0 = Rotation.values()[Math.abs(hash0) % 4];
        Rotation rot1 = Rotation.values()[Math.abs(hash1) % 4];
        Rotation rot2 = Rotation.values()[Math.abs(hash2) % 4];

        Rotation finalRot;

        if (index <= 1) {
            finalRot = rot0;
        } else if (index <= 5) {
            finalRot = rot1;
        } else {
            finalRot = rot2;
        }

        BlockState nextState = state;

        var partValue = PARTS_PROPERTY.getValue(targetPart);

        if (partValue.isPresent()) {

            nextState =
                nextState.setValue(
                    (EnumProperty) PARTS_PROPERTY,
                    (Enum) partValue.get()
                );

            if (nextState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {

                Direction dir = finalRot.rotate(Direction.NORTH);

                nextState =
                    nextState.setValue(
                        BlockStateProperties.HORIZONTAL_FACING,
                        dir
                    );
            }
        }

        if (nextState != state) {
            world.setBlock(pos, nextState, 3);
        }
    }

    private static void initializeNBT(
        BlockEntity be,
        int y
    ) {
        if (be != null && !be.getPersistentData().getBoolean("rootYSet")) {

            CompoundTag data = be.getPersistentData();

            data.putInt("rootY", y);
            data.putInt("maxHeight", 11);
            data.putBoolean("hasFruit", false);

            // Large seed range for better variation
            data.putInt(
                "growthSeed",
                new Random().nextInt(2_000_000)
            );

            data.putBoolean("rootYSet", true);

            be.setChanged();
        }
    }

    private static void transferNBT(
    LevelAccessor world,
    BlockPos source,
    BlockPos target
) {
    BlockEntity sourceBE = world.getBlockEntity(source);
    BlockEntity targetBE = world.getBlockEntity(target);

    if (sourceBE != null && targetBE != null) {
        CompoundTag src = sourceBE.getPersistentData();
        CompoundTag dst = targetBE.getPersistentData();

        dst.putInt("rootY", src.getInt("rootY"));
        dst.putInt("maxHeight", src.getInt("maxHeight"));
        dst.putInt("growthSeed", src.getInt("growthSeed"));
        dst.putBoolean("hasFruit", src.getBoolean("hasFruit"));
        dst.putBoolean("rootYSet", true);

        targetBE.setChanged();
    }
}
}