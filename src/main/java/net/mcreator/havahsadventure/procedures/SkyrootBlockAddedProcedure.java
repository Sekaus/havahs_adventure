package net.mcreator.havahsadventure.procedures;

import net.mcreator.havahsadventure.init.HavahsAdventureModBlocks;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class SkyrootBlockAddedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) return;
	
	    BlockPos currentPos = BlockPos.containing(x, y, z);
	    BlockPos.MutableBlockPos scanPos = currentPos.mutable();
	
	    // Scan down until we find the block directly above soil/air
	    while (world.getBlockState(scanPos.below()).getBlock() == HavahsAdventureModBlocks.SKYROOT.get()) {
	        scanPos.move(Direction.DOWN);
	    }
	
	    // scanPos is now at the absolute lowest Skyroot block in the column
	    int trueRootY = scanPos.getY();
	
	    // Set rootY on this newly placed block
	    BlockEntity be = world.getBlockEntity(currentPos);
	    if (be != null) {
	        CompoundTag data = be.getPersistentData();
	        data.putInt("rootY", trueRootY);
	        data.putBoolean("rootYSet", true);
	        be.setChanged();
    }
	}
}