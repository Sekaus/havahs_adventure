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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.havahsadventure.init.HavahsAdventureModBlocks;

import javax.annotation.Nullable;

@EventBusSubscriber
public class RemovePlantInPotProcedure {
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

        // Check if block is your potted plant variant
        if (blockstate.getBlock() == HavahsAdventureModBlocks.POTTED_LIGHT_SPIRAL.get()) {
            if (entity instanceof LivingEntity _livEnt) {
                // Vanilla mechanic: Only remove plant if player's main hand is empty
                if (_livEnt.getMainHandItem().isEmpty()) {
                    BlockPos pos = BlockPos.containing(x, y, z);

                    // 1. Drop the item centered above the block (skip in Creative Mode)
                    boolean isCreative = entity instanceof Player player && player.getAbilities().instabuild;
                    if (!isCreative && world instanceof ServerLevel _level) {
                        ItemEntity entityToSpawn = new ItemEntity(_level, x + 0.5, y + 0.4, z + 0.5, new ItemStack(HavahsAdventureModBlocks.LIGHT_SPIRAL.get()));
                        entityToSpawn.setPickUpDelay(10);
                        _level.addFreshEntity(entityToSpawn);
                    }

                    // 2. Reset block state back to empty flower pot
                    world.setBlock(pos, Blocks.FLOWER_POT.defaultBlockState(), 3);

                    // 3. Cancel interaction event and swing hand
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