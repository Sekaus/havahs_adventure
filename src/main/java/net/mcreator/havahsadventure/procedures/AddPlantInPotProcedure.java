package net.mcreator.havahsadventure.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.havahsadventure.init.HavahsAdventureModBlocks;

import javax.annotation.Nullable;

@EventBusSubscriber
public class AddPlantInPotProcedure {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getHand() != InteractionHand.MAIN_HAND)
            return;
        
        execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getLevel().getBlockState(event.getPos()), event.getEntity());
    }

    public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
        execute(null, world, x, y, z, blockstate, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
        if (entity == null)
            return;

        // Check if the target block is a standard flower pot
        if (blockstate.getBlock() == Blocks.FLOWER_POT) {
            if (entity instanceof LivingEntity _livEnt) {
                ItemStack heldStack = _livEnt.getMainHandItem();

                // Check if holding the custom plant item
                if (heldStack.getItem() == HavahsAdventureModBlocks.LIGHT_SPIRAL.get().asItem()) {
                    BlockPos pos = BlockPos.containing(x, y, z);

                    // 1. Shrink item stack if not in Creative Mode
                    if (!(entity instanceof Player player && player.getAbilities().instabuild)) {
                        heldStack.shrink(1);
                    }

                    // 2. Replace the flower pot block with the potted plant variant
                    world.setBlock(pos, HavahsAdventureModBlocks.POTTED_LIGHT_SPIRAL.get().defaultBlockState(), 3);

                    // 3. Cancel event and trigger hand swing
                    if (event instanceof PlayerInteractEvent.RightClickBlock clickEvent) {
                        clickEvent.setCanceled(true);
                        clickEvent.setCancellationResult(InteractionResult.sidedSuccess(world.isClientSide()));
                    }
                    if (entity instanceof Player player) {
                        player.swing(InteractionHand.MAIN_HAND, true);
                    }
                }
            }
        }
    }
}