package net.mcreator.havahsadventure.procedures;

import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.havahsadventure.init.HavahsAdventureModBlocks;

public class SkyrootOnBlockRightclickedProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
        if (entity == null)
            return;
            
        BlockPos clickedPos = BlockPos.containing(x, y, z);

        // --- Stage 1: Right-clicked a fully grown stem with fruit ---
        if ((getPropertyByName(blockstate, "parts") instanceof EnumProperty _getep1 ? blockstate.getValue(_getep1).toString() : "").equals("stem_with_fruit")) {
            {
                String _value = "stem_6";
                if (blockstate.getBlock().getStateDefinition().getProperty("parts") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
                    world.setBlock(clickedPos, blockstate.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
            }
            if (!world.isClientSide() && world.getServer() != null) {
                for (ItemStack itemstackiterator : world.getServer().reloadableRegistries().getLootTable(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("havahs_adventure:gameplay/skyroot")))
                        .getRandomItems(new LootParams.Builder((ServerLevel) world).create(LootContextParamSets.EMPTY))) {
                    if (world instanceof ServerLevel _level) {
                        ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, itemstackiterator);
                        entityToSpawn.setPickUpDelay(10);
                        _level.addFreshEntity(entityToSpawn);
                    }
                }
            }
        } 
        // --- Stage 2: Using Bone Meal on the plant ---
        else {
            ItemStack itemstackInHand = entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY;
            
            if (itemstackInHand.getItem() == Items.BONE_MEAL) {
                // 1. Find the absolute top block of this specific Skyroot vine
                BlockPos.MutableBlockPos topPos = new BlockPos.MutableBlockPos(clickedPos.getX(), clickedPos.getY(), clickedPos.getZ());
                while (world.getBlockState(topPos.above()).getBlock() == HavahsAdventureModBlocks.SKYROOT.get()) {
                    topPos.move(net.minecraft.core.Direction.UP);
                }
                
                // 2. Call your tick procedure directly at the top block's position
                BlockState topState = world.getBlockState(topPos);
                SkyrootOnTickUpdateProcedure.execute(world, topPos.getX(), topPos.getY(), topPos.getZ(), topState);
                
                // 3. Play visual particle effects at the block the player actually clicked
                if (world instanceof net.minecraft.world.level.Level _level) {
                    _level.levelEvent(1505, clickedPos, 0); 
                }
                
                // 4. Safely consume 1 bone meal directly from the player's hand (respects creative mode)
                if (!(entity instanceof net.minecraft.world.entity.player.Player _player && _player.getAbilities().instabuild)) {
                    itemstackInHand.shrink(1);
                }
            }
        }
    }

    private static Property<?> getPropertyByName(BlockState state, String name) {
        for (Property<?> property : state.getProperties()) {
            if (property.getName().equals(name)) {
                return property;
            }
        }
        return null;
    }
}