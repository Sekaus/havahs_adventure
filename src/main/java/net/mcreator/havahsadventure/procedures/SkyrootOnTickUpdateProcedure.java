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

public class SkyrootOnTickUpdateProcedure extends net.mcreator.havahsadventure.Utils {

    private static final EnumProperty<?> PARTS_PROPERTY =
        (EnumProperty<?>) HavahsAdventureModBlocks.SKYROOT.get()
            .getStateDefinition()
            .getProperty("parts");

    public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
        if (world.isClientSide()) return;

        BlockPos currentPos = BlockPos.containing(x, y, z);

        // 1. Guard Condition: ONLY the topmost block executes growth & visual updates
        if (world.getBlockState(currentPos.above()).getBlock() == HavahsAdventureModBlocks.SKYROOT.get()) {
            return;
        }

        // 2. Locate true base block
        BlockPos rootPos = getRootPosition(world, currentPos);
        if (rootPos == null) {
            return; // Abort if floating without valid soil below
        }

        int rootY = rootPos.getY();
        int currentY = currentPos.getY();

        // Fetch / initialize root data
        BlockEntity rootBE = world.getBlockEntity(rootPos);
        if (rootBE != null) {
            initializeRootNBT(world, rootBE);
        }

        int maxHeight = 11;
        int seed = rootBE != null ? getBlockNBTInt(rootBE, "growthSeed") : 0;
        boolean hasFruit = rootBE != null && getBlockNBTBoolean(rootBE, "hasFruit");
        int currentHeight = currentY - rootY + 1;

        // 3. Fruit Conversion (Triggers once full height is reached)
        if (currentHeight >= maxHeight && !hasFruit) {
            if (rootBE != null) {
                CompoundTag data = rootBE.getPersistentData();
                data.putBoolean("hasFruit", true);
                rootBE.setChanged();
            }
            hasFruit = true; // Update local flag so the visual pass below applies it immediately
        }

        // 4. Growth Logic
        if (currentHeight < maxHeight) {
            BlockPos abovePos = currentPos.above();

            if (world.isEmptyBlock(abovePos) && canGrow(world, currentPos, rootY, currentY)) {
                world.setBlock(
                    abovePos,
                    HavahsAdventureModBlocks.SKYROOT.get().defaultBlockState(),
                    3
                );
                
                // Update top height marker so the visual pass includes the newly placed top block
                currentY++;
            }
        }

        // 5. Visual Pass (Runs EVERY tick, recalculating parts dynamically)
        MutableBlockPos scanPos = new MutableBlockPos(currentPos.getX(), currentY, currentPos.getZ());

        for (int yLevel = currentY; yLevel >= rootY; yLevel--) {
            scanPos.set(currentPos.getX(), yLevel, currentPos.getZ());
            BlockState scanState = world.getBlockState(scanPos);

            if (scanState.getBlock() == HavahsAdventureModBlocks.SKYROOT.get()) {
                updateBlockVisual(world, scanPos, scanState, currentY, seed, hasFruit);
            }
        }
    }

    private static BlockPos getRootPosition(LevelAccessor world, BlockPos pos) {
        BlockPos.MutableBlockPos scanPos = pos.mutable();

        while (world.getBlockState(scanPos.below()).getBlock() == HavahsAdventureModBlocks.SKYROOT.get()) {
            scanPos.move(Direction.DOWN);
        }

        BlockState groundState = world.getBlockState(scanPos.below());
        if (groundState.isAir()) {
            return null;
        }

        return scanPos.immutable();
    }

    private static void initializeRootNBT(LevelAccessor world, BlockEntity rootBE) {
        CompoundTag data = rootBE.getPersistentData();
        if (!data.getBoolean("seedSet")) {
            data.putInt("growthSeed", world.getRandom().nextInt(2_000_000));
            data.putBoolean("hasFruit", false);
            data.putBoolean("seedSet", true);
            rootBE.setChanged();
        }
    }

    private static boolean canGrow(
        LevelAccessor world,
        BlockPos topPos,
        int rootY,
        int currentTopY
    ) {
        int projectedTopY = currentTopY + 1;

        for (int yLevel = rootY; yLevel <= projectedTopY; yLevel++) {
            int futureIndex = projectedTopY - yLevel;
            String futurePart = getPartForIndex(futureIndex);

            if (isWidePart(futurePart)) {
                BlockPos checkPos = new BlockPos(topPos.getX(), yLevel, topPos.getZ());

                for (Direction dir : new Direction[] {
                        Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST
                }) {
                    BlockPos sidePos = checkPos.relative(dir);
                    BlockState sideState = world.getBlockState(sidePos);

                    if (!world.isEmptyBlock(sidePos) && sideState.getBlock() != HavahsAdventureModBlocks.SKYROOT.get()) {
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
        int seed,
        boolean hasFruit
    ) {
        if (PARTS_PROPERTY == null || !state.hasProperty(PARTS_PROPERTY)) {
            return;
        }

        int index = topY - pos.getY();
        String targetPart = getPartForIndex(index);

        // Convert the base block (stem_6) into stem_with_fruit when fruit is active
        if (index == 10 && hasFruit) {
            targetPart = "stem_with_fruit";
        }

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
            nextState = nextState.setValue(
                (EnumProperty) PARTS_PROPERTY,
                (Enum) partValue.get()
            );

            if (nextState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                Direction dir = finalRot.rotate(Direction.NORTH);
                nextState = nextState.setValue(
                    BlockStateProperties.HORIZONTAL_FACING,
                    dir
                );
            }
        }

        if (nextState != state) {
            world.setBlock(pos, nextState, 3);
        }
    }
}