package net.mcreator.havahsadventure;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;

public abstract class Utils {
	protected static int getBlockNBTInt(
        BlockEntity be,
        String tag
    ) {
        return be != null
            ? be.getPersistentData().getInt(tag)
            : 0;
    }

    protected static boolean getBlockNBTBoolean(
        BlockEntity be,
        String tag
    ) {
        return be != null
            ? be.getPersistentData().getBoolean(tag)
            : false;
    }
}